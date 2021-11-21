package com.example.poll.servlet;

import com.example.poll.model.Answer;
import com.example.poll.model.Poll;
import com.example.poll.model.Question;
import com.example.poll.service.AnswerDao;
import com.example.poll.service.QuestionDao;
import com.example.poll.service.impl.AnswerDaoImpl;
import com.example.poll.service.impl.PollDaoImpl;
import com.example.poll.service.impl.QuestionDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/questions")
public class QuestionsServlet extends HttpServlet {

    private final QuestionDao questionDao = new QuestionDaoImpl();
    private final AnswerDao answerDao = new AnswerDaoImpl();
    private final PollDaoImpl pollDao = new PollDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int pollId = Integer.parseInt(id);
        Poll poll = pollDao.findById(pollId);
        if (poll != null) {
            List<Question> byPollId = questionDao.findByPollId(pollId);
            byPollId.forEach(e -> e.setAnswers(answerDao.findByQuestionId(e.getId())));
            req.setAttribute("poll", poll);
            req.setAttribute("questions", byPollId);
            req.getRequestDispatcher("question.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Requested Poll wasn't found, please try again");
            resp.sendRedirect("/");
        }
    }
}
