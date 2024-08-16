package ru.chernov.tennisscoreboard.services.score;

public class MatchScore extends Score<Integer>{
    private SetScore currentSetScore;
    private int countSetsForWin;

    public MatchScore(int countSetsForWin) {
        this.currentSetScore = new SetScore();
        this.countSetsForWin = countSetsForWin;
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
        Integer playerScore = getPlayerScore(pointWinnerNumber);
        playerScore++;
        setPlayerScore(playerScore, pointWinnerNumber);

        if(playerScore == countSetsForWin) {
            return MatchState.getWonStateByPlayerId(pointWinnerNumber);
        }

        currentSetScore = new SetScore();
        return MatchState.ONGOING;
    }
}
