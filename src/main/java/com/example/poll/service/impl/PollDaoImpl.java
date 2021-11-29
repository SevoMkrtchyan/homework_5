package com.example.poll.service.impl;

import com.example.poll.manager.PollManager;
import com.example.poll.model.Poll;
import com.example.poll.service.Dao;

import java.util.List;

public class PollDaoImpl implements Dao<Poll> {

    private final PollManager pollManager = new PollManager();

    @Override
    public List<Poll> findAll() {
        return pollManager.findAll();
    }

    @Override
    public Poll findById(int id) {
        return pollManager.findById(id);
    }
}