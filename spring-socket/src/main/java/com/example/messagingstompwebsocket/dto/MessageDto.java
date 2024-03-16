package com.example.messagingstompwebsocket.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageDto {
    private String content;
    private String userName;
    private int roomNo;
}
