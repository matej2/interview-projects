import java.util.*

class Match (val homeTeamName: String, val awayTeamName: String) {
    var homeTeam: Team = Team(homeTeamName)
    var awayTeam: Team = Team(awayTeamName)
    val started: Date = Date()

    fun updateScore(homeTeamScore: Int, awayTeamScore: Int) {
        homeTeam.score = homeTeamScore
        awayTeam.score = awayTeamScore
    }

    fun getTotalScore() : Int {
        return homeTeam.score + awayTeam.score
    }
}