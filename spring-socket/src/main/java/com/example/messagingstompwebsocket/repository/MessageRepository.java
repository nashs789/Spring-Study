package com.example.messagingstompwebsocket.repository;

import com.example.messagingstompwebsocket.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
