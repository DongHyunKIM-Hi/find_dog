package com.example.find_dog.controller;

import com.example.find_dog.model.chat.ChatRoom;
import com.example.find_dog.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;


    // chatroom을 생성하는 페이지  여기서 사용자 이름 설정  차후에 토큰에 있는 이름뽑아서 넣어줄 예졍
    @GetMapping("/make")
    public String rooms() {
        return "/chat/room";
    }

    @GetMapping("/test")
    public String test(){
        return "test123123";
    }

    //chatroom 생성후 chatroom id 반환

    @PostMapping("/room")
    @ResponseBody
    public String createRoom(@RequestParam String user){
        ChatRoom myroom = chatService.createChatRoom(user);
        return myroom.getRoomId();
    }

    // chatroom 입장
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId){
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

}
