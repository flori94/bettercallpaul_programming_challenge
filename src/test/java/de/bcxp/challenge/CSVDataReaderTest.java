package de.bcxp.challenge;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All test cases for the CSVDateReader-class
 */
public class CSVDataReaderTest {

    @Test
    public void testReadDataWithValidFile() throws InvalidCSVFormatException, IOException {
        String filePath = "src/test/resources/de/bcxp/challenge/weatherValid.csv";

        CSVDataReader csvDataReader = new CSVDataReader();

        List<List<String>> data = csvDataReader.readData(filePath);
        assertNotNull(data);
        assertEquals(30, data.size());
    }

    @Test
    public void testReadDataWithEmptyFile() throws InvalidCSVFormatException, IOException {
        String filePath = "src/test/resources/de/bcxp/challenge/empty.csv";
        CSVDataReader csvDataReader = new CSVDataReader();

        assertThrows(InvalidCSVFormatException.class, () -> {
            List<List<String>> data = csvDataReader.readData(filePath);
        });
    }

    @Test
    public void testReadDataFileNotFound() throws FileNotFoundException {
        String filePath = "src/test/resources/de/bcxp/challenge/nofile.csv";
        CSVDataReader csvDataReader = new CSVDataReader();

        assertThrows(FileNotFoundException.class, () -> {
            List<List<String>> data = csvDataReader.readData(filePath);
        });
    }

    @Test
    public void testReadDataFalseDelimiter() throws InvalidCSVFormatException {
        String filePath = "src/test/resources/de/bcxp/challenge/weatherFalseDelimiter.csv";
        CSVDataReader csvDataReader = new CSVDataReader();

        assertThrows(InvalidCSVFormatException.class, () -> {
            List<List<String>> data = csvDataReader.readData(filePath);
        });
    }
}
