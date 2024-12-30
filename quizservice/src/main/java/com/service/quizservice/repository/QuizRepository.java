package com.service.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.quizservice.model.QuizModel;

public interface QuizRepository  extends JpaRepository<QuizModel,Integer>{

    
} 