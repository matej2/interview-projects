import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class ScoreboardTest{
    @Test
    fun getSummaryForEmptyScoreboardTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()

        val summary = scoreboard.getSummary()

        assertNotNull(summary)
        assertEquals(0, summary.size)
    }

    @Test
    fun getSummaryForNonEmptyScoreboardTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()
        val match: Match = getMatchInstance()

        scoreboard.addMatch(match)
        val summary = scoreboard.getSummary()

        assertNotNull(summary)
        assertEquals(1, summary.size)
    }


    @Test
    fun addMatchValidInputTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()
        val match: Match = getMatchInstance()

        scoreboard.addMatch(match)

        assertEquals(1, scoreboard.matchList.size)
        assertEquals ("Nanjing United", scoreboard.matchList[0].homeTeam.name)
        assertEquals ("Karachi Palace", scoreboard.matchList[0].awayTeam.name)

    }

    fun getScoreboardInstance() = Scoreboard()
    fun getMatchInstance() = Match("Nanjing United", "Karachi Palace")
}