package ru.chernov.tennisscoreboard.services.score;

public class RegularGameScore extends GameScore<RegularGamePoints>{
    @Override
    protected RegularGamePoints getZeroScore() {
        return RegularGamePoints.ZERO;
    }

    @Override
    public MatchState winCalculation(int pointWinnerNumber) {
        RegularGamePoints playerScore = getPlayerScore(pointWinnerNumber);

        if(playerScore.ordinal() <= RegularGamePoints.THIRTY.ordinal()) {
            setPlayerScore(playerScore.next(), pointWinnerNumber);
        }
        else if (playerScore == RegularGamePoints.FORTY) {
            RegularGamePoints opponentScore = getOpponentScore(pointWinnerNumber);
            if(opponentScore == RegularGamePoints.ADVANTAGE) {
                setOpponentScore(RegularGamePoints.FORTY, pointWinnerNumber);
            }
            else if (opponentScore == RegularGamePoints.FORTY) {
                setPlayerScore(RegularGamePoints.FIFTEEN, pointWinnerNumber);
            }
            else {
                return MatchState.getWonStateByPlayerId(pointWinnerNumber);
            }
        }
        else if (playerScore == RegularGamePoints.ADVANTAGE) {
            return MatchState.getWonStateByPlayerId(pointWinnerNumber);
        }

        return MatchState.ONGOING;
    }
}
