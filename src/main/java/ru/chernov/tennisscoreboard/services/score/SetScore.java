package ru.chernov.tennisscoreboard.services.score;

public class SetScore extends Score<Integer>{
    private GameScore<?> currentGameScore;

    public SetScore() {
        this.currentGameScore = new RegularGameScore();
    }

    public void setCurrentGameScore(GameScore<?> currentGameScore) {
        this.currentGameScore = currentGameScore;
    }

    public GameScore<?> getCurrentGameScore() {
        return currentGameScore;
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public MatchState winCalculation(int pointWinnerNumber) {
        MatchState currentGameState = currentGameScore.winCalculation(pointWinnerNumber);

        if(currentGameState == MatchState.PLAYER_ONE_WON || currentGameState == MatchState.PLAYER_TWO_WON) {
            return gameWon(pointWinnerNumber);
        }

        return MatchState.ONGOING;
    }

    private MatchState gameWon(int pointWinnerNumber) {
        Integer playerGameScore = getPlayerScore(pointWinnerNumber);
        playerGameScore++;
        setPlayerScore(playerGameScore, pointWinnerNumber);
        currentGameScore = new RegularGameScore();

        if(playerGameScore > 5) {
            Integer opponentGameScore = getOpponentScore(pointWinnerNumber);

            if(playerGameScore - opponentGameScore > 1) {
                return MatchState.getWonStateByPlayerId(pointWinnerNumber);
            }

            if(playerGameScore == 6 && opponentGameScore == 6) {
                currentGameScore = new TieBreakGameScore();
                return MatchState.ONGOING;
            }

            if(playerGameScore == 7 && opponentGameScore == 6) {
                return MatchState.getWonStateByPlayerId(pointWinnerNumber);
            }
        }

        return MatchState.ONGOING;
    }
}
