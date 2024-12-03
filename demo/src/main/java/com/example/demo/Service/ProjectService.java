package com.example.demo.Service;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MessageRepository messageRepository;


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(int id, Person person) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            return personRepository.save(person);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }


    public List<Message> getMessagesByPersonId(int personId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            return person.get().messages;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Message getMessageByPersonIdMessageId(int personId, int messageId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            return person.get().messages.stream()
                    .filter(msg -> msg.id == messageId)
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Message addMessageToPerson(int personId, Message message) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            message.id = messageRepository.findAll().stream().mapToInt(msg -> msg.id).max().orElse(0) + 1;
            messageRepository.save(message);
            person.get().messages.add(message);
            return personRepository.save(person.get()).messages.get(person.get().messages.size()-1);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public void deleteMessageFromPerson(int personId, int messageId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            person.get().messages.removeIf(msg -> msg.id == messageId);
            personRepository.save(person.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}