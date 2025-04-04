import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.ResultFormatter;

class ResultFormatterTest {

    @Test
    void testFactorizePathWithRepeatingMoves() {
        ResultFormatter formatter = new ResultFormatter();
        String input = "FFFRRFL";
        String expected = "3F 2R F L ";
        assertEquals(expected, formatter.factorizePath(input), "Factorized path should match the expected format");
    }

    @Test
    void testFactorizePathWithNoRepeatingMoves() {
        ResultFormatter formatter = new ResultFormatter();
        String input = "FRLFRL";
        String expected = "F R L F R L ";
        assertEquals(expected, formatter.factorizePath(input), "Factorized path should match the expected format");
    }

    @Test
    void testFactorizePathWithSingleMove() {
        ResultFormatter formatter = new ResultFormatter();
        String input = "F";
        String expected = "F ";
        assertEquals(expected, formatter.factorizePath(input), "Factorized path should match the expected format for a single move");
    }
}
