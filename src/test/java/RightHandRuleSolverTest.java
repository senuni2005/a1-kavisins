import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.GenerateMaze;
import ca.mcmaster.se2aa4.mazerunner.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.RightHandRuleSolver;

class RightHandRuleSolverTest {

    @Test
    void testSolveMazeNotNull() throws Exception {
        Path filePath = Paths.get("examples/tiny.maz.txt");
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        MazeSolver solver = new RightHandRuleSolver(maze);

        String path = solver.solveMaze();

        assertNotNull(path, "Solver result should not be null");
    }

    @Test
    void testSolveMazeNotEmpty() throws Exception {
        Path filePath = Paths.get("examples/tiny.maz.txt");
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        MazeSolver solver = new RightHandRuleSolver(maze);

        String path = solver.solveMaze();

        assertFalse(path.isEmpty(), "Solver result should not be empty");
    }
}
