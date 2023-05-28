package de.bcxp.challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads data from a CSV-file and returns a two-dimensional List of Strings with its content
 */
public class CSVDataReader implements DataReader {

    /**
     * @param filePath contains the path of the file which should be processed
     * @return a two-dimensional list of strings containing the data from the file from the filePath
     * @throws IOException if something went wrong during reading the file
     * @throws InvalidCSVFormatException if the csv-file is not in the expected format, eg. missing or false delimiter
     */
    @Override
    public List<List<String>> readData(String filePath) throws IOException, InvalidCSVFormatException {
        List<List<String>> data = new ArrayList<>();

        FileReader fileReader = null;
        BufferedReader reader = null;

        try {
            fileReader = new FileReader(filePath);
            reader = new BufferedReader(fileReader);
            String line;
            String delimiter = "";
            line = reader.readLine();

            if (line.equals("")){
                throw new InvalidCSVFormatException("file is empty");
            }

            delimiter = detectDelimiter(line);

            while ((line = reader.readLine()) != null){

                String[] tokens = line.split(delimiter);
                List<String> columns = new ArrayList<>();

                for (String token : tokens) {
                    columns.add(token);
                }

                data.add(columns);
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            throw e;
        } catch (InvalidCSVFormatException e) {
            System.out.println(e.getMessage());
            throw e;

            // Closing the readers to save resources
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing BufferedReader: " + e.getMessage());
                }
            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println("Error closing FileReader: " + e.getMessage());
                }
            }
        }
        return data;
    }

    public String detectDelimiter(String line) throws InvalidCSVFormatException {
        if (line.contains(",")) {
            return ",";
        } else if (line.contains(";")) {
            return ";";
        } else {
            throw new InvalidCSVFormatException("Unsupported delimiter");
        }
    }
}
