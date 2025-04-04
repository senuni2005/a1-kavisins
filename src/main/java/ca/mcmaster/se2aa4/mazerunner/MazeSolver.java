package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MazeSolver {
    private static final Logger logger = LogManager.getLogger(MazeSolver.class);
    protected static final String[] DIRECTIONS = {"N", "E", "S", "W"};
    protected int direction;
    protected int current_row, current_col;
    protected final int exit_row, exit_col;
    protected final int[][] maze;
    protected final StringBuilder path;

    public MazeSolver(GenerateMaze maze) {
        this.current_row = maze.getEntryRow();
        this.current_col = maze.getEntryCol();
        this.exit_row = maze.getExitRow();
        this.exit_col = maze.getExitCol();
        this.maze = maze.getMaze();
        this.path = new StringBuilder();
        InitializePlayer initialize_player = new InitializePlayer(maze);
        this.direction = initialize_player.setInitialDirection();
        logger.info("MazeSolver initialized: Starting at (" + current_row + ", " + current_col + ") facing " + DIRECTIONS[direction]);
    }

    // Template Method: Defines the skeleton of the algorithm
    public final String solveMaze() {
        logger.info("Starting maze solving...");
        initializePlayer();

        while (!isAtExit()) {
            makeMove();  // Varies by subclass (RightHandRuleSolver, etc.)
        }

        logger.info("Maze solved. Path taken: " + path.toString());
        return path.toString();
    }

    protected abstract void makeMove();

    private void initializePlayer() {
        logger.info("Player initialized at (" + current_row + ", " + current_col + ")");
    }

    private boolean isAtExit() {
        return current_row == exit_row && current_col == exit_col;
    }

    protected void appendMove(char move) {
        path.append(move);
        logger.debug("Path so far: " + path.toString());
    }
}
