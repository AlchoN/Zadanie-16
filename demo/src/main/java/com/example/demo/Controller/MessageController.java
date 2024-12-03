package com.example.demo.Controller;

import com.example.demo.dto.Message;
import com.example.demo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/message")
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    @GetMapping("/message/{id}")
    public Message getMessageById(@PathVariable int id){
        return messageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        message.id = messageRepository.findAll().stream().mapToInt(msg -> msg.id).max().orElse(0) + 1;
        Message addedMessage = messageRepository.save(message);
        return new ResponseEntity<>(addedMessage, HttpStatus.CREATED);
    }

    @PutMapping("/message/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        Optional<Message> existingMessage = messageRepository.findById(id);
        if (existingMessage.isPresent()) {
            return messageRepository.save(message);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        messageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
