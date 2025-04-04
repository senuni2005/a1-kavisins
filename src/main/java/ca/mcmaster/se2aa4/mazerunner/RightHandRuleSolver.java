package ca.mcmaster.se2aa4.mazerunner;
public class RightHandRuleSolver extends MazeSolver {

    public RightHandRuleSolver(GenerateMaze maze) {
        super(maze);
    }

    @Override
    protected void makeMove() {
        // Specific implementation of move decision (Right-Hand Rule)
        int right_direction = (direction + 1) % 4;

        if (canMove(DIRECTIONS[right_direction])) {
            turnRight();
            moveForward();
        } 
        else if (canMove(DIRECTIONS[direction])) {
            moveForward();
        } 
        else {
            turnLeft();
        }
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
        appendMove('F'); // Append the 'F' for forward move
    }

    private void turnRight() {
        direction = (direction + 1) % 4;
        appendMove('R'); // Append the 'R' for right turn
    }

    private void turnLeft() {
        direction = (direction + 3) % 4;
        appendMove('L'); // Append the 'L' for left turn
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
}
