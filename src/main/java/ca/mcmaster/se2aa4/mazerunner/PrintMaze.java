package ca.mcmaster.se2aa4.mazerunner;

// Prints out maze results from making a move

public class PrintMaze {

    public void printMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
