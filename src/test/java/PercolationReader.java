import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PercolationReader {
    private PercolationReader() {

    }

    public static Percolation read(String name) throws IOException {
        InputStream data = PercolationReader.class.getResourceAsStream(name);
        BufferedReader in = null;
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        int gridSize = 1;
        try {
            in = new BufferedReader(new InputStreamReader(data));
            gridSize = Integer.valueOf(in.readLine());
            int rowCounter = 0;
            while (in.ready()) {
                String s = in.readLine();
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '1') {
                        coordinates.add(new Coordinate(rowCounter + 1, i + 1));
                    }
                }
                rowCounter++;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        Percolation percolation = new Percolation(gridSize);
        Random random = new Random();
        long seed = random.nextLong();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test is running with seed = " + seed);
        Collections.shuffle(coordinates, new Random(seed));
        for (Coordinate coordinate : coordinates) {
            percolation.open(coordinate.getX(), coordinate.getY());
        }
        return percolation;
    }

    public static boolean[][] readFullnessMatrix(String name) throws IOException {
        InputStream data = PercolationReader.class.getResourceAsStream(name);
        BufferedReader in = null;
        boolean[][] fullnessMatrix;
        int gridSize = 1;
        try {
            in = new BufferedReader(new InputStreamReader(data));
            gridSize = Integer.valueOf(in.readLine());
            fullnessMatrix = new boolean[gridSize][gridSize];
            int rowCounter = 0;
            while (in.ready()) {
                String s = in.readLine();
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '1') {
                        fullnessMatrix[rowCounter][i] = true;
                    }
                }
                rowCounter++;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return fullnessMatrix;
    }

    private static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int getX() {
            return x;
        }

        private int getY() {
            return y;
        }

    }
}
