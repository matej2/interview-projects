import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Match {
    String teamOneName;
    Integer teamOneScore;
    String teamTwoName;
    Integer teamTwoScore;
    Date started;

    public static Match initMatch(String teamOne, String teamTwo) {
        return new Match(teamOne, 0, teamTwo, 0, new Date());
    }
}
