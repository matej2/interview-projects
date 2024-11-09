import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testGetMatchValidInput() {
        Map<String, Integer> foundMatch = match.getMatch(teamOne, teamTwo);

        assertEquals(0, foundMatch.get(teamOne));
        assertEquals(0, foundMatch.get(teamTwo));
    }
}