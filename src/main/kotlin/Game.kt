class Game (val homeTeamName: String, val awayTeamName: String) {
    var homeTeam: Team = Team(homeTeamName)
    var awayTeam: Team = Team(awayTeamName)

    fun updateScore(homeTeamScore: Int, awayTeamScore: Int) {
        homeTeam.score = homeTeamScore
        awayTeam.score = awayTeamScore
    }


}