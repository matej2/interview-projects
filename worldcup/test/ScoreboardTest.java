import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    private Scoreboard scoreboard;
    String teamOne = "Team One";
    String teamTwo = "Team Two";

    @BeforeEach
    public void setup() {
        scoreboard = new Scoreboard();
    }

    @Test
    void testStartMatch() {
        // Scenario 1: Valid input
        initScoreboard();

        // Scenario 2: Match already exists
        assertThrows(RuntimeException.class, this::initScoreboard);
    }

    @Test
    void testGetMatchIndexValidInput() {
        initScoreboard();

        String foundMatch = scoreboard.getMatchIndex(teamOne, teamTwo);

        assertNotNull(foundMatch);
    }

    @Test
    void testUpdateMatchValidInput() {
        initScoreboard();

        scoreboard.updateMatch(teamOne, 1, teamTwo, 0);

        Match updatedMatch = getScoreboard();
        assertEquals(1, updatedMatch.getTeamOneScore());
        assertEquals(0, updatedMatch.getTeamTwoScore());
    }


    @Test
    void testUpdateMatchMatchDoesNotExist() {
        assertThrows(RuntimeException.class, () -> scoreboard.updateMatch(teamOne, 1, teamTwo, 0));
    }

    @Test
    void testGetOrderedScoreboard() {
        initScoreboard();
        scoreboard.startMatch("C", "D");
        scoreboard.updateMatch("C", 0, "D", 2);

        scoreboard.startMatch("E", "F");
        scoreboard.updateMatch("E", 1, "F", 0);

        List<Match> list = scoreboard.getOrderedScoreboard();

        assertEquals(3, list.size());
        assertEquals(0, list.get(0).getTotalScore());
        assertEquals(1, list.get(1).getTotalScore());
        assertEquals(2, list.get(2).getTotalScore());
    }

    private void initScoreboard() {
        scoreboard.startMatch(teamOne, teamTwo);
    }

    private Match getScoreboard() {
        String existingMatchIndex = scoreboard.getMatchIndex(teamOne, teamTwo);
        return scoreboard.getMatchList().get(existingMatchIndex);
    }
}