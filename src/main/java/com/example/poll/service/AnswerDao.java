package com.example.poll.service;

import com.example.poll.model.Answer;

import java.util.List;

public interface AnswerDao extends Dao<Answer> {

    List<Answer> findByQuestionId(int questionId);
}