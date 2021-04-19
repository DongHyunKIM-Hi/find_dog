package com.example.find_dog.controller;

import com.example.find_dog.model.chat.ChatMessage;
import com.example.find_dog.repository.AdoptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final AdoptionRepository adoptionRepository;


    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage("새로운 가족을 기다리는 아이들이 궁금하신가요?");
            message.setSender("발견일동");
            message.setType(ChatMessage.MessageType.TALK);
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
            message.setMessage("이걸 만든 사람들은 누군가요?");
            message.setSender("발견일동");
            message.setType(ChatMessage.MessageType.TALK);
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
            message.setMessage("아이들을 도와주고 싶은데 후원을 할 수 있을까요?");
            message.setSender("발견일동");
            message.setType(ChatMessage.MessageType.TALK);
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        }

        else{
            message.setSender("발견일동");
            switch (message.getMessage()){
                case "새로운 가족을 기다리는 아이들이 궁금하신가요?":
                    message.setMessage(adoptionRepository.findAll().size()+ "마리의 아이들이 기다리고 있습니다(클릭시 이동)");
                    message.setType(ChatMessage.MessageType.DEV);
                    message.setLink("https://dmanimal.co.kr/adoption");
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
                    break;
                case "이걸 만든 사람들은 누군가요?":
                    message.setMessage("김동현(Viva)");
                    message.setSender("나는 백엔드 개발자요!");
                    message.setType(ChatMessage.MessageType.DEV);
                    message.setLink("https://github.com/DongHyunKIM-Hi");
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
                    message.setMessage("강미진(cona)");
                    message.setSender("나도 백엔드 개발자요!!");
                    message.setLink("https://github.com/conagreen");
                    message.setType(ChatMessage.MessageType.DEV);
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
                    message.setMessage("김형민(hmk1022)");
                    message.setSender("내가 바로 프론트의 신");
                    message.setLink("https://github.com/rlagudals95");
                    message.setType(ChatMessage.MessageType.DEV);
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
                    message.setMessage("임다희(DaHee200)");
                    message.setSender("최신형 노트북 보유");
                    message.setLink("https://github.com/DaHee200");
                    message.setType(ChatMessage.MessageType.DEV);
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
                    message.setMessage("심현인(holasim91)");
                    message.setSender("비숑 마스터");
                    message.setLink("https://github.com/holasim91");
                    message.setType(ChatMessage.MessageType.DEV);
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
                    break;
                case "아이들을 도와주고 싶은데 후원을 할 수 있을까요?":
                    message.setMessage("우리은행 1002 -xxx-xxx-xx");
                    message.setLink("https://www.wooribank.com/");
                    message.setType(ChatMessage.MessageType.DEV);
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
                    break;
                default:
                    message.setMessage("알수없는 오류가... ㄷㄷ");
                    message.setSender("난 버그다옹");
                    message.setType(ChatMessage.MessageType.TALK);
                    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
            }
            message.setMessage("돌아가기");
            message.setSender("발견일동");
            message.setType(ChatMessage.MessageType.ENTER);
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        }

    }
}