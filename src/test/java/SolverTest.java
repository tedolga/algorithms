import org.junit.Test;

import junit.framework.Assert;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class SolverTest {
    @Test
    public void testIsSolvable() throws Exception {
        Board board = BoardReader.readBoard("test-board-infeasible-0.txt");
        Solver solver = new Solver(board);
        Assert.assertFalse(solver.isSolvable());

        board = BoardReader.readBoard("test-board-3.txt");
        solver = new Solver(board);
        Assert.assertTrue(solver.isSolvable());
    }

    @Test
    public void testMoves() throws Exception {
        Board board = BoardReader.readBoard("test-board-0.txt");
        Solver solver = new Solver(board);
        Assert.assertEquals(0, solver.moves());

        board = BoardReader.readBoard("test-board-2.txt");
        solver = new Solver(board);
        Assert.assertEquals(1, solver.moves());

        board = BoardReader.readBoard("test-board-1.txt");
        solver = new Solver(board);
        Assert.assertEquals(4, solver.moves());

        board = BoardReader.readBoard("test-board-3.txt");
        solver = new Solver(board);
        Assert.assertEquals(15, solver.moves());
    }

    @Test
    public void testSolution() throws Exception {

    }
}
