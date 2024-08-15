package services.score;

import org.junit.jupiter.api.Test;
import ru.chernov.tennisscoreboard.services.score.MatchState;
import ru.chernov.tennisscoreboard.services.score.RegularGameScore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularGameScoreTest {


    /*first player wins three points and does not win the game,
    after winning the fourth point the game ends with the result PLAYER_ONE_WON*/
    @Test
    public void TestFourGoalsWin() {
        RegularGameScore regularGameScore = new RegularGameScore();

        for(int i = 0; i < 3; i++) {
            assertEquals(MatchState.ONGOING, regularGameScore.winCalculation(0));
        }

        assertEquals(MatchState.PLAYER_ONE_WON, regularGameScore.winCalculation(0));
    }

    // the game doesn't end on a goal after forty-forty score
    @Test
    public void TestFortyFortySituation() {
        RegularGameScore regularGameScore = new RegularGameScore();

        for(int i = 0; i < 3; i++) {
            assertEquals(MatchState.ONGOING, regularGameScore.winCalculation(0));
            assertEquals(MatchState.ONGOING, regularGameScore.winCalculation(1));
        }

        assertEquals(MatchState.ONGOING, regularGameScore.winCalculation(0));
    }
}
