package ru.chernov.tennisscoreboard.services;

import ru.chernov.tennisscoreboard.models.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchService {
    private static Map<UUID, Match> matches = new ConcurrentHashMap<>();

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
