package ca.mcmaster.se2aa4.mazerunner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Convert maze into 2D int array for easier comparison and locate entry and exit index
public class GenerateMaze {
    private static final Logger logger = LogManager.getLogger(GenerateMaze.class);
    private final int[][] maze;
    private int entry_row, entry_col;
    private int exit_row, exit_col;

    public GenerateMaze(String filePath) throws Exception {
        logger.info("Starting maze generation from file: " + filePath);
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        maze = new int[lines.size()][lines.get(0).length()];
        entry_row = entry_col = exit_row = exit_col = -1;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                maze[row][col] = (lines.get(row).charAt(col) == '#') ? 1 : 0;
            }

            // Find entry index
            if (entry_row == -1 && maze[row][0] == 0) {
                entry_row = row;
                entry_col = 0;
                logger.debug("Entry found at: (" + entry_row + ", " + entry_col + ")");
            }
            // Find exit index
            if (exit_row == -1 && maze[row][lines.get(row).length() - 1] == 0) {
                exit_row = row;
                exit_col = lines.get(row).length() - 1;
                logger.debug("Exit found at: (" + exit_row + ", " + exit_col + ")");
            }
        }

        if (entry_row == -1 || exit_row == -1) {
            logger.error("Entry or Exit not found in maze.");
            throw new Exception("Entry or Exit not found in maze.");
        }

        logger.info("Maze generation completed.");
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getEntryRow() {
        return entry_row;
    }

    public int getEntryCol() {
        return entry_col;
    }

    public int getExitRow() {
        return exit_row;
    }

    public int getExitCol() {
        return exit_col;
    }
}