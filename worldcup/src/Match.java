import java.util.*;

public class Match {
    private final HashMap<String, Map<String, Integer>> matchlist = new HashMap<>();

    // TODO: Check if it already exists
    public void startMatch(String teamOne, String teamTwo) {
        HashMap<String, Integer> entry = new HashMap<>();
        entry.put(teamOne, 0);
        entry.put(teamTwo, 0);

        matchlist.put(UUID.randomUUID().toString(), entry);
    }

    public void updateMatch(String teamOne, int teamOneScore, String teamTwo, int teamTwoScore) {
        String existingMatchIndex = getMatchIndex(teamOne, teamTwo);
        Map<String, Integer> existingMatch = matchlist.get(existingMatchIndex);

        existingMatch.put(teamOne, teamOneScore);
        existingMatch.put(teamTwo, teamTwoScore);

        matchlist.put(existingMatchIndex, existingMatch);
    }

    public String getMatchIndex(String teamOne, String teamTwo) {
        Map.Entry<String, Map<String, Integer>> foundMatch = matchlist
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().containsKey(teamOne))
                .filter(entry -> entry.getValue().containsKey(teamTwo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Match not found"));

        return foundMatch.getKey();
    }

    // TODO: Ordering
    public Map<String, Map<String, Integer>> getMatchList() {
        return matchlist;
    }
}
