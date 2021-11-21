package com.example.poll.service.impl;

import com.example.poll.manager.ResultManager;
import com.example.poll.model.Result;
import com.example.poll.service.ResultDao;

import java.util.List;

public class ResultDaoImpl implements ResultDao {

    private final ResultManager resultManager = new ResultManager();

    @Override
    public List<Result> findAll() {
        return resultManager.findAll();
    }

    @Override
    public Result findById(int id) {
        return resultManager.findById(id);
    }

    @Override
    public List<Result> findByPollId(int pollId) {
        return resultManager.findByPollId(pollId);
    }

    @Override
    public Result findByScore(int pollId, int score) {
        return resultManager.findByScore(pollId, score);
    }
}