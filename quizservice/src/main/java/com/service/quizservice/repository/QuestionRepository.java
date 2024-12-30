package com.service.quizservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.quizservice.model.QuestionModel;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionModel, Integer> {

    List<QuestionModel> findByCategory(String category);

    @Query(value = "SELECT * FROM Question_Model q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)

    List<QuestionModel> findRandomByCategory(String category, int numQ);

}