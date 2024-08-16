package services.score;

import org.junit.jupiter.api.Test;
import ru.chernov.tennisscoreboard.services.score.MatchScore;
import ru.chernov.tennisscoreboard.services.score.MatchState;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchScoreTest {
    private final int COUNT_SETS_TO_WIN = 2;

    @Test
    public void TestFortyEightPointsWin() {
        MatchScore matchScore = new MatchScore(COUNT_SETS_TO_WIN);

        for(int i = 0; i < 47; i++) {
            matchScore.winCalculation(0);
        }

        assertEquals(MatchState.PLAYER_ONE_WON, matchScore.winCalculation(0));
    }

    @Test
    public void TestTwoOneScoreWin() {
        MatchScore matchScore = new MatchScore(COUNT_SETS_TO_WIN);

        for(int i = 0; i < 24; i++) {
            matchScore.winCalculation(0);
        }

        for(int i = 0; i < 23; i++) {
            matchScore.winCalculation(1);
        }

        assertEquals(MatchState.ONGOING, matchScore.winCalculation(1));

        for(int i = 0; i < 23; i++) {
            matchScore.winCalculation(0);
        }

        assertEquals(MatchState.PLAYER_ONE_WON, matchScore.winCalculation(0));
    }
}
