package com.example.poll.service.impl;

import com.example.poll.manager.QuestionManager;
import com.example.poll.model.Question;
import com.example.poll.service.QuestionDao;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private final QuestionManager questionManager = new QuestionManager();

    @Override
    public List<Question> findAll() {
        return questionManager.findAll();
    }

    @Override
    public Question findById(int id) {
        return questionManager.findById(id);
    }

    @Override
    public List<Question> findByPollId(int pollId) {
        return questionManager.findByPollId(pollId);
    }
}