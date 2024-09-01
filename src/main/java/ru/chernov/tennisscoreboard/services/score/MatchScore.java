package ru.chernov.tennisscoreboard.services.score;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MatchScore extends Score<Integer>{
    public final static int DEFAULT_COUNT_SETS = 2;

    private SetScore currentSetScore;
    private int countSetsForWin;

    private final Map<Integer, List<Integer>> setsResults;

    public MatchScore(int countSetsForWin) {
        this.currentSetScore = new SetScore();
        this.countSetsForWin = countSetsForWin;

        setsResults = new HashMap<>();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public MatchState winCalculation(int pointWinnerNumber) {
        MatchState currentSetState = currentSetScore.winCalculation(pointWinnerNumber);

        if(currentSetState == MatchState.PLAYER_ONE_WON || currentSetState == MatchState.PLAYER_TWO_WON) {
            return setWon(pointWinnerNumber);
        }

        return MatchState.ONGOING;
    }

    private MatchState setWon(int pointWinnerNumber) {
        setPlayerScore(getPlayerScore(pointWinnerNumber) + 1, pointWinnerNumber);
        Integer playerScore = getPlayerScore(pointWinnerNumber);

        List<Integer> score = new ArrayList<>();
        score.add(currentSetScore.getPlayerScore(0));
        score.add(currentSetScore.getPlayerScore(1));

        setsResults.put(getPlayerScore(0) + getPlayerScore(1), score);

        if(playerScore == countSetsForWin) {
            return MatchState.getWonStateByPlayerId(pointWinnerNumber);
        }

        currentSetScore = new SetScore();
        return MatchState.ONGOING;
    }

    public Integer getSetResults(int setNumber, int playerNumber){
        try {
            return setsResults.get(setNumber).get(playerNumber);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public String getCurrentGameScore(int playerNumber) {
        if(currentSetScore.getCurrentGameScore().getPlayerScore(playerNumber) instanceof RegularGamePoints) {
            return ((RegularGamePoints) currentSetScore.getCurrentGameScore().getPlayerScore(playerNumber)).getCode();
        }

        return currentSetScore.getCurrentGameScore().getPlayerScore(playerNumber).toString();
    }
}
