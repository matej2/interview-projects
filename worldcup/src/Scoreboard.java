import java.util.*;

public class Scoreboard {
    private Map<String, Match> matchList = new HashMap<>();

    public void startMatch(String teamOne, String teamTwo) {
        if (getMatchIndex(teamOne, teamTwo) != null) {
            throw new RuntimeException("Match already exists");
        }

        Match newMatch = Match.initMatch(teamOne, teamTwo);

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

    // TODO: Ordering
    public Map<String, Match> getMatchList() {
        return matchList;
    }
}
