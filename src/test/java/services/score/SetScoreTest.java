package services.score;

import org.junit.jupiter.api.Test;
import ru.chernov.tennisscoreboard.services.score.MatchState;
import ru.chernov.tennisscoreboard.services.score.SetScore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetScoreTest {

    @Test
    public void TestWinAfterTwentyFourPoints() {
        SetScore setScore = new SetScore();

        for(int j = 0; j < 23; j++) {
            assertEquals(MatchState.ONGOING, setScore.winCalculation(0));
        }

        assertEquals(MatchState.PLAYER_ONE_WON, setScore.winCalculation(0));
    }
}
