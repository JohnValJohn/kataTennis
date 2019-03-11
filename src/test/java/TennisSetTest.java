import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TennisSetTest {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private TennisSet tennisSet = new TennisSet(player1, player2);

    @Test
    public void testOneGameWon(){
        tennisSet.scoreOneGame(player1);
        assertEquals(1, player1.getTennisSetScore());
    }

    @Test
    public void testSixStraightGames(){
        scoreManyGames(6, player2);
        assertEquals(6, player2.getTennisSetScore());
        assertEquals(player2, tennisSet.getWinner());
    }

    @Test
    public void testCloseSet() {
        scoreManyGames(5, player1);
        scoreManyGames(5, player2);
        tennisSet.scoreOneGame(player1);
        assertEquals(null, tennisSet.getWinner());
        tennisSet.scoreOneGame(player1);
        assertEquals(7, player1.getTennisSetScore());
        assertEquals(5, player2.getTennisSetScore());
        assertEquals(player1, tennisSet.getWinner());
    }

    private void winGame(Player winner, Game game){
        for (int i = 0; i<4; i++){
            game.scoreOnePoint(winner);
        }
    }

    private void scoreManyGames(int numberOfGames, Player gameWinner){
        for (int i = 0; i< numberOfGames; i++){
            tennisSet.scoreOneGame(gameWinner);
        }
    }

}
