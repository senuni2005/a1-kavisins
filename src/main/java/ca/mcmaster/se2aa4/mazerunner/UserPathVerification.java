package ca.mcmaster.se2aa4.mazerunner;

class UserPathVerifier {
    private final GenerateMaze maze;
    
    public UserPathVerifier(GenerateMaze maze) {
        this.maze = maze;
    }
    
    public boolean verifyPath(String path) {
        int row = maze.getEntryRow();
        int col = maze.getEntryCol();
        int direction = 1; 

        for (char move : path.toCharArray()) {
            if (move == 'F') {
                if (!canMoveForward(row, col, direction)) {
                    return false;
                }
                if (direction == 0) { row--; }
                else if (direction == 1) { col++; }
                else if (direction == 2) { row++; }
                else if (direction == 3) { col--; }
            } else if (move == 'R') {
                direction = (direction + 1) % 4;
            } else if (move == 'L') {
                direction = (direction + 3) % 4;
            } else {
                return false;
            }
        }

        if (row == maze.getExitRow() && col == maze.getExitCol()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canMoveForward(int row, int col, int direction) {
        int[][] grid = maze.getMaze();
        
        if (direction == 0) { 
            return row > 0 && grid[row - 1][col] == 0;
        } else if (direction == 1) { 
            return col < grid[0].length - 1 && grid[row][col + 1] == 0;
        } else if (direction == 2) { 
            return row < grid.length - 1 && grid[row + 1][col] == 0;
        } else if (direction == 3) { 
            return col > 0 && grid[row][col - 1] == 0;
        }
        
        return false;
    }
}
