package ru.chernov.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.repositories.MatchRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchesServlet", urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {
    private final MatchRepository matchRepository = new MatchRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterName = req.getParameter("filter_by_player_name");
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }
        long pageSize = 5;

        long totalItems = 0;
        List<Match> matches;
        if (filterName == null || filterName.trim().isEmpty()) {
            matches = matchRepository.getAllPagination((int) pageSize, (int) (page * pageSize));
            totalItems = matchRepository.getAllUnique();
        } else {
            matches = matchRepository.getByPlayerNamePagination(filterName, (int) pageSize, (int) (page * pageSize));
            totalItems = matchRepository.getByPlayerNameUnique(filterName);
        }

        long totalPages = (totalItems / pageSize) + 1;

        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("matches", matches);
        req.setAttribute("totalItems", totalItems);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
