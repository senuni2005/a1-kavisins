package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Generates and initializes the Maze Runner game.

public class GenerateMaze {
    private static final Logger logger = LogManager.getLogger();

    public String[][] generateMazeFromFile(String filePath) throws IOException {
        logger.info("Reading maze from file: " + filePath);

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int rows = 0;

        while ((line = reader.readLine()) != null) {
            rows++;
        }
        reader.close();

        reader = new BufferedReader(new FileReader(filePath));
        String[][] maze = new String[rows][];
        int currentRow = 0;

        while ((line = reader.readLine()) != null) {
            maze[currentRow] = line.split("");
            currentRow++;
        }
        reader.close();

        logger.info("Maze successfully generated.");
        return maze;
    }

    public int[] getStartPosition(String[][] maze) {
        logger.info("Finding starting position in the maze.");

        for (int col = 0; col < maze[0].length; col++) {
            if (maze[0][col].equals(" ")) {
                logger.info("Start position at top border: [0, " + col + "]");
                return new int[]{0, col};
            }
        }
        for (int col = 0; col < maze[maze.length - 1].length; col++) {
            if (maze[maze.length - 1][col].equals(" ")) {
                logger.info("Start position at bottom border: [" + (maze.length - 1) + ", " + col + "]");
                return new int[]{maze.length - 1, col};
            }
        }
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][0].equals(" ")) {
                logger.info("Start position at left border: [" + row + ", 0]");
                return new int[]{row, 0};
            }
        }
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][maze[row].length - 1].equals(" ")) {
                logger.info("Start position at right border: [" + row + ", " + (maze[row].length - 1) + "]");
                return new int[]{row, maze[row].length - 1};
            }
        }
        return null;
    }
}
