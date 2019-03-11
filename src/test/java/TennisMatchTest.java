import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TennisMatchTest {
    private Player player1;
    private Player player2;
    private TennisMatch match;

    @Before
    public void setup() {
        player1 = new Player();
        player2 = new Player();
        match = new TennisMatch(player1, player2);
    }

    @Test
    public void testOnePoint() {
        match.scoreOnePoint(player1);
        assertEquals(GameScore.FIFTEEN, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
    }

    @Test
    public void testTwoStraightPoints() {
        scoreManyPoints(2, player1);
        assertEquals(GameScore.THIRTY, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
    }

    @Test
    public void testThreeStraightPoints() {
        scoreManyPoints(3, player1);
        assertEquals(GameScore.FOURTY, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
    }

    @Test
    public void testStraightGame() {
        scoreManyPoints(4, player1);
        assertEquals(GameScore.ZERO, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
        assertEquals(1, player1.getTennisSetScore());
        assertEquals(0, player2.getTennisSetScore());
    }

    @Test
    public void testDeuce() {
        reachDeuce();
        assertEquals(GameScore.DEUCE, player1.getGameScore());
        assertEquals(GameScore.DEUCE, player2.getGameScore());
    }

    @Test
    public void testAdvantage() {
        reachDeuce();
        match.scoreOnePoint(player2);
        assertEquals(GameScore.FOURTY, player1.getGameScore());
        assertEquals(GameScore.ADVANTAGE, player2.getGameScore());
    }

    @Test
    public void testWinAfterDeuce() {
        reachDeuce();
        scoreManyPoints(2, player2);
        assertEquals(GameScore.ZERO, player1.getGameScore());
        assertEquals(GameScore.ZERO, player2.getGameScore());
        assertEquals(1, player2.getTennisSetScore());
        assertEquals(0, player1.getTennisSetScore());
    }

    @Test
    public void testDeuceAfterAdvantage() {
        reachDeuce();
        match.scoreOnePoint(player1);
        match.scoreOnePoint(player2);
        assertEquals(GameScore.DEUCE, player1.getGameScore());
        assertEquals(GameScore.DEUCE, player2.getGameScore());
    }

    @Test
    public void testSixStraightGames() {
        scoreManyGames(6, player2);
        assertEquals(6, player2.getTennisSetScore());
        assertEquals(player2, match.getWinner());
    }

    @Test
    public void testSetThatGoesToSeven() {
        scoreManyGames(5, player1);
        scoreManyGames(5, player2);
        scoreGame(player1);
        assertEquals(null, match.getWinner());
        scoreGame(player1);
        assertEquals(7, player1.getTennisSetScore());
        assertEquals(5, player2.getTennisSetScore());
        assertEquals(player1, match.getWinner());
    }

    @Test
    public void testOneTieBreakPoint() {
        reachTieBreak();
        match.scoreOnePoint(player1);
        assertEquals(1, player1.getTieBreakScore());
        assertEquals(0, player2.getTieBreakScore());
        assertEquals(null, match.getWinner());
    }

    @Test
    public void testMatchWonAfterTieBreak(){
        reachTieBreak();
        scoreManyPoints(7, player2);
        assertEquals(0, player1.getTieBreakScore());
        assertEquals(0, player2.getTieBreakScore());
        assertEquals(6, player1.getTennisSetScore());
        assertEquals(7, player2.getTennisSetScore());
        assertEquals(player2, match.getWinner());
    }

    @Test
    public void testLongTieBreakNotFinished(){
        reachTieBreak();
        scoreManyPoints(6, player1);
        scoreManyPoints(6, player2);
        match.scoreOnePoint(player1);
        assertEquals(7, player1.getTieBreakScore());
        assertEquals(6, player2.getTieBreakScore());
        assertEquals(6, player1.getTennisSetScore());
        assertEquals(6, player2.getTennisSetScore());
        assertEquals(null, match.getWinner());
    }

    @Test
    public void testLongTieBreakFinished(){
        reachTieBreak();
        scoreManyPoints(6, player1);
        scoreManyPoints(7, player2);
        scoreManyPoints(3, player1);
        assertEquals(0, player1.getTieBreakScore());
        assertEquals(0, player2.getTieBreakScore());
        assertEquals(7, player1.getTennisSetScore());
        assertEquals(6, player2.getTennisSetScore());
        assertEquals(player1, match.getWinner());
    }

    private void reachDeuce() {
        scoreManyPoints(2, player1);
        match.scoreOnePoint(player2);
        match.scoreOnePoint(player1);
        scoreManyPoints(2, player2);
    }

    private void reachTieBreak() {
        scoreManyGames(5, player1);
        scoreManyGames(5, player2);
        scoreGame(player1);
        scoreGame(player2);
    }

    private void scoreManyPoints(int numberOfPointsWon, Player pointWinner) {
        for (int i = 0; i < numberOfPointsWon; i++) {
            match.scoreOnePoint(pointWinner);
        }
    }

    private void scoreManyGames(int numberOfGames, Player gameWinner) {
        for (int i = 0; i < numberOfGames; i++) {
            scoreGame(gameWinner);
        }
    }

    private void scoreGame(Player gameWinner) {
        scoreManyPoints(4, gameWinner);
    }
}
