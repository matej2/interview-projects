class Scoreboard {
    private val matchList: MutableList<Match> = mutableListOf()

    fun getSummary(): List<Match> {
        return matchList
            .stream()
            .sorted(compareBy<Match> { it.getTotalScore() })
            .sorted(compareBy<Match> { it.started })
            .toList()
    }
    fun addMatch(match: Match) = matchList.add(match)
    private fun getMatchIndexByTeamNames(homeTeamName: String, awayTeamName: String): Int {
        return matchList.indexOfFirst { it.homeTeam.name == homeTeamName && it.awayTeam.name == awayTeamName }
    }
    fun updateMatch(match: Match, homeTeamScore: Int, awayTeamScore: Int): Boolean {
        val matchIndex = getMatchIndexByTeamNames(match.homeTeam.name, match.awayTeam.name)

        if (matchIndex == -1) {
            return false
        }
        val foundMatch: Match = matchList[matchIndex]
        foundMatch.updateScore(homeTeamScore, awayTeamScore)

        return true
    }
    private fun getMatch(match: Match): Match? {
        return matchList
            .stream()
            .filter { it.homeTeam.name == match.homeTeam.name}
            .filter { it.awayTeam.name == match.awayTeam.name}
            .findFirst()
            .orElse(null)
    }
    fun finishMatch(match: Match): Boolean {
        val foundMatch: Match = getMatch(match) ?: return false

        matchList.remove(foundMatch)

        return true
    }
}