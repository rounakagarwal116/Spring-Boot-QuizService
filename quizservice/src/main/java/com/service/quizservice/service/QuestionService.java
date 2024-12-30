package com.service.quizservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.quizservice.model.QuestionModel;
import com.service.quizservice.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
    }

    public List<QuestionModel> getByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(QuestionModel questionModel) {
        try {
            if (questionModel.getRightAnswer().isEmpty() == false)
                questionRepository.save(questionModel);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Occured " + e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

}
