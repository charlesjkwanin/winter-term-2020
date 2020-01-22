package main;

import java.util.ArrayList;

class TourManager {
    private static ArrayList<City> destinationCities = new ArrayList<>();

    static void addCity(City city) {
        destinationCities.add(city);
    }

    static City getCity(int index) {
        return destinationCities.get(index);
    }

    static int numberOfCities() {
        return destinationCities.size();
    }

    static ArrayList<City> getDestinationCities() {
        return destinationCities;
    }


}
