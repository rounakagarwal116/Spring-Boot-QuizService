package com.service.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.quizservice.model.Response;
import com.service.quizservice.model.UserQuestion;
import com.service.quizservice.service.QuizServices;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizServices quizservice;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam(required = false) String category, @RequestParam int numQ,
            @RequestParam String description) {
        return quizservice.createQuiz(category, numQ, description);
    }

    @GetMapping("getQuiz/{id}")

    public ResponseEntity<List<UserQuestion>> getQuizById(@PathVariable Integer id) {
        return quizservice.getQuizById(id);
    }

    @PostMapping("submit/{id}")

    public ResponseEntity<String> submit(@PathVariable Integer id, @RequestBody List<Response> response) {
        return quizservice.calculate(id, response);
    }
}
