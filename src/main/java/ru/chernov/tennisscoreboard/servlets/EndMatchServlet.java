package ru.chernov.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.chernov.tennisscoreboard.services.OngoingMatchService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "EndMatchServlet", urlPatterns = "/end-match")
public class EndMatchServlet extends HttpServlet {
    private final OngoingMatchService ongoingMatchService = new OngoingMatchService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        ongoingMatchService.removeMatch(uuid);
        resp.sendRedirect("index.jsp");
    }
}
