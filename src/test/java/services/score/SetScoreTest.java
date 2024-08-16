package services.score;

import org.junit.jupiter.api.Test;
import ru.chernov.tennisscoreboard.services.score.MatchState;
import ru.chernov.tennisscoreboard.services.score.SetScore;

import static org.junit.jupiter.api.Assertions.*;

public class SetScoreTest {

    @Test
    public void TestWinAfterTwentyFourPoints() {
        SetScore setScore = new SetScore();

        for(int j = 0; j < 23; j++) {
            assertEquals(MatchState.ONGOING, setScore.winCalculation(0));
        }

        assertEquals(MatchState.PLAYER_ONE_WON, setScore.winCalculation(0));
    }

    @Test
    public void TestFiveSixScore() {
        SetScore setScore = new SetScore();

        setScore.setPlayerScore(5, 0);
        setScore.setPlayerScore(5, 1);

        for(int i = 0; i < 3; i++) {
            setScore.winCalculation(0);
        }

        assertEquals(MatchState.ONGOING, setScore.winCalculation(0));
    }

    @Test
    public void TestTieBreak() {
        SetScore setScore = new SetScore();

        setScore.setPlayerScore(6,0);
        for (int i = 0; i < 23; i++) {
            setScore.winCalculation(1);
        }

        assertEquals(MatchState.ONGOING, setScore.winCalculation(1));

        for(int i = 0; i < 6; i++) {
            assertEquals(MatchState.ONGOING, setScore.winCalculation(0));
        }

        assertEquals(MatchState.PLAYER_ONE_WON, setScore.winCalculation(0));

    }
}
