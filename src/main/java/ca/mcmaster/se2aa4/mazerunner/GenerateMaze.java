package ca.mcmaster.se2aa4.mazerunner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class GenerateMaze {
    private final int[][] maze;
    private int entry_row, entry_col;
    private int exit_row, exit_col;

    public GenerateMaze(String filePath) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        maze = new int[lines.size()][lines.get(0).length()];
        entry_row = entry_col = exit_row = exit_col = -1;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                maze[row][col] = (lines.get(row).charAt(col) == '#') ? 1 : 0;
            }

            if (entry_row == -1 && maze[row][0] == 0) {
                entry_row = row;
                entry_col = 0;
            }
            if (exit_row == -1 && maze[row][lines.get(row).length() - 1] == 0) {
                exit_row = row;
                exit_col = lines.get(row).length() - 1;
            }
        }

        if (entry_row == -1 || exit_row == -1) {
            throw new Exception("Entry or Exit not found in maze.");
        }
    }

    protected int[][] getMaze() {
        return maze;
    }

    protected int getEntryRow() {
        return entry_row;
    }

    protected int getEntryCol() {
        return entry_col;
    }

    protected int getExitRow() {
        return exit_row;
    }

    protected int getExitCol() {
        return exit_col;
    }
}