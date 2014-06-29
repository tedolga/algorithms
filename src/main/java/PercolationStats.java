package percolation;

public class PercolationStats {
    private int N;
    private int T;
    private double mean;
    private double stddev;
    private double[] openSitesFractions;
    private boolean isExperimentsExecuted = false;

    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }
        this.N = N;
        this.T = T;
        openSitesFractions = new double[T];
    }

    public double mean() {
        if (mean != 0.0) {
            return mean;
        }
        mean = StdStats.mean(calculateFractions());
        return mean;
    }

    public double stddev() {
        if (stddev != 0.0) {
            return stddev;
        }
        stddev = StdStats.stddev(calculateFractions());
        return stddev;
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev / Math.sqrt(T));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev / Math.sqrt(T));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("% java PercolationStats ");
            int N = StdIn.readInt();
            int T = StdIn.readInt();
            Stopwatch stopwatch = new Stopwatch();
            percolationStats = new PercolationStats(N, T);
            StdOut.printf("mean                    = %f%n", percolationStats.mean());
            StdOut.printf("stddev                  = %f%n", percolationStats.stddev());
            StdOut.printf("95%% confidence interval = %f, %f%n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
            System.out.println(stopwatch.elapsedTime());
        }
    }

    private double getOpenSitesFraction() {
        Percolation percolation = new Percolation(N);
        int openCellsCount = 0;
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(1, N + 1);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                openCellsCount += 1;
            }
        }
        return openCellsCount / Math.pow(N, 2);
    }

    private double[] calculateFractions() {
        if (isExperimentsExecuted) {
            return openSitesFractions;
        }
        for (int i = 0; i < T; i++) {
            openSitesFractions[i] = getOpenSitesFraction();
        }
        isExperimentsExecuted = true;
        return openSitesFractions;
    }
}
