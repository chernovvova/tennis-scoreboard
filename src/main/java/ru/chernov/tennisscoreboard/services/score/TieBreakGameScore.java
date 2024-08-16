package ru.chernov.tennisscoreboard.services.score;

public class TieBreakGameScore extends GameScore<Integer>{

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public MatchState winCalculation(int pointWinnerNumber) {
        setPlayerScore(getPlayerScore(pointWinnerNumber) + 1, pointWinnerNumber);
        Integer playerScore = getPlayerScore(pointWinnerNumber);
        Integer opponentScore = getOpponentScore(pointWinnerNumber);

        if(playerScore > 6 && playerScore - opponentScore > 1) {
            return MatchState.getWonStateByPlayerId(pointWinnerNumber);
        }

        return MatchState.ONGOING;
    }
}
