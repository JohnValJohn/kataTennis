import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(JUnit4.class)
public class TennisGameTest {
    private Game g;
    private Player player1;
    private Player player2;
    private TennisSet mockedTennisSet = mock(TennisSet.class);

    @Before
    public void setup(){
        player1 = new Player();
        player2 = new Player();
        g = new Game(player1, player2, mockedTennisSet);
    }

    @Test
    public void testOnePoint() {
        g.scoreOnePoint(player1);
        assertEquals(GameScore.FIFTEEN, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
    }

    @Test
    public void testTwoStraightPoints(){
        scoreManyPoints(2, player1);
        assertEquals(GameScore.THIRTY, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
    }

    @Test
    public void testThreeStraightPoints(){
        scoreManyPoints(3, player1);
        assertEquals(GameScore.FOURTY, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
    }

    @Test
    public void testStraightGame(){
        scoreManyPoints(4, player1);
        checkWinner((player1));
    }

    @Test
    public void testDeuce(){
        reachDeuce();
        assertEquals(GameScore.DEUCE, player1.getGameScore());
        assertEquals(GameScore.DEUCE, player2.getGameScore());
    }

    @Test
    public void testAdvantage(){
        reachDeuce();
        g.scoreOnePoint(player2);
        assertEquals(GameScore.FOURTY, player1.getGameScore());
        assertEquals(GameScore.ADVANTAGE, player2.getGameScore());
    }

    @Test
    public void testWinAfterDeuce(){
        reachDeuce();
        scoreManyPoints(2, player2);
        checkWinner(player2);
    }

    @Test
    public void testDeuceAfterAdvantage(){
        reachDeuce();
        g.scoreOnePoint(player1);
        g.scoreOnePoint(player2);
        assertEquals(GameScore.DEUCE, player1.getGameScore());
        assertEquals(GameScore.DEUCE, player2.getGameScore());
    }

    private void reachDeuce() {
        scoreManyPoints(2, player1);
        g.scoreOnePoint(player2);
        g.scoreOnePoint(player1);
        scoreManyPoints(2,player2);
    }

    private void scoreManyPoints(int numberOfPointsWon, Player pointWinner) {
        for (int i=0; i<numberOfPointsWon; i++){
            g.scoreOnePoint(pointWinner);
        }
    }

    private void checkWinner(Player winner){
        assertEquals(GameScore.ZERO, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
        verify(mockedTennisSet, times(1)).scoreOneGame(winner);
    }

}
