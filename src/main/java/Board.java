import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class Board {
    private static final int MAX_DIGITS_NUMBER = 6;
    private int[] blocksFlatArray;
    private int[][] blocks;
    private int zeroRow = -1;
    private int zeroColumn = -1;
    private int dimension;

    public Board(int[][] blocks) {
        this.dimension = blocks.length;
        this.blocks = new int[dimension][dimension];
        this.blocksFlatArray = new int[dimension * dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.blocks[i][j] = blocks[i][j];
                this.blocksFlatArray[i * dimension + j] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    zeroRow = i;
                    zeroColumn = j;
                }
            }
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
        int row = StdRandom.uniform(dimension);
        if (row == zeroRow) {
            row = row == dimension - 1 ? row - 1 : dimension + 1;
        }
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
        return Arrays.equals(blocksFlatArray, that.blocksFlatArray);
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<Board>();
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
        if (zeroColumn != dimension-1) {
            neighbors.add(getTwinBoard(zeroRow, zeroColumn, zeroRow, zeroColumn + 1));
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension).append("\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                stringBuilder.append(generateValueWithZeros(blocks[i][j]));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String generateValueWithZeros(int blockValue) {
        String stringBlockValue = String.valueOf(blockValue);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBlockValue);
        for (int i = 0; i < MAX_DIGITS_NUMBER - stringBlockValue.length(); i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private Board getTwinBoard(int row1, int column1, int row2, int column2) {
        Board twin = new Board(blocks);
        int temp1 = twin.blocks[row1][column1];
        twin.blocks[row1][column1] = twin.blocks[row2][column2];
        twin.blocks[row2][column2] = temp1;
        int temp2 = twin.blocksFlatArray[row1 * dimension + column1];
        twin.blocksFlatArray[row1 * dimension + column1] = twin.blocksFlatArray[row2 * dimension + column2];
        twin.blocksFlatArray[row2 * dimension + column2] = temp2;
        if (twin.blocks[row1][column1] == 0) {
            twin.zeroRow = row1;
            twin.zeroColumn = column1;
        } else if (twin.blocks[row2][column2] == 0) {
            twin.zeroRow = row2;
            twin.zeroColumn = column2;
        }
        return twin;
    }
}

