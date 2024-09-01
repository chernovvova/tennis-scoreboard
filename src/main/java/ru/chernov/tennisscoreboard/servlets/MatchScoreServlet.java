package ru.chernov.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.models.Player;
import ru.chernov.tennisscoreboard.services.OngoingMatchService;
import ru.chernov.tennisscoreboard.services.SaveMatchService;
import ru.chernov.tennisscoreboard.services.score.MatchState;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchService ongoingMatchService = new OngoingMatchService();
    private final SaveMatchService matchSaveService = new SaveMatchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = ongoingMatchService.getMatch(uuid);
        Player p = match.getWinner();
        System.out.println(p);
        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = ongoingMatchService.getMatch(uuid);

        boolean isPlayer1Won = req.getParameter("player-1") == null ? false : true;
        boolean isPlayer2Won = req.getParameter("player-2") == null ? false : true;

        if(isPlayer1Won) {
            if(match.getMatchScore().winCalculation(0) == MatchState.PLAYER_ONE_WON) {
                match.setWinner(match.getPlayer1());
                matchSaveService.saveMatch(match);
            }
        }
        else {
            if(match.getMatchScore().winCalculation(1) == MatchState.PLAYER_TWO_WON) {
                match.setWinner(match.getPlayer2());
                matchSaveService.saveMatch(match);
            }
        }

        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}
