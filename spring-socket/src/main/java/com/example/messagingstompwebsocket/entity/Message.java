package com.example.messagingstompwebsocket.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Message {

    @Id @GeneratedValue
    @Column(name = "msg_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private String userName;
    //private User user;

    @Column(name = "room_no")
    private int roomNo;
}
