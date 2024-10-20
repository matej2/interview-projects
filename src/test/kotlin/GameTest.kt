import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GameTest {
    var game : Game = Game()
    @BeforeEach
    fun setup() {
        game = Game()
    }
    @Test
    fun updateScoreSetCorrectScoreTest() {
        assertEquals(0, game.awayTeam)
        assertEquals(0, game.homeTeam)
    }
}