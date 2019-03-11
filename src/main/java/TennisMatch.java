/**
 * Class that automatically calculates the score of 2 tennis players every time a point is played
 */
public class TennisMatch {
    private Player player1;
    private Player player2;
    private Player winner;

    public TennisMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Call this method every time a player scores a point.
     * The scores will be automatically updated
     * @param pointWinner
     */
    public void scoreOnePoint(Player pointWinner) {
        boolean isTieBreak = player1.getTennisSetScore() == 6 && player2.getTennisSetScore() == 6;
        if (isTieBreak) {
            scoreTieBreakPoint(pointWinner);
        } else {
            scoreGamePoint(pointWinner);
        }
    }

    private void scoreGamePoint(Player pointWinner) {
        GameScore previousScore = pointWinner.getGameScore();
        Player otherPlayer = getOtherPlayer(pointWinner);
        switch (previousScore) {
            case ZERO:
                pointWinner.setGameScore(GameScore.FIFTEEN);
                break;
            case FIFTEEN:
                pointWinner.setGameScore(GameScore.THIRTY);
                break;
            case THIRTY:
                if (otherPlayer.getGameScore() == GameScore.FOURTY) {
                    setDeuceScore();
                } else {
                    pointWinner.setGameScore(GameScore.FOURTY);
                }
                break;
            case FOURTY:
                if (otherPlayer.getGameScore() == GameScore.ADVANTAGE) {
                    setDeuceScore();
                } else {
                    winGame(pointWinner);
                }
                break;
            case DEUCE:
                pointWinner.setGameScore(GameScore.ADVANTAGE);
                otherPlayer.setGameScore(GameScore.FOURTY);
                break;
            case ADVANTAGE:
                winGame(pointWinner);
        }
    }

    private void scoreTieBreakPoint(Player pointWinner) {
        Player otherPlayer = getOtherPlayer(pointWinner);
        pointWinner.setTieBreakScore(pointWinner.getTieBreakScore() + 1);
        if (pointWinner.getTieBreakScore() >= 7 && thereIsAtLeastTwoPointsDifference(pointWinner.getTieBreakScore(), otherPlayer.getTieBreakScore())) {
            winTieBreak(pointWinner);
        }
    }

    private void scoreOneGame(Player gameWinner) {
        Player otherPlayer = getOtherPlayer(gameWinner);
        gameWinner.setTennisSetScore(gameWinner.getTennisSetScore() + 1);
        if (gameWinner.getTennisSetScore() >= 6 && thereIsAtLeastTwoPointsDifference(gameWinner.getTennisSetScore(), otherPlayer.getTennisSetScore())) {
            this.setWinner(gameWinner);
        }
    }

    private void setDeuceScore() {
        player1.setGameScore(GameScore.DEUCE);
        player2.setGameScore(GameScore.DEUCE);
    }

    private void winGame(Player winner) {
        player1.setGameScore(GameScore.ZERO);
        player2.setGameScore(GameScore.ZERO);
        this.scoreOneGame(winner);
    }

    private void winTieBreak(Player pointWinner){
        scoreOneGame(pointWinner);
        player1.setTieBreakScore(0);
        player2.setTieBreakScore(0);
        this.setWinner(pointWinner);
    }

    private boolean thereIsAtLeastTwoPointsDifference(int highestScore, int lowestScore) {
        return highestScore - lowestScore >= 2;
    }

    private Player getOtherPlayer(Player player){
        return player == this.player1 ? this.player2 : this.player1;
    }


    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
