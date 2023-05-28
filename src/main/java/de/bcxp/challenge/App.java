package de.bcxp.challenge;
import java.io.IOException;

public final class App {
    public static void main(String... args) throws InvalidCSVFormatException, IOException, NumberFormatException {

        String weatherFilePath = "src/main/resources/de/bcxp/challenge/weather.csv";
        String countriesFilePath = "src/main/resources/de/bcxp/challenge/countries.csv";

        int smallestTempSpread = Computation.computeSmallestTempSpread(weatherFilePath);
        System.out.println("Day with smallest temperature spread: " + smallestTempSpread);

        String countryWithHighestPopulationDensity = Computation.computeCountryWithHighestDensity(countriesFilePath);
        System.out.println("Country with highest population density: " + countryWithHighestPopulationDensity);
    }
}
