import lombok.Getter;

import java.util.*;

@Getter
public class Scoreboard {
    private Map<String, Match> matchList = new HashMap<>();

    public void startMatch(String teamOne, String teamTwo) {
        if (getMatchIndex(teamOne, teamTwo) != null) {
            throw new RuntimeException("Match already exists");
        }

        Match newMatch = new Match(teamOne, teamTwo);

        matchList.put(UUID.randomUUID().toString(), newMatch);
    }

    public void updateMatch(String teamOne, int teamOneScore, String teamTwo, int teamTwoScore) {
        String existingMatchIndex = getMatchIndex(teamOne, teamTwo);
        if (getMatchIndex(teamOne, teamTwo) == null) {
            throw new RuntimeException("Match does not exist");
        }

        Match existingMatch = matchList.get(existingMatchIndex);
        existingMatch.setTeamOneScore(teamOneScore);
        existingMatch.setTeamTwoScore(teamTwoScore);

        matchList.put(existingMatchIndex, existingMatch);
    }

    public String getMatchIndex(String teamOne, String teamTwo) {
        Map.Entry<String, Match> foundMatch = matchList
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue().getTeamOneName(), teamOne))
                .filter(entry -> Objects.equals(entry.getValue().getTeamTwoName(), teamTwo))
                .findFirst()
                .orElse(new AbstractMap.SimpleEntry<>(null, null));

        return foundMatch.getKey();
    }

    public List<Match> getOrderedScoreboard() {
        return matchList
                .values()
                .stream()
                .sorted(Comparator.comparing(Match::getTotalScore))
                .sorted(Comparator.comparing(Match::getStarted))
                .toList();
    }
}
