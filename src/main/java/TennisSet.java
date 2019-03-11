public class TennisSet {
    private Player player1;
    private Player player2;
    private Player winner;

    public TennisSet(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void scoreOneGame(Player gameWinner){
        Player otherPlayer = gameWinner == player1 ? player2 : player1;
        gameWinner.setTennisSetScore(gameWinner.getTennisSetScore() + 1);
        if(gameWinner.getTennisSetScore() >= 6 && thereIsAtLeastTwoPointsDifference(gameWinner, otherPlayer)){
            this.setWinner(gameWinner);
        }
    }

    private boolean thereIsAtLeastTwoPointsDifference(Player gameWinner, Player otherPlayer) {
        return gameWinner.getTennisSetScore() - otherPlayer.getTennisSetScore() >= 2;
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
