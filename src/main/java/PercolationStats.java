/**
 * Class for metering of percolation threshold via Monte Carlo simulation.
 */
public class PercolationStats {
    private static final double RATIO = 1.96;
    private int gridSize;
    private int experimentNumber;
    private double mean;
    private double stddev;
    private double[] openSitesFractions;

    /**
     * Creates {@linkplain PercolationStats} instance and performs T independent computational
     * experiments on an N-by-N grid.
     *
     * @param gridSize         size of NxN grid, which will be used in  percolation threshold calculation.
     * @param experimentNumber number of experiments for percolation threshold calculation.
     */
    public PercolationStats(int gridSize, int experimentNumber) {
        if (gridSize < 1 || experimentNumber < 1) {
            throw new IllegalArgumentException("gridSize and experimentNumber must be greater than 0");
        }
        this.gridSize = gridSize;
        this.experimentNumber = experimentNumber;
        calculateFractions();
    }

    /**
     * Returns mean value of percolation threshold.
     *
     * @return mean value of percolation threshold.
     */
    public double mean() {
        if (mean != 0.0) {
            return mean;
        }
        mean = StdStats.mean(openSitesFractions);
        return mean;
    }

    /**
     * Returns standard deviation value of percolation threshold.
     *
     * @return standard deviation value of percolation threshold.
     */
    public double stddev() {
        if (stddev != 0.0) {
            return stddev;
        }
        stddev = StdStats.stddev(openSitesFractions);
        return stddev;
    }

    /**
     * Returns lower bound of the 95% confidence interval.
     *
     * @return lower bound of the 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - (RATIO * stddev() / Math.sqrt(experimentNumber));
    }

    /**
     * Returns upper bound of the 95% confidence interval.
     *
     * @return upper bound of the 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + (RATIO * stddev() / Math.sqrt(experimentNumber));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("% java PercolationStats ");
            int gridSize = StdIn.readInt();
            int experimentsNumber = StdIn.readInt();
            Stopwatch stopwatch = new Stopwatch();
            percolationStats = new PercolationStats(gridSize, experimentsNumber);
            StdOut.printf("mean                    = %f%n", percolationStats.mean());
            StdOut.printf("stddev                  = %f%n", percolationStats.stddev());
            StdOut.printf("95%% confidence interval = %f, %f%n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
            System.out.println(stopwatch.elapsedTime());
        }
    }

    private double getOpenSitesFraction() {
        Percolation percolation = new Percolation(gridSize);
        int openCellsCount = 0;
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, gridSize + 1);
            int j = StdRandom.uniform(1, gridSize + 1);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                openCellsCount += 1;
            }
        }
        return openCellsCount / Math.pow(gridSize, 2);
    }

    private void calculateFractions() {
        openSitesFractions = new double[experimentNumber];
        for (int i = 0; i < experimentNumber; i++) {
            openSitesFractions[i] = getOpenSitesFraction();
        }
    }
}
