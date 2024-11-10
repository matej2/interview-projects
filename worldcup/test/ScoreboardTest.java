import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    private Scoreboard match;
    String teamOne = "Team One";
    String teamTwo = "Team Two";

    @BeforeEach
    public void setup() {
        match = new Scoreboard();
    }

    @Test
    void testStartMatch() {
        // Scenario 1: Valid input
        match.startMatch("A", "B");

        // Scenario 2: Match already exists
        assertThrows(RuntimeException.class, () -> match.startMatch("A", "B"));
    }

    @Test
    void testGetMatchIndexValidInput() {
        match.startMatch(teamOne, teamTwo);

        String foundMatch = match.getMatchIndex(teamOne, teamTwo);

        assertNotNull(foundMatch);
    }

    @Test
    void testUpdateMatchValidInput() {
        match.startMatch(teamOne, teamTwo);

        match.updateMatch(teamOne, 1, teamTwo, 0);

        Match updatedMatch = getMatch();
        assertEquals(1, updatedMatch.getTeamOneScore());
        assertEquals(0, updatedMatch.getTeamTwoScore());
    }


    @Test
    void testUpdateMatchMatchDoesNotExist() {
        assertThrows(RuntimeException.class, () -> match.updateMatch(teamOne, 1, teamTwo, 0));
    }

    private Match getMatch() {
        String existingMatchIndex = match.getMatchIndex(teamOne, teamTwo);
        return match.getMatchList().get(existingMatchIndex);
    }
}