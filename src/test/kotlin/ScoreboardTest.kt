import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ScoreboardTest{
    @Test
    fun getSummaryForEmptyScoreboardTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()

        val summary = scoreboard.getSummary()

        assertEquals(0, summary.size)
    }

    @Test
    fun getSummaryForNonEmptyScoreboardTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()
        val match: Match = getMatchInstance()

        scoreboard.addMatch(match)
        val summary = scoreboard.getSummary()

        assertEquals(1, summary.size)
    }


    @Test
    fun addMatchValidInputTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()
        val match: Match = getMatchInstance()

        scoreboard.addMatch(match)
        val summary = scoreboard.getSummary()

        assertEquals(1, summary.size)
        assertEquals ("Nanjing United", summary[0].homeTeam.name)
        assertEquals ("Karachi Palace", summary[0].awayTeam.name)

    }

    @Test
    fun finishMatchValidInput() {
        val scoreboard: Scoreboard = getScoreboardInstance()
        val match: Match = getMatchInstance()

        scoreboard.addMatch(match)
        val result: Boolean = scoreboard.finishMatch(match)
        val matchList = scoreboard.getSummary()

        assertTrue(result)
        assertEquals(0, matchList.size)
    }

    @Test
    fun finishMatchInvalidInput() {
        val scoreboard: Scoreboard = getScoreboardInstance()
        val match: Match = getMatchInstance()

        scoreboard.addMatch(match)
        val result: Boolean = scoreboard.finishMatch(Match("A", "B"))

        assertFalse(result)
    }

    @Test
    fun updateMatchByIndexForValidInputTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()
        val match: Match = getMatchInstance()

        scoreboard.addMatch(match)

        assertTrue(scoreboard.updateMatch(match, 1, 0))
        assertEquals(1, scoreboard.getSummary()[0].homeTeam.score)
        assertEquals(0, scoreboard.getSummary()[0].awayTeam.score)
    }

    private fun getScoreboardInstance() = Scoreboard()
    private fun getMatchInstance() = Match("Nanjing United", "Karachi Palace")
}