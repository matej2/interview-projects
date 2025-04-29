import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MatchTest {
    @Test
    fun updateScoreInitialScoreTest() {
        val game = getGameInstance()

        assertEquals(0, game.awayTeam.score)
        assertEquals(0, game.homeTeam.score)
    }

    @Test
    fun updateScoreSetStartScoreTest() {
        val game = getGameInstance()

        game.updateScore(1, 2)

        assertEquals(1, game.homeTeam.score)
        assertEquals(2, game.awayTeam.score)
    }

    fun getGameInstance() = Match(homeTeamName = "Chengdu United", awayTeamName = "Chelmsford United")
}