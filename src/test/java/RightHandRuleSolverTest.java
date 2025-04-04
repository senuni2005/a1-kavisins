import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.GenerateMaze;
import ca.mcmaster.se2aa4.mazerunner.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.RightHandRuleSolver;

class RightHandRuleSolverTest {

    @BeforeEach
    void setUp() {
        // Reset the Singleton instance before each test
        GenerateMaze.resetInstance();
    }

    @Test
    void testSolveMazeCorrectness() throws Exception {
        String expectedPath = "FRFFFFFFLLFFFFFFFFRFFRFFLLFFRFFRFFFFRFFLFFFFLFFLLFFRFFFFRFFLFFRFFRFFFFRFFLLFFLFFRFFRFFFFRFFLLFFLFFRFFLLFFRFFRFFLLFFFFRFFRFFLLFFFFRFFRFFLLFFFFRFFRFFLLFFRFFFFFFFFFFRFFRFFFFFFFFLLFFFFFFFFLFFRFFFFRFFRFFLLFFRFFRFFFFFFFFFFFFFFLLFFFFFFFFFFFFRFFRFFFFFFLLFFFFRFFRFFFFFFRFFLFFFFFFLLFFFFFFRFFRFFFFFFFFLLFFFFFFFFFFFFRFFRFFFFFFFFFFLLFFFFFFRFFRFFFFLLFFFFLFFRFFFFLFFRFFLFFRFFLFFRFFLFFFFRFFRFFLLFFFFRFFRFFFFFFRFFLLFFRFFRFFFFLLFFRFFRFFFFLLFFFFRFFRFFLLFFRFFRFFFFRFFLFFLLFFRFFRFFFFFFLFFRFFFFFFFFLLFFFFFFFFRFFRFFFFFFFFFFRFFFFRFFLLFFRFFLLFFRFFRFFLFFFFRFFLLFFFFLLFFRFFFFRFFRFFLLFFFFRFFRFFFFFFLLFFFFFFRFFFFRFFRFFLFFLLFFRFFFFRFFRFFLLFFRFFRFFFFLLFFFFLFFFFRFFRFFFFLLFFRFFLLFFRFFRFFLLFFFFFFRFFRFFFFFFFFRFFFFFFRFFLLFFLFFRF";
        Path filePath = Paths.get("examples/medium.maz.txt");
        GenerateMaze maze = GenerateMaze.getInstance(filePath.toString());
        MazeSolver solver = new RightHandRuleSolver(maze);
        String path = solver.solveMaze();
        assertEquals(expectedPath, path, "Solver path should match the expected solution");
    }

    @Test
    void testSolverWithDifferentMaze() throws Exception {
        String expectedPath = "FFFFFLLFFRFFRFFLLFFRFFRFFF";
        Path filePath = Paths.get("examples/tiny.maz.txt");
        GenerateMaze maze = GenerateMaze.getInstance(filePath.toString());
        MazeSolver solver = new RightHandRuleSolver(maze);
        String path = solver.solveMaze();
        assertEquals(expectedPath, path, "Solver path for maze should match the expected solution");
    }

    @Test
    void testSolverPathContainsValidMoves() throws Exception {
        Path filePath = Paths.get("examples/large.maz.txt");
        GenerateMaze maze = GenerateMaze.getInstance(filePath.toString());
        MazeSolver solver = new RightHandRuleSolver(maze);
        String path = solver.solveMaze();
        assertTrue(path.matches("[FRL]+"), "Solver path should contain only valid moves (F, R, L)");
    }

    @AfterEach
    void reset() {
        // Reset the Singleton instance after each test
        GenerateMaze.resetInstance();
    }
}
