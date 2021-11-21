package com.example.poll.manager;

import com.example.poll.db.DBConnectionProvider;
import com.example.poll.model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<Result> findAll() {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM result";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getResultFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Result findById(long id) {
        String sql = "SELECT * FROM result where id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getResultFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Result> findByPollId(int id) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM result where poll_id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(getResultFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Result findByScore(int pollId, int score) {
        String sql = "SELECT * FROM result where poll_id=? and min_score <=? and max_score>=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pollId);
            statement.setInt(2, score);
            statement.setInt(3, score);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getResultFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Result getResultFromResultSet(ResultSet resultSet) {
        try {
            Result result = new Result();
            result.setId(resultSet.getInt(1));
            result.setExplanation(resultSet.getString(2));
            result.setMinScore(resultSet.getInt(3));
            result.setMaxScore(resultSet.getInt(4));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
