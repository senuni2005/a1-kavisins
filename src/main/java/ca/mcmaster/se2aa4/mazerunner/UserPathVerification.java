package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Checks user path input to see if correct
class UserPathVerifier {
    private static final Logger logger = LogManager.getLogger(UserPathVerifier.class);
    private final GenerateMaze maze;
    
    public UserPathVerifier(GenerateMaze maze) {
        this.maze = maze;
    }
    
    public boolean verifyPath(String path) {
        int row = maze.getEntryRow();
        int col = maze.getEntryCol();
        int direction = 1; // Start facing East

        logger.info("Starting path verification from entry point (" + row + ", " + col + ")");
        
        for (char move : path.toCharArray()) {
            logger.debug("Processing move: " + move + " from (" + row + ", " + col + ") facing " + direction);
            
            if (move == 'F') {
                if (!canMoveForward(row, col, direction)) {
                    logger.error("Cannot move forward at (" + row + ", " + col + "), invalid path.");
                    return false; // Can't move forward, invalid path
                }
                // Move forward
                if (direction == 0) { row--; } // North
                else if (direction == 1) { col++; } // East
                else if (direction == 2) { row++; } // South
                else if (direction == 3) { col--; } // West
            } else if (move == 'R') {
                direction = (direction + 1) % 4; // Turn right
                logger.debug("Turned right, now facing " + direction);
            } else if (move == 'L') {
                direction = (direction + 3) % 4; // Turn left
                logger.debug("Turned left, now facing " + direction);
            } else {
                logger.error("Invalid move in the path: " + move);
                return false; // Invalid move in the path
            }
        }

        // Check if the final position is the exit
        if (row == maze.getExitRow() && col == maze.getExitCol()) {
            logger.info("Path successfully reaches the exit at (" + row + ", " + col + ")");
            return true;
        } else {
            logger.error("Path does not reach the exit. Final position: (" + row + ", " + col + ")");
            return false;
        }
    }

    private boolean canMoveForward(int row, int col, int direction) {
        int[][] grid = maze.getMaze();
        
        if (direction == 0) { 
            return row > 0 && grid[row - 1][col] == 0; // North
        } else if (direction == 1) { 
            return col < grid[0].length - 1 && grid[row][col + 1] == 0; // East
        } else if (direction == 2) { 
            return row < grid.length - 1 && grid[row + 1][col] == 0; // South
        } else if (direction == 3) { 
            return col > 0 && grid[row][col - 1] == 0; // West
        }
        
        return false;
    }
}
