package de.bcxp.challenge;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
/**
 * All test cases for the CSVDateReader-class
 */
public class ComputationTest {

    @Test
    public void testComputeSmallestTempSpread() throws IOException, InvalidCSVFormatException, NumberFormatException {
        String filePath = "src/test/resources/de/bcxp/challenge/weatherValid.csv";

        int result = Computation.computeSmallestTempSpread(filePath);

        assertEquals(14, result);
    }

    @Test
    public void testComputeSmallestTempSpreadFalseFormat() throws IOException {
        String filePath = "src/test/resources/de/bcxp/challenge/weatherFalseFormat.csv";

        assertThrows(NumberFormatException.class, () -> {
            Computation.computeSmallestTempSpread(filePath);
        });
    }

    @Test
    public void testComputeCountryWithHighestDensity() throws InvalidCSVFormatException, IOException {
        String filePath = "src/test/resources/de/bcxp/challenge/countriesValid.csv";

        String result = Computation.computeCountryWithHighestDensity(filePath);

        assertEquals("Croatia", result);
    }

    @Test
    public void testComputeCountryWithHighestDensityFalseFormat() throws IOException {
        String filePath = "src/test/resources/de/bcxp/challenge/countriesFalseFormat.csv";

        assertThrows(NumberFormatException.class, () -> {
            Computation.computeCountryWithHighestDensity(filePath);
        });
    }
}