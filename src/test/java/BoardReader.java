import java.io.*;

public class BoardReader {

    public static Board readBoard(String fileName) throws IOException {
        InputStream inputStream = BoardReader.class.getResourceAsStream(fileName);
        BufferedReader reader = null;
        int[][] blocks = null;
        int dimension;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            dimension = Integer.valueOf(reader.readLine());
            blocks = new int[dimension][dimension];
            int rowCounter = 0;
            while (rowCounter < dimension) {
                String line = reader.readLine().trim();
                if (line.length() > 0) {
                    String[] blockStrings = line.split(" +");
                    for (int j = 0; j < dimension; j++) {
                        blocks[rowCounter][j] = Integer.valueOf(blockStrings[j]);
                    }
                    rowCounter++;
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }

        }
        return new Board(blocks);
    }

    public static void main(String[] args) throws IOException {
        Board board = BoardReader.readBoard("test-board-3.txt");
        Solver solver = new Solver(board);
        for (Board board1 : solver.solution()) {
            System.out.println(board1.toString());
        }
    }

}
