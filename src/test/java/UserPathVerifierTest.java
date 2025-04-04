import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.GenerateMaze;
import ca.mcmaster.se2aa4.mazerunner.UserPathVerifier;

class UserPathVerifierTest {
    Path filePath = Paths.get("examples/straight.maz.txt");

    @Test
    void testVerifyValidPath() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        UserPathVerifier verifier = new UserPathVerifier(maze);
        
        String validPath = "FFFF";
        
        assertTrue(verifier.verifyPath(validPath), "The valid path should lead to the exit.");
    }

    @Test
    void testVerifyInvalidPath() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());
        UserPathVerifier verifier = new UserPathVerifier(maze);
        
        String invalidPath = "FFFRRR";
        assertFalse(verifier.verifyPath(invalidPath), "The invalid path should not reach the exit.");
    }
}
