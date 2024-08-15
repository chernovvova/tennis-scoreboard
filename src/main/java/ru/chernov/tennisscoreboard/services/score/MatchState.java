package ru.chernov.tennisscoreboard.services.score;

public enum MatchState {
    ONGOING,
    PLAYER_ONE_WON,
    PLAYER_TWO_WON;

    public static MatchState getWonStateByPlayerId(int playerId) {
        if(playerId == 0) {
            return PLAYER_ONE_WON;
        }
        return PLAYER_TWO_WON;
    }
}
