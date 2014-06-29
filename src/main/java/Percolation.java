/**
 * Class for system percolation emulation.
 */
public class Percolation {
    private static final int MAX_GRID_VALUE = (int) Math.sqrt((double) (Integer.MAX_VALUE - 2));

    private boolean[][] cells;
    private int gridSize;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    /**
     * Creates grid of nxn cells with all blocked sites. Size of grid should be in range [1, 46340].
     *
     * @param n size of grid.
     * @throws IllegalArgumentException if size of grid is not in range.
     */
    public Percolation(final int n) {
        if (n < 1 || n > MAX_GRID_VALUE) {
            throw new IllegalArgumentException(String.format("Grid size should be in interval [ %d , %d ].", 1, MAX_GRID_VALUE));
        }
        initiateQuickUnionUF(n);
        this.cells = new boolean[n][n];
        this.gridSize = n;
    }

    /**
     * Opens cell with defined indexes. Both indexes should be in range [1, n], where n is the grid size.
     *
     * @param i row index.
     * @param j column index.
     * @throws {@linkplain IndexOutOfBoundsException} if row or column index is not in range [1, n].
     */
    public void open(final int i, final int j) {
        checkIndexes(i, j);
        if (!cells[i - 1][j - 1]) {
            cells[i - 1][j - 1] = true;
            connectWithNeighbors(i - 1, j - 1);
        }
    }

    /**
     * Defines, if specified cell is opened.
     *
     * @param i row index, should be in range [1, n], where n is the grid size.
     * @param j column index, should be in range [1, n], where n is the grid size.
     * @return true, if cell is opened.
     * @throws {@linkplain IndexOutOfBoundsException} if row or column index is not in range [1, n].
     */
    public boolean isOpen(final int i, final int j) {
        checkIndexes(i, j);
        return cells[i - 1][j - 1];
    }

    /**
     * Defines, if cell is full (if it is opened and there is a chain of opened cells, which can bring us from the first
     * row to specified cell.
     *
     * @param i i row index, should be in range [1, n], where n is the grid size.
     * @param j column index, should be in range [1, n], where n is the grid size.
     * @return true, if cell is full.
     * @throws {@linkplain IndexOutOfBoundsException} if row or column index is not in range [1, n].
     */
    public boolean isFull(final int i, final int j) {
        checkIndexes(i, j);
        return weightedQuickUnionUF.connected(0, convertToWQUUAddress(i - 1, j - 1));
    }

    /**
     * Defines, if there is at list one opened cell in the top row which is connected with a opened cell in the bottom row.
     *
     * @return true, if there is at list one opened cell in the top row which is connected with a opened cell in the
     *         bottom row.
     */
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, gridSize * gridSize + 1);
    }

    private void connectWithNeighbors(final int i, final int j) {
        connectWithNeighbor(i, j - 1, i, j);
        connectWithNeighbor(i, j + 1, i, j);
        connectWithNeighbor(i - 1, j, i, j);
        connectWithNeighbor(i + 1, j, i, j);
        connectWithTopOrBottom(i, j);
    }

    private void connectWithTopOrBottom(final int i, final int j) {
        int index = convertToWQUUAddress(i, j);
        if (i == 0) {
            weightedQuickUnionUF.union(0, index);
        }
        if (i == (gridSize - 1)) {
            weightedQuickUnionUF.union(gridSize * gridSize + 1, index);
        }
    }

    private void connectWithNeighbor(final int i1, final int j1, final int i2, final int j2) {
        if (isInRange(i1 + 1) && isInRange(j1 + 1) && cells[i1][j1]) {
            int index1 = convertToWQUUAddress(i1, j1);
            int index2 = convertToWQUUAddress(i2, j2);
            weightedQuickUnionUF.union(index1, index2);
        }
    }

    private int convertToWQUUAddress(final int i, int j) {
        return gridSize * i + j + 1;
    }

    private void initiateQuickUnionUF(final int n) {
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    }

    private void checkIndexes(final int i, final int j) {
        if (!(isInRange(i) && isInRange(j))) {
            throw new IndexOutOfBoundsException(String.format("%d and %d are invalid indexes.", i, j));
        }
    }

    private boolean isInRange(final int i) {
        return i > 0 && i <= gridSize;
    }
}
