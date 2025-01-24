package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Handles player movement logic with error handling

public class Player {
    private static final Logger logger = LogManager.getLogger();
    protected int current_row;
    protected int current_col;
    private String direction;
    private boolean at_start;
    private final int start_row;
    private final int start_col;

    public Player(int start_row, int start_col, String direction) {
        this.start_row = start_row;
        this.start_col = start_col;
        this.direction = direction;
        this.current_row = start_row;
        this.current_col = start_col;
        this.at_start = true;

        logger.info("Player initialized at [" + start_row + ", " + start_col + "]");
    }

    protected void moveForward(String[][] maze) {
        if (canMoveForward(maze)) {
            maze[current_row][current_col] = " ";
            int new_row = current_row;
            int new_col = current_col;

            if (direction.equals("UP")) {
                new_row--;
            } else if (direction.equals("DOWN")) {
                new_row++;
            } else if (direction.equals("LEFT")) {
                new_col--;
            } else if (direction.equals("RIGHT")) {
                new_col++;
            }

            maze[new_row][new_col] = getDirectionSymbol();
            current_row = new_row;
            current_col = new_col;

            if (current_row != start_row || current_col != start_col) {
                at_start = false;
            }

            logger.info("Player moved to [" + current_row + ", " + current_col + "]");
        } else {
            logger.error("Cannot move forward from [" + current_row + ", " + current_col + "] facing " + direction);
        }
    }

    protected void turnLeft() {
        logger.info("Turning left from " + direction);
        if (direction.equals("UP")) {
            direction = "LEFT";
        } else if (direction.equals("LEFT")) {
            direction = "DOWN";
        } else if (direction.equals("DOWN")) {
            direction = "RIGHT";
        } else if (direction.equals("RIGHT")) {
            direction = "UP";
        }
        logger.info("Now facing " + direction);
    }

    protected void turnRight() {
        logger.info("Turning right from " + direction);
        if (direction.equals("UP")) {
            direction = "RIGHT";
        } else if (direction.equals("RIGHT")) {
            direction = "DOWN";
        } else if (direction.equals("DOWN")) {
            direction = "LEFT";
        } else if (direction.equals("LEFT")) {
            direction = "UP";
        }
        logger.info("Now facing " + direction);
    }

    protected boolean canMoveForward(String[][] maze) {
        boolean is_valid_move = false;
        if (direction.equals("UP")) {
            is_valid_move = current_row > 0 && !maze[current_row - 1][current_col].equals("#");
        } else if (direction.equals("DOWN")) {
            is_valid_move = current_row < maze.length - 1 && !maze[current_row + 1][current_col].equals("#");
        } else if (direction.equals("LEFT")) {
            is_valid_move = current_col > 0 && !maze[current_row][current_col - 1].equals("#");
        } else if (direction.equals("RIGHT")) {
            is_valid_move = current_col < maze[current_row].length - 1 && !maze[current_row][current_col + 1].equals("#");
        }
        logger.debug("Checking if can move forward from [" + current_row + ", " + current_col + "] facing " + direction + ": " + is_valid_move);
        return is_valid_move;
    }

    protected String getDirectionSymbol() {
        if (direction.equals("UP")) {
            return "↑";
        } else if (direction.equals("DOWN")) {
            return "↓";
        } else if (direction.equals("LEFT")) {
            return "←";
        } else if (direction.equals("RIGHT")) {
            return "→";
        } else {
            return "P";
        }
    }

    protected boolean isBorder(String[][] maze) {
        boolean is_border = (current_row == 0) || (current_row == maze.length - 1) || (current_col == 0) || (current_col == maze[current_row].length - 1);
        logger.debug("Checking if at border: " + is_border);
        return is_border;
    }

    protected boolean hasSucceeded(String[][] maze) {
        return isBorder(maze) && (current_row != start_row || current_col != start_col);
    }
}
