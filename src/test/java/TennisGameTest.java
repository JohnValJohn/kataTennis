import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TennisGameTest {
    private Game g;
    private Player player1;
    private Player player2;

    @Before
    public void setup(){
        player1 = new Player();
        player2 = new Player();
        g = new Game(player1, player2);
    }

    @Test
    public void testOnePoint() {
        g.scoreOnePoint(player1);
        assertEquals(15, player1.getScore());
        assertEquals(0, player2.getScore());
    }

    @Test
    public void testTwoStraightPoints(){
        winManyPointsInARow(2, player1);
        assertEquals(30, player1.getScore());
        assertEquals(0, player2.getScore());
    }

    @Test
    public void testThreeStraightPoints(){
        winManyPointsInARow(3, player1);
        assertEquals(40, player1.getScore());
        assertEquals(0, player2.getScore());
    }

    @Test
    public void testStraightGame(){
        winManyPointsInARow(4, player1);

        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(player1, g.getWinner());
    }

    @Test
    public void testBeginningOfBalancedGame(){
        reachDeuce();
        assertEquals(40, player1.getScore());
        assertEquals(40, player2.getScore());
    }

    @Test
    public void testFullBalancedGame(){
        reachDeuce();
        g.scoreOnePoint(player2);
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(player2, g.getWinner());
    }

    private void reachDeuce() {
        winManyPointsInARow(2, player1);
        g.scoreOnePoint(player2);
        g.scoreOnePoint(player1);
        winManyPointsInARow(2,player2);
    }

    private void winManyPointsInARow(int numberOfPointsWon, Player pointWinner) {
        for (int i=0; i<numberOfPointsWon; i++){
            g.scoreOnePoint(pointWinner);
        }
    }

}
