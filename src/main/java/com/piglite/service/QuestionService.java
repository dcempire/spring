package com.piglite.service;

import com.piglite.model.Question;

import java.util.List;

public interface QuestionService {
    void saveQuestion(Question question);

    List<Question> findAllQuestions();

    Question findById(int id);

    List<Question> findByName(String name);

    void updateQuestion(Question question);

    void delById(int id);

}
