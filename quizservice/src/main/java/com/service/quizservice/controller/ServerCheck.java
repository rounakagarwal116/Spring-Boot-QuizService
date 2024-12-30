package com.service.quizservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerCheck {
    @GetMapping()
    public String getAllQuestions() {
        return "Server Working";
    }

}
