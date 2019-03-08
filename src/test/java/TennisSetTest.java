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
        Game g = new Game(player1, player2);
        winGame(player1, g);
        assertEquals(1, player1.getTennisSetScore());
    }

    private void winGame(Player winner, Game game){
        for (int i = 0; i<4; i++){
            game.scoreOnePoint(winner);
        }
    }

}
