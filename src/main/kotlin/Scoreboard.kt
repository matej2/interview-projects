import java.util.stream.Collectors

class Scoreboard() {
    var matchList: MutableList<Match> = mutableListOf()

    fun getSummary(): MutableList<Match>? {
        return matchList
            .stream()
            .sorted(compareBy<Match> { it.getTotalScore() })
            .sorted(compareBy<Match> { it.started })
            .toList()
    }
    fun addMatch(match: Match) = matchList.add(match)
}