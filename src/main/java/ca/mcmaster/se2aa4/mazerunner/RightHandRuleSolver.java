package ca.mcmaster.se2aa4.mazerunner;

class RightHandRuleSolver implements MazeSolver {
    private static final String[] DIRECTIONS = {"N", "E", "S", "W"};
    private int direction;
    private int current_row, current_col;
    private final int exit_row, exit_col;
    private final int[][] maze;
    private final StringBuilder path;

    public RightHandRuleSolver(GenerateMaze maze) {
        this.current_row = maze.getEntryRow();
        this.current_col = maze.getEntryCol();
        this.exit_row = maze.getExitRow();
        this.exit_col = maze.getExitCol();
        this.maze = maze.getMaze();
        this.path = new StringBuilder();
        InitializePlayer initialize_player = new InitializePlayer(maze);
        this.direction = initialize_player.setInitialDirection();
    }

    private boolean canMove(String move) {
        if (move.equals("N")) {
            return current_row > 0 && maze[current_row - 1][current_col] == 0;
        } else if (move.equals("E")) {
            return current_col < maze[0].length - 1 && maze[current_row][current_col + 1] == 0;
        } else if (move.equals("S")) {
            return current_row < maze.length - 1 && maze[current_row + 1][current_col] == 0;
        } else if (move.equals("W")) {
            return current_col > 0 && maze[current_row][current_col - 1] == 0;
        }
        return false;
    }

    private void moveForward() {
        if (DIRECTIONS[direction].equals("N")) {
            current_row--;
        } else if (DIRECTIONS[direction].equals("E")) {
            current_col++;
        } else if (DIRECTIONS[direction].equals("S")) {
            current_row++;
        } else if (DIRECTIONS[direction].equals("W")) {
            current_col--;
        }
        path.append('F');
    }

    private void turnRight() {
        direction = (direction + 1) % 4;
        path.append('R');
    }

    private void turnLeft() {
        direction = (direction + 3) % 4;
        path.append('L');
    }

    @Override
    public String solveMaze() {
        while (!(current_row == exit_row && current_col == exit_col)) {
            int right_direction = (direction + 1) % 4;
            if (canMove(DIRECTIONS[right_direction])) {
                turnRight();
                moveForward();
            } else if (canMove(DIRECTIONS[direction])) {
                moveForward();
            } else {
                turnLeft();
            }
        }
        return path.toString();
    }
}
