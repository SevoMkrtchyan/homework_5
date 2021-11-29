package com.example.poll.service.impl;

import com.example.poll.manager.AnswerManager;
import com.example.poll.model.Answer;
import com.example.poll.service.AnswerDao;

import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private final AnswerManager answerManager = new AnswerManager();

    @Override
    public List<Answer> findAll() {
        return answerManager.findAll();
    }

    @Override
    public Answer findById(int id) {
        return answerManager.findById(id);
    }

    @Override
    public List<Answer> findByQuestionId(int questionId) {
        return answerManager.findByQuestionId(questionId);
    }
}