package com.example.demo.Controller;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private ProjectService personService;

    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person addedPerson = personService.addPerson(person);
        return new ResponseEntity<>(addedPerson, HttpStatus.CREATED);
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/person/{personId}/message")
    public List<Message> getMessagesByPersonId(@PathVariable int personId) {
        return personService.getMessagesByPersonId(personId);
    }

    @GetMapping("/person/{personId}/message/{messageId}")
    public Message getMessageByPersonIdMessageId(@PathVariable int personId, @PathVariable int messageId) {
        return personService.getMessageByPersonIdMessageId(personId, messageId);
    }

    @PostMapping("/person/{personId}/message")
    public ResponseEntity<Message> addMessageToPerson(@PathVariable int personId, @RequestBody Message message) {
        Message addedMessage = personService.addMessageToPerson(personId, message);
        return new ResponseEntity<>(addedMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/person/{personId}/message/{messageId}")
    public ResponseEntity<Void> deleteMessageFromPerson(@PathVariable int personId, @PathVariable int messageId) {
        personService.deleteMessageFromPerson(personId, messageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

