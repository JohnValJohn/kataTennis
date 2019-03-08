public class Game {
    private Player winner;
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void scoreOnePoint(Player pointWinner){
        int previousScore = pointWinner.getScore();
        switch (previousScore){
            case 0:
                pointWinner.setScore(15);
                break;
            case 15:
                pointWinner.setScore(30);
                break;
            case 30:
                pointWinner.setScore(40);
                break;
            case 40:
                player1.setScore(0);
                player2.setScore(0);
                this.setWinner(pointWinner);
                break;
        }
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
