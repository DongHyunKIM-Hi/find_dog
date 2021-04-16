package com.example.find_dog.model.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK,DEV,SHOW
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String link;
}
