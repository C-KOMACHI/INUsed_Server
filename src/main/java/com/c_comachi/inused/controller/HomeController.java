package com.c_comachi.inused.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        log.info("메인 페이지");
        return "index";
    }

    @GetMapping("/registration")
    public String registartion() {
        log.info("회원가입");
        return "registration";
    }

}
