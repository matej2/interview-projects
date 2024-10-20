import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class ScoreboardTest{
    @Test
    fun getSummaryForEmptyScoreboard() {
        val scoreboard: Scoreboard = getScoreboardInstance()

        val summary = scoreboard.getSummary()

        assertTrue(summary.contains("Scoreboard summary"))
    }

    fun getScoreboardInstance() = Scoreboard()
}