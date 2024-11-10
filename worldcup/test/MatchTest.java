import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    Match match;
    String teamOne = "Team One";
    String teamTwo = "Team Two";

    @BeforeEach
    public void setup() {
        match = new Match();
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

        Map<String, Integer> updatedMatch = getMatch();
        assertEquals(1, updatedMatch.get(teamOne));
        assertEquals(0, updatedMatch.get(teamTwo));
    }


    @Test
    void testUpdateMatchMatchDoesNotExist() {
        assertThrows(RuntimeException.class, () -> match.updateMatch(teamOne, 1, teamTwo, 0));
    }

    private Map<String, Integer> getMatch() {
        String existingMatchIndex = match.getMatchIndex(teamOne, teamTwo);
        return match.getMatchList().get(existingMatchIndex);
    }
}