class Scoreboard() {
    val matchList = mutableListOf<Match>()

    fun getSummary(): String {
        val orderedMatch = matchList
            .stream()
            .sorted(compareBy { it.getTotalScore() })
            .sorted(compareBy { it.started })

        var output = "Scoreboard summary: \n\n"
        orderedMatch
            .forEach {
                output += "Match between ${it.homeTeam.name} and ${it.awayTeam.name}: ${it.homeTeam.score} : ${it.awayTeam.score}"
            }
        return output
    }
}