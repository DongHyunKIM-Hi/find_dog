package com.example.find_dog.service;


import com.example.find_dog.model.chat.ChatRoom;
import com.example.find_dog.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;


    public ChatRoom createChatRoom(String user){
        ChatRoom chatRoom = ChatRoom.create(user);
        return chatRoom;
    }
}
