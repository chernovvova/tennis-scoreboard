package services.score;

import org.junit.jupiter.api.Test;
import ru.chernov.tennisscoreboard.services.score.MatchState;
import ru.chernov.tennisscoreboard.services.score.TieBreakGameScore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TieBreakGameScoreTest {

    @Test
    public void TestWinOnSevenPoints() {
        TieBreakGameScore tieBreakGameScore = new TieBreakGameScore();

        for (int i = 0; i < 5; i++) {
            tieBreakGameScore.winCalculation(0);
            tieBreakGameScore.winCalculation(1);
        }

        assertEquals(MatchState.ONGOING, tieBreakGameScore.winCalculation(0));
        assertEquals(MatchState.PLAYER_ONE_WON, tieBreakGameScore.winCalculation(0));
    }

    @Test
    public void TestSixSevenScore() {
        TieBreakGameScore tieBreakGameScore = new TieBreakGameScore();

        for (int i = 0; i < 6; i++) {
            tieBreakGameScore.winCalculation(0);
            tieBreakGameScore.winCalculation(1);
        }

        assertEquals(MatchState.ONGOING, tieBreakGameScore.winCalculation(0));
    }

    @Test
    public void TestAdvantageOnTwoPointsWin() {
        TieBreakGameScore tieBreakGameScore = new TieBreakGameScore();

        for (int i = 0; i < 7; i++) {
            tieBreakGameScore.winCalculation(0);
            tieBreakGameScore.winCalculation(1);
        }

        tieBreakGameScore.winCalculation(0);
        assertEquals(MatchState.PLAYER_ONE_WON, tieBreakGameScore.winCalculation(0));
    }
}
