package com.example.poll.service;

import com.example.poll.model.Question;

import java.util.List;

public interface QuestionDao extends Dao<Question> {

    List<Question> findByPollId(int pollId);
}