import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.ResultFormatter;

class ResultFormatterTest {

    @Test
    void testFactorizePath() {
        ResultFormatter formatter = new ResultFormatter();
        String input = "FFFRRFL";
        String expected = "3F 2R F L ";
        assertEquals(expected, formatter.factorizePath(input));
    }
}
