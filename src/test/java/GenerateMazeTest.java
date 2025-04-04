import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.GenerateMaze;

class GenerateMazeTest {

    int[][] hardcodedMaze = {
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1}
    };

    Path filePath = Paths.get("examples/straight.maz.txt");

    @Test
    void testGenerateMazeInitialization() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        for (int i = 0; i < hardcodedMaze.length; i++) {
            assertArrayEquals(hardcodedMaze[i], maze.getMaze()[i], "Maze row " + i + " should match the expected layout.");
        }
    }

    @Test
    void testMazeEntryRow() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        assertEquals(maze.getEntryRow(), 2, "Entry row should be at 2.");
    }

    @Test
    void testMazeEntryCol() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        assertEquals(maze.getEntryCol(), 0, "Entry column should be at 0.");
    }

    @Test
    void testMazeExitRow() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        assertEquals(maze.getExitRow(), 2, "Exit row should be at 3.");
    }

    @Test
    void testMazeExitCol() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        assertEquals(maze.getExitCol(), 4, "Exit column should be at 4.");
    }
}
