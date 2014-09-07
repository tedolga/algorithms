import java.util.ArrayList;
import java.util.List;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Solver {
    private boolean isSolvable = true;
    private int moves;
    private List<Board> solution = new ArrayList<Board>();
    private MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();

    public Solver(Board initial) {
        SearchNode searchNode = new SearchNode();
        searchNode.board = initial;
        solution.add(initial);
        findGoal(searchNode);
    }

    private void findGoal(SearchNode searchNode) {
        while (!searchNode.board.isGoal()) {
            Iterable<Board> neighbors = searchNode.board.neighbors();
            int insertedNeighbours = 0;
            for (Board neighbour : neighbors) {
                if (searchNode.previous == null || !neighbour.equals(searchNode.previous.board)
                        && neighbour.manhattan() <= searchNode.board.manhattan()) {
                    SearchNode neighbourNode = new SearchNode();
                    neighbourNode.board = neighbour;
                    neighbourNode.moves = searchNode.moves + 1;
                    neighbourNode.previous = searchNode;
                    minPQ.insert(neighbourNode);
                    insertedNeighbours++;
                }
            }
            if (minPQ.isEmpty()) {
                isSolvable = false;
                return;
            }
            if (insertedNeighbours == 0) {
                solution = new ArrayList<Board>();
                moves = 0;
            }
            SearchNode nextNode = minPQ.delMin();
            solution.add(nextNode.board);
            moves += 1;
            searchNode = nextNode;
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    public static void main(String[] args) {
    }

    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode previous;
        private int moves;

        public int compareTo(SearchNode other) {
            return Integer.valueOf(board.manhattan() + moves).compareTo(other.board.manhattan() + moves);
        }
    }
}
