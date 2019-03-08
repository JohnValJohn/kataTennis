public class Player {
    private GameScore gameScore = GameScore.ZERO;
    private int tennisSetScore = 0;

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
}
