package ru.chernov.tennisscoreboard.services.score;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    private final List<T> score = new ArrayList<>();

    protected abstract T getZeroScore();

    public Score() {
        score.add(getZeroScore());
        score.add(getZeroScore());
    }

    public int getOpponentNumber(int playerNumber) {
        return playerNumber == 1 ? 0 : 1;
    }

    public T getPlayerScore(int playerNumber) {
        return score.get(playerNumber);
    }

    public T getOpponentScore(int playerNumber) {
        int opponentNumber = playerNumber == 0 ? 1 : 0;
        return score.get(opponentNumber);
    }

    public void setPlayerScore(T newScore, int playerNumber) {
        score.set(playerNumber, newScore);
    }

    public void setOpponentScore(T newScore, int playerNumber) {
        int opponentNumber = playerNumber == 0 ? 1 : 0;
        score.set(opponentNumber, newScore);
    }

    public abstract MatchState winCalculation(int pointWinnerNumber);
}
