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
        match.startMatch(teamOne, teamTwo);
    }
    @Test
    void testStartMatchValidInput() {
        match.startMatch("A", "B");
    }

    @Test
    void testGetMatchIndexValidInput() {
        String foundMatch = match.getMatchIndex(teamOne, teamTwo);

        assertNotNull(foundMatch);
    }

    @Test
    void testUpdateMatchValidInput() {
        match.updateMatch(teamOne, 1, teamTwo, 0);

        Map<String, Integer> updatedMatch = getMatch();
        assertEquals(1, updatedMatch.get(teamOne));
        assertEquals(0, updatedMatch.get(teamTwo));
    }

    private Map<String, Integer> getMatch() {
        String existingMatchIndex = match.getMatchIndex(teamOne, teamTwo);
        return match.getMatchList().get(existingMatchIndex);
    }
}