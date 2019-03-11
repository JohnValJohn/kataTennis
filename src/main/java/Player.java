/**
 Class that stores the score of a tennis player
 */
public class Player {
    private GameScore gameScore = GameScore.ZERO;
    private int tennisSetScore = 0;
    private int tieBreakScore = 0;

    public int getTennisSetScore() {
        return tennisSetScore;
    }

    public void setTennisSetScore(int tennisSetScore) {
        this.tennisSetScore = tennisSetScore;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public int getTieBreakScore() {
        return tieBreakScore;
    }

    public void setTieBreakScore(int tieBreakScore) {
        this.tieBreakScore = tieBreakScore;
    }
}
