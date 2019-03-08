public class Game {
    private Player winner;
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


    public void scoreOnePoint(Player pointWinner) {
        GameScore previousScore = pointWinner.getScore();
        Player otherPlayer = pointWinner == player1 ? player2 : player1;
        switch (previousScore) {
            case ZERO:
                pointWinner.setScore(GameScore.FIFTEEN);
                break;
            case FIFTEEN:
                pointWinner.setScore(GameScore.THIRTY);
                break;
            case THIRTY:
                if (otherPlayer.getScore() == GameScore.FOURTY) {
                    setDeuceScore();
                } else {
                    pointWinner.setScore(GameScore.FOURTY);
                }
                break;
            case FOURTY:
                if (otherPlayer.getScore() == GameScore.ADVANTAGE){
                    setDeuceScore();
                } else {
                    winGame(pointWinner);
                }
                break;
            case DEUCE:
                pointWinner.setScore(GameScore.ADVANTAGE);
                otherPlayer.setScore(GameScore.FOURTY);
                break;
            case ADVANTAGE:
                winGame(pointWinner);
        }
    }

    private void setDeuceScore() {
        player1.setScore(GameScore.DEUCE);
        player2.setScore(GameScore.DEUCE);
    }

    private void winGame(Player winner) {
        player1.setScore(GameScore.ZERO);
        player2.setScore(GameScore.ZERO);
        this.setWinner(winner);
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
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
}
