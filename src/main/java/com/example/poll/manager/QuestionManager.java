package com.example.poll.manager;

import com.example.poll.db.DBConnectionProvider;
import com.example.poll.model.Answer;
import com.example.poll.model.Question;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class QuestionManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final AnswerManager answerManager = new AnswerManager();

    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question questionFromResultSet = getQuestionFromResultSet(resultSet);
                questions.add(questionFromResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public Question findById(int id) {
        String sql = "SELECT * FROM question where id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getQuestionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Question> findByPollId(int id) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question where poll_id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                questions.add(getQuestionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private Question getQuestionFromResultSet(ResultSet resultSet) {
        try {
            Question question = new Question();
            question.setId(resultSet.getInt(1));
            question.setText(resultSet.getString(2));
            List<Answer> byQuestionId = answerManager.findByQuestionId(question.getId());
            if (!byQuestionId.isEmpty()) {
                question.setAnswers(byQuestionId);
            }
            return question;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
