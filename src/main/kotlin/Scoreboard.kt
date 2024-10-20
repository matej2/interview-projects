import java.util.stream.Collectors

class Scoreboard() {
    var matchList: MutableList<Match> = mutableListOf()

    fun getSummary(): List<Match>? {
        return matchList
            .stream()
            .sorted(compareBy<Match> { it.getTotalScore() })
            .sorted(compareBy<Match> { it.started })
            .toList()
    }
    fun addMatch(match: Match) = matchList.add(match)
    fun finishMatch(match: Match): Boolean {
        val foundMatch: MutableList<Match> = matchList
            .stream()
            .filter { it.homeTeam.name == match.homeTeam.name}
            .filter { it.awayTeam.name == match.awayTeam.name}
            .toList()

        if(foundMatch.size == 0) {
            return false
        }

        matchList.remove(foundMatch[0])

        return true
    }
}