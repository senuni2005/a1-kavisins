package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class InitializePlayer {
    private static final Logger logger = LogManager.getLogger(InitializePlayer.class);
    private final GenerateMaze maze;

    public InitializePlayer(GenerateMaze maze) {
        this.maze = maze;
        logger.info("Initializing player with maze entry at: (" + maze.getEntryRow() + ", " + maze.getEntryCol() + ")");
    }

    public int setInitialDirection() {
        int entry_col = maze.getEntryCol();
        logger.debug("Determining initial direction based on entry column: " + entry_col);

        if (entry_col == 0) {
            logger.info("Entry is at the left border, facing East.");
            return 1;
        } else if (entry_col == maze.getMaze()[0].length - 1) {
            logger.info("Entry is at the right border, facing West.");
            return 3;
        }

        logger.info("Entry is not on the border, defaulting to East.");
        return 1;
    }
}
