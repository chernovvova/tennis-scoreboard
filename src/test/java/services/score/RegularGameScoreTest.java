package services.score;

import org.junit.jupiter.api.Test;
import ru.chernov.tennisscoreboard.services.score.MatchState;
import ru.chernov.tennisscoreboard.services.score.RegularGameScore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularGameScoreTest {

    @Test
    public void TestFourGoalsWin() {
        RegularGameScore regularGameScore = new RegularGameScore();

        for(int i = 0; i < 3; i++) {
            assertEquals(MatchState.ONGOING, regularGameScore.winCalculation(0));
        }

        assertEquals(MatchState.PLAYER_ONE_WON, regularGameScore.winCalculation(0));
    }
}
