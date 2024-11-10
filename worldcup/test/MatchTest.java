import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchTest {

    @Test
    void testInitMatch() {
        Match match = Match.initMatch("A", "B");

        assertEquals(0, match.getTeamOneScore());
        assertEquals(0, match.getTeamTwoScore());
    }
}