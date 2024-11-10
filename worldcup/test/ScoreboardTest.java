import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        scoreboard.startMatch("A", "B");

        // Scenario 2: Match already exists
        assertThrows(RuntimeException.class, () -> scoreboard.startMatch("A", "B"));
    }

    @Test
    void testGetMatchIndexValidInput() {
        scoreboard.startMatch(teamOne, teamTwo);

        String foundMatch = scoreboard.getMatchIndex(teamOne, teamTwo);

        assertNotNull(foundMatch);
    }

    @Test
    void testUpdateMatchValidInput() {
        scoreboard.startMatch(teamOne, teamTwo);

        scoreboard.updateMatch(teamOne, 1, teamTwo, 0);

        Match updatedMatch = getScoreboard();
        assertEquals(1, updatedMatch.getTeamOneScore());
        assertEquals(0, updatedMatch.getTeamTwoScore());
    }


    @Test
    void testUpdateMatchMatchDoesNotExist() {
        assertThrows(RuntimeException.class, () -> scoreboard.updateMatch(teamOne, 1, teamTwo, 0));
    }

    private Match getScoreboard() {
        String existingMatchIndex = scoreboard.getMatchIndex(teamOne, teamTwo);
        return scoreboard.getMatchList().get(existingMatchIndex);
    }
}