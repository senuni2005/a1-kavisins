package ca.mcmaster.se2aa4.mazerunner;

class InitializePlayer {
    private final GenerateMaze maze;

    public InitializePlayer(GenerateMaze maze) {
        this.maze = maze;
    }

    public int setInitialDirection() {
        int entry_col = maze.getEntryCol();

        if (entry_col == 0) {
            return 1; 
        } else if (entry_col == maze.getMaze()[0].length - 1) {
            return 3;
        }

        return 1;
    }
}
