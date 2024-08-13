package ru.chernov.tennisscoreboard.services;

import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.models.Player;

import java.util.UUID;

public class MatchGeneratorService {
    OngoingMatchService ongoingMatchService = new OngoingMatchService();

    public UUID generateMatch(String player1Name, String player2Name) {
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        Match match = new Match(player1, player2);

        UUID uuid = UUID.randomUUID();
        ongoingMatchService.addMatch(uuid, match);

        return uuid;
    }
}
