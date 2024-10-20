import java.util.*

class Match (val homeTeamName: String, val awayTeamName: String) {
    val homeTeam: Team = Team(homeTeamName)
    val awayTeam: Team = Team(awayTeamName)
    val started: Date = Date()

    fun updateScore(homeTeamScore: Int, awayTeamScore: Int) {
        homeTeam.score = homeTeamScore
        awayTeam.score = awayTeamScore
    }

    fun getTotalScore() : Int {
        return homeTeam.score + awayTeam.score
    }
}