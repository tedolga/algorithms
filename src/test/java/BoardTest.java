import junit.framework.Assert;

import org.junit.Test;

public class BoardTest {
    @Test
    public void testDimension() throws Exception {
        Board board = BoardReader.readBoard("test-board-1.txt");
        Assert.assertEquals(3, board.dimension());
        board = BoardReader.readBoard("test-board-2.txt");
        Assert.assertEquals(2, board.dimension());
        board = new Board(new int[128][128]);
        Assert.assertEquals(128, board.dimension());
    }

    @Test
    public void testHamming() throws Exception {
        Board board = BoardReader.readBoard("test-board-1.txt");
        Assert.assertEquals(4, board.hamming());
        board = BoardReader.readBoard("test-board-0.txt");
        Assert.assertEquals(0, board.hamming());
    }

    @Test
    public void testManhattan() throws Exception {
        Board board = BoardReader.readBoard("test-board-1.txt");
        Assert.assertEquals(4, board.manhattan());
        board = BoardReader.readBoard("test-board-3.txt");
        Assert.assertEquals(28, board.manhattan());
        board = BoardReader.readBoard("test-board-0.txt");
        Assert.assertEquals(0, board.manhattan());
    }

    @Test
    public void testIsGoal() throws Exception {
        Board board = BoardReader.readBoard("test-board-0.txt");
        Assert.assertTrue(board.isGoal());
        board = BoardReader.readBoard("test-board-2.txt");
        Assert.assertFalse(board.isGoal());
        board = BoardReader.readBoard("test-board-3.txt");
        Assert.assertFalse(board.isGoal());
    }

    @Test
    public void testTwin() throws Exception {
        Board board = BoardReader.readBoard("test-board-0.txt");
        Board twinBoard = board.twin();
        Assert.assertFalse(board.equals(twinBoard));
        Assert.assertFalse(twinBoard.isGoal());
        Assert.assertTrue(board.isGoal());

        board = BoardReader.readBoard("test-board-2.txt");
        twinBoard = board.twin();
        Assert.assertFalse(board.equals(twinBoard));

    }

    @Test
    public void testEquals() throws Exception {
        Board board1 = BoardReader.readBoard("test-board-1.txt");
        Board board2 = BoardReader.readBoard("test-board-1.txt");
        Assert.assertEquals(board1, board2);
        board2 = board2.twin();
        Assert.assertFalse(board1.equals(board2));
    }

    @Test
    public void testNeighbors() throws Exception {
        Board board = BoardReader.readBoard("test-board-2.txt");
        Iterable<Board> neighbors = board.neighbors();
        int count = 0;
        boolean thereIsGoal = false;
        for (Board boardNeighbor : neighbors) {
            Assert.assertFalse(boardNeighbor.equals(board));
            count++;
            if (boardNeighbor.isGoal()) {
                thereIsGoal = true;
            }
        }
        Assert.assertTrue(thereIsGoal);
        Assert.assertEquals(2, count);

        board = BoardReader.readBoard("test-board-3.txt");
        neighbors = board.neighbors();
        count = 0;
        thereIsGoal = false;
        for (Board boardNeighbor : neighbors) {
            Assert.assertFalse(boardNeighbor.equals(board));
            count++;
        }
        Assert.assertEquals(4, count);
    }

}
