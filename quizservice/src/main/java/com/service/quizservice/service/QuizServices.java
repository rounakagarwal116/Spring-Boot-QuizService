package com.service.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.quizservice.model.*;
import com.service.quizservice.repository.QuestionRepository;
import com.service.quizservice.repository.QuizRepository;

@Service
public class QuizServices {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String description) {
        List<QuestionModel> list = questionRepository.findRandomByCategory(category, numQ);
        QuizModel quiz = new QuizModel();

        quiz.setDescription(description);
        quiz.setQuestions(list);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Succes", HttpStatus.CREATED);
    }

    public ResponseEntity<List<UserQuestion>> getQuizById(Integer id) {
        Optional<QuizModel> quiz = quizRepository.findById(id);
        List<QuestionModel> questionList = quiz.get().getQuestions();
        List<UserQuestion> responseList = new ArrayList<>();
        for (QuestionModel q : questionList) {
            UserQuestion userQuestion = new UserQuestion(q.getId(), q.getQuestionTitle(), q.getOption1(),
                    q.getOption2(), q.getOption3(), q.getOption4());
            responseList.add(userQuestion);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    public ResponseEntity<String> calculate(Integer id, List<Response> responseList) {
        QuizModel quizModel = quizRepository.findById(id).get();
        List<QuestionModel> qList = quizModel.getQuestions();
        int score = 0;
        int i = 0;
        for (Response response : responseList) {
            if (response.getResponse().equalsIgnoreCase(qList.get(i).getRightAnswer()))
                score++;
            i++;
        }
        String result = score + "/" + responseList.size();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
