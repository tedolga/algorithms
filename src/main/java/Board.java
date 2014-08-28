import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Board {
    private int[] blocksFlatArray;
    private int[][] blocks;
    private int dimension;

    public Board(int[][] blocks) {
        this.dimension = blocks.length;
        this.blocks = Arrays.copyOf(blocks, dimension);
        this.blocksFlatArray = new int[dimension * dimension];
        for (int i = 0; i < dimension; i++) {
            System.arraycopy(blocks[i], 0, this.blocksFlatArray, i * dimension, dimension);
        }
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < blocksFlatArray.length; i++) {
            if (blocksFlatArray[i] != 0 && blocksFlatArray[i] != i + 1) {
                hamming += 1;
            }
        }
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < blocksFlatArray.length; i++) {
            if (blocksFlatArray[i] != 0 && blocksFlatArray[i] != i + 1) {
                int currentHorizontalIndex = i / dimension;
                int currentVerticalIndex = i % dimension;
                int requiredHorizontalIndex = (blocksFlatArray[i] - 1) / dimension;
                int requiredVerticalIndex = (blocksFlatArray[i] - 1) % dimension;
                manhattan += Math.abs(requiredHorizontalIndex - currentHorizontalIndex) + Math.abs(requiredVerticalIndex - currentVerticalIndex);
            }
        }
        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        if (dimension == 2) {
            return new Board(blocks);
        }
        int row = StdRandom.uniform(dimension);
        int column1 = StdRandom.uniform(dimension);
        int column2 = column1 == dimension - 1 ? column1 - 1 : column1 + 1;
        return getTwinBoard(row, column1, row, column2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Board)) {
            return false;
        }
        Board that = (Board) o;
        return Arrays.equals(blocks, that.blocks);
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<Board>();
        int zeroRow = -1;
        int zeroColumn = -1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] == 0) {
                    zeroRow = i;
                    zeroColumn = j;
                    break;
                }
            }
        }
        if (zeroRow == -1 || zeroColumn == -1) {
            throw new IllegalStateException("Empty element is not present on board.");
        }
        if (zeroRow != 0) {
            neighbors.add(getTwinBoard(zeroRow, zeroColumn, zeroRow - 1, zeroColumn));
        }
        if (zeroRow != dimension - 1) {
            neighbors.add(getTwinBoard(zeroRow, zeroColumn, zeroRow + 1, zeroColumn));
        }
        if (zeroColumn != 0) {
            neighbors.add(getTwinBoard(zeroRow, zeroColumn, zeroRow, zeroColumn - 1));
        }
        if (zeroColumn != dimension) {
            neighbors.add(getTwinBoard(zeroRow, zeroColumn, zeroRow, zeroColumn + 1));
        }
        return neighbors;
    }

    public String toString() {
    }// string representation of the board (in the output format specified below)

    private Board getTwinBoard(int row1, int column1, int row2, int column2) {
        Board twin = new Board(blocks);
        int temp = twin.blocks[row1][column1];
        twin.blocks[row1][column1] = twin.blocks[row2][column2];
        twin.blocks[row2][column2] = temp;
        return twin;
    }
}
}