package ca.mcmaster.se2aa4.mazerunner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Convert maze into 2D int array for easier comparison and locate entry and exit index
public class GenerateMaze {
    private static volatile GenerateMaze instance;
    private int[][] maze;
    private int entry_row, entry_col;
    private int exit_row, exit_col;

    private GenerateMaze(String filePath) throws Exception {
        // Private constructor prevents instantiation
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
            }
            // Find exit index
            if (exit_row == -1 && maze[row][lines.get(row).length() - 1] == 0) {
                exit_row = row;
                exit_col = lines.get(row).length() - 1;
            }
        }

        if (entry_row == -1 || exit_row == -1) {
            throw new Exception("Entry or Exit not found in maze.");
        }
    }

    public static GenerateMaze getInstance(String filePath) throws Exception {
        if (instance == null) {
            synchronized (GenerateMaze.class) {
                if (instance == null) {
                    instance = new GenerateMaze(filePath);
                }
            }        
        }
        return instance;
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
