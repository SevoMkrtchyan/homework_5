package com.example.poll.manager;

import com.example.poll.db.DBConnectionProvider;
import com.example.poll.model.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<Answer> findAll() {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT * FROM answer";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(getAnswerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public Answer findById(int id) {
        String sql = "SELECT * FROM answer where id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAnswerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Answer> findByQuestionId(int questionId) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT * FROM answer where question_id=" + questionId;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(getAnswerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private Answer getAnswerFromResultSet(ResultSet resultSet) {
        try {
            Answer answer = new Answer();
            answer.setId(resultSet.getInt(1));
            answer.setText(resultSet.getString(2));
            answer.setWeight(resultSet.getInt(3));
            return answer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
