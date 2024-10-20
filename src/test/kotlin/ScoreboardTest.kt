import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ScoreboardTest{
    @Test
    fun getSummaryForEmptyScoreboardTest() {
        val scoreboard: Scoreboard = getScoreboardInstance()

        val summary = scoreboard.matchList

        assertTrue(summary.isEmpty())
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