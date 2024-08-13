package ru.chernov.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.services.MatchGeneratorService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "NewMatchServlet", value = "/tennis_scoreboard/new_match")
public class NewMatchServlet extends HttpServlet {
    MatchGeneratorService matchGenerator = new MatchGeneratorService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.html").forward(req, resp);
        resp.setStatus(200);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");

        if(player1Name == null || player2Name == null
        || player1Name.isEmpty() || player2Name.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong parameters");
        }
        if(player1Name.equals(player2Name)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Player cannot play with himself");
        }

        UUID newId = matchGenerator.generateMatch(player1Name, player2Name);
        String url = String.format("/tennis_scoreboard/match-score?uuid=$" + newId.toString());
        resp.sendRedirect(url);
    }
}
