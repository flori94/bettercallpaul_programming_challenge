package de.bcxp.challenge;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Interface for different classes to implement different readData-Methods,
 * depending on the given file format (CSV, JSON etc.).
 * They all return a two-dimensional List of Strings for further processing (eg. Temperature calculations)
 */
public interface DataReader {

    List<List<String>> readData (String filePath) throws FileNotFoundException, IOException, InvalidCSVFormatException ;
}
