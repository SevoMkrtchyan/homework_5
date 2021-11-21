package com.example.poll.servlet;

import com.example.poll.model.Question;
import com.example.poll.model.Result;
import com.example.poll.service.QuestionDao;
import com.example.poll.service.ResultDao;
import com.example.poll.service.impl.QuestionDaoImpl;
import com.example.poll.service.impl.ResultDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/answers")
public class AnswerServlet extends HttpServlet {

    private final QuestionDao questionDao = new QuestionDaoImpl();
    private final ResultDao resultDao = new ResultDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("pollId");
        int pollId = Integer.parseInt(id);
        List<Question> questions = questionDao.findAll();
        int tmp = 0, score;
        for (Question question : questions) {
            String weight = req.getParameter("question" + question.getId());
            tmp += Integer.parseInt(weight);
        }
        score = tmp / questions.size();
        Result byScore = resultDao.findByScore(pollId, score);
        req.setAttribute("result", byScore);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

}
