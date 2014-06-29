import java.util.BitSet;

public class Percolation1 {
    private static final int MAX_GRID_VALUE = (int) Math.sqrt((double) (Integer.MAX_VALUE - 2));

    private BitSet cells;
    private int gridSize;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation1(int n) {
        if (n < 1 || n > MAX_GRID_VALUE) {
            throw new IllegalArgumentException(String.format("Grid size should be in interval [ %d , %d ].", 1, MAX_GRID_VALUE));
        }
        initiateQuickUnionUF(n);
        this.cells = new BitSet(n * n);
        this.gridSize = n;
    }

    public void open(int i, int j) {
        checkIndexes(i, j);
        if (!isOpen(i, j)) {
            cells.set(convertToArrayAddress(i - 1, j - 2), true);
            connectWithNeighbors(i - 1, j - 1);
        }
    }

    public boolean isOpen(int i, int j) {
        checkIndexes(i, j);
        return cells.get(convertToArrayAddress(i - 1, j - 2));
    }

    public boolean isFull(int i, int j) {
        return weightedQuickUnionUF.connected(0, convertToArrayAddress(i - 1, j - 1));
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, gridSize * gridSize + 1);
    }

    private void connectWithNeighbors(int i, int j) {
        connectWithNeighbor(i - 1, j, i, j);
        connectWithNeighbor(i, j - 1, i, j);
        connectWithNeighbor(i + 1, j, i, j);
        connectWithNeighbor(i, j + 1, i, j);
        connectWithTopOrBottom(i, j);
    }

    private void connectWithTopOrBottom(int i, int j) {
        int index = convertToArrayAddress(i, j);
        if (i == 0) {
            weightedQuickUnionUF.union(0, index);
        }
        if (i == (gridSize - 1)) {
            weightedQuickUnionUF.union(gridSize * gridSize + 1, index);
        }
    }

    private void connectWithNeighbor(int i1, int j1, int i2, int j2) {
        if (isInRange(i1 + 1) && isInRange(j1 + 1) && isOpen(i1 + 1, j1 + 1)) {
            int index1 = convertToArrayAddress(i1, j1);
            int index2 = convertToArrayAddress(i2, j2);
            weightedQuickUnionUF.union(index1, index2);
        }
    }

    private int convertToArrayAddress(int i, int j) {
        return gridSize * i + j + 1;
    }

    private void initiateQuickUnionUF(int n) {
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    }

    private void checkIndexes(int i, int j) {
        if (!(isInRange(i) && isInRange(j))) {
            throw new IndexOutOfBoundsException(String.format("%d and %d are invalid indexes.", i, j));
        }
    }

    private boolean isInRange(int i) {
        return i > 0 && i <= gridSize;
    }
}
