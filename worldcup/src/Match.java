import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Match {
    String teamOneName;
    Integer teamOneScore;
    String teamTwoName;
    Integer teamTwoScore;
    Date started;

    private Match() {}

    public Match(String teamOne, String teamTwo) {
        this.teamOneName = teamOne;
        this.teamTwoName = teamTwo;
        this.teamOneScore = 0;
        this.teamTwoScore = 0;
        this.started = new Date();
    }

    public Integer getTotalScore() {
        return teamOneScore + teamTwoScore;
    }
}
