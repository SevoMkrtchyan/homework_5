package com.example.poll.manager;

import com.example.poll.db.DBConnectionProvider;
import com.example.poll.model.Poll;
import com.example.poll.model.Question;
import com.example.poll.model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PollManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    private ResultManager resultManager = new ResultManager();
    private QuestionManager questionManager = new QuestionManager();

    public List<Poll> findAll() {
        List<Poll> polls = new ArrayList<>();
        String sql = "SELECT * FROM poll";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Poll pollFromResultSet = getPollFromResultSet(resultSet);
                polls.add(pollFromResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return polls;
    }

    public Poll findById(int id) {
        String sql = "SELECT * FROM poll where id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getPollFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Poll getPollFromResultSet(ResultSet resultSet) {
        try {
            Poll poll = new Poll();
            poll.setId(resultSet.getInt(1));
            poll.setName(resultSet.getString(2));
            poll.setDescription(resultSet.getString(3));
            List<Result> results = resultManager.findByPollId(poll.getId());
            if (results != null) {
                poll.setResults(results);
            }
            List<Question> questions = questionManager.findByPollId(poll.getId());
            if (questions != null) {
                poll.setQuestions(questions);
            }
            return poll;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
