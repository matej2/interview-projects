import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Match {
    private final List<Map<String, Integer>> matchlist = new ArrayList<>();

    // TODO: Check if it already exists
    public void startMatch(String teamOne, String teamTwo) {
        matchlist.add(Map.of(teamOne, 0, teamTwo, 0));
    }

    public void updateMatch(String teamOne, int teamOneScore, String teamTwo, int teamTwoScore) {
        getMatch(teamOne, teamTwo).put(teamOne, teamOneScore);
        getMatch(teamOne, teamTwo).put(teamTwo, teamTwoScore);
    }

    public Map<String, Integer> getMatch(String teamOne, String teamTwo) {
        return matchlist
                .stream()
                .filter(entry -> entry.containsKey(teamOne))
                .filter(entry -> entry.containsKey(teamTwo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No data found"));
    }
}
