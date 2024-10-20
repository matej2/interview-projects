class Game (val homeTeam: Team, val awayTeam: Team) {

    fun updateScore(homeTeamScore: UByte, awayTeamScore: UByte) {
        homeTeam.score = homeTeamScore
        awayTeam.score = awayTeamScore
    }


}