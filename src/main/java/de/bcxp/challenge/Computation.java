package de.bcxp.challenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Computes the smallest temperature spread within a month
 * and determines the country with the highest population density
 * both from a given two-dimensional list of strings
 */
public class Computation {
    /**
     * @param filePath contains the path of the file which should be processed
     * @return the smallest temperature spread between maximum and minimum of a day
     * @throws IOException if something went wrong during reading the file
     * @throws InvalidCSVFormatException if the csv-file is not in the expected format, eg. missing or false delimiter
     * @throws NumberFormatException if some data inside the list has not the correct datatype
     */
    public static int computeSmallestTempSpread(String filePath) throws IOException, InvalidCSVFormatException, NumberFormatException {

        int smallestTempSpread = Integer.MAX_VALUE;
        int dayWithSmallestTempSpread = 0;

        try {
            DataReader dataReader = new CSVDataReader();
            List<List<String>> data = dataReader.readData(filePath);

            for (List<String> row : data) {

                try {
                    int tempSpread = Integer.parseInt(row.get(1)) - Integer.parseInt(row.get(2));

                    if (tempSpread < smallestTempSpread) {
                        smallestTempSpread = tempSpread;
                        dayWithSmallestTempSpread = Integer.parseInt(row.get(0));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("error while parsing MxT or MnT at day " + row.get(0) + " "
                            + e.getMessage());
                    throw e;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            throw e;
        } catch (InvalidCSVFormatException e) {
            System.out.println("Invalid CSV format: " + e.getMessage());
            throw e;
        }
        return dayWithSmallestTempSpread;
    }

    /**
     * @param filePath contains the path of the file which should be processed
     * @return the highest population density in number of people per square kilometre in of a given set of countries
     * @throws IOException if something went wrong during reading the file
     * @throws InvalidCSVFormatException if the csv-file is not in the expected format, eg. missing or false delimiter
     * @throws NumberFormatException if some data inside the list has not the correct datatype
     */
    public static String computeCountryWithHighestDensity(String filePath) throws IOException, InvalidCSVFormatException, NumberFormatException {

        String countryWithHighestDensity = "";
        double highestDensity = 0.0;

        try {
            DataReader dataReader = new CSVDataReader();
            List<List<String>> data = dataReader.readData(filePath);

            for (List<String> row : data) {
                try {
                    String country = row.get(0);
                    double population = Double.parseDouble(row.get(3));
                    double area = Double.parseDouble(row.get(4));
                    double populationDensity = population / area;

                    if (populationDensity > highestDensity) {
                        highestDensity = populationDensity;
                        countryWithHighestDensity = country;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("error while parsing population or area number in country \"" + row.get(0) + "\" "
                    + e.getMessage());
                    throw e;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            throw e;
        } catch (InvalidCSVFormatException e) {
            System.out.println("Invalid CSV format: " + e.getMessage());
            throw e;
        }

        return countryWithHighestDensity;
    }
}
