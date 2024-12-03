package com.example.demo.dto;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthday")
    private String birthday;


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Message> messages;

    public int getId() {
        return id;
        }
   public void setId(int id) {
        this.id = id; 
       }
  

   public String getFirstname() {
       return firstname;
   }

   public void setFirstname(String firstname) {
       this.firstname = firstname;
   }

   public String getSurname() {
       return surname;
   }

   public void setSurname(String surname) {
       this.surname = surname;
   }

   public String getLastname() {
       return lastname;
   }

   public void setLastname(String lastname) {
       this.lastname = lastname;
   }

   public String getBirthday() {
       return birthday;
   }

   public void setBirthday(String birthday) {
       this.birthday = birthday;
   }

    public Person() {}

    public Person(int id, String firstname, String surname, String lastname, String birthday) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
