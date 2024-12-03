package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
