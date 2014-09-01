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

    public Solver(Board initial) {
        SearchNode searchNode = new SearchNode();
        searchNode.board = initial;
        solution.add(initial);
        while (!searchNode.board.isGoal()) {
            Iterable<Board> neighbors = searchNode.board.neighbors();
            MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
            for (Board neighbour : neighbors) {
                if (searchNode.previous == null || !neighbour.equals(searchNode.previous.board)) {
                    SearchNode neighbourNode = new SearchNode();
                    neighbourNode.board = neighbour;
                    neighbourNode.moves = searchNode.moves + 1;
                    neighbourNode.previous = searchNode;
                    minPQ.insert(neighbourNode);
                }
            }
            SearchNode nextNode = minPQ.delMin();
            if (nextNode.board.manhattan() > searchNode.board.manhattan()) {
                isSolvable = false;
                moves = 0;
                solution = new ArrayList<Board>();
                return;
            }
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
