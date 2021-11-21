package com.example.poll.servlet;

import com.example.poll.model.Poll;
import com.example.poll.service.impl.PollDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    private final PollDaoImpl pollDao = new PollDaoImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Poll> all = pollDao.findAll();
        request.setAttribute("all", all);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

}