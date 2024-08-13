package ru.chernov.tennisscoreboard.services;

import ru.chernov.tennisscoreboard.models.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchService {
    private static Map<UUID, Match> matches = new HashMap<>();

    public Match getMatch(UUID id) {
        return matches.get(id);
    }

    public void addMatch(UUID uuid, Match match) {
        matches.put(uuid, match);
    }

    public void removeMatch(UUID id) {
        matches.remove(id);
    }
}
