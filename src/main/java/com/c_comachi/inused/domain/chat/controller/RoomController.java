package com.c_comachi.inused.domain.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class RoomController {

    @GetMapping("")
    public String login(){
        return "login";
    }
    @GetMapping("room")
    public String rooms(Model model) {
        return "chat/room";
    }

    @GetMapping("room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable(value = "roomId") String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

}
