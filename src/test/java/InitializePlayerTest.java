import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.GenerateMaze;
import ca.mcmaster.se2aa4.mazerunner.InitializePlayer;

class InitializePlayerTest {
    Path filePath = Paths.get("examples/straight.maz.txt");

    @Test
    void testSetInitialDirection() throws Exception {
        GenerateMaze maze = new GenerateMaze(filePath.toString());        
        InitializePlayer player = new InitializePlayer(maze);
        int direction = player.setInitialDirection();
        assertEquals(1, direction, "Initial direction should be East when entry is on the left border.");
    }

}
