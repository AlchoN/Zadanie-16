package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}

