package main;

import java.util.ArrayList;
import java.util.Collections;

import static main.Population.mutationRate;

public class Tour {
    private ArrayList<City> tour = new ArrayList<>(); // a tour stores cities
    private double distance = 0; // total distance
    private double fitness = 0; // fitness of this tour

    Tour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
        //generateTour();
    }

    /**
     * Put all cities in their respective slots in this tour
     */
    void generateTour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            setCity(i, TourManager.getCity(i));
        }
        Collections.shuffle(tour.subList(0, TourManager.numberOfCities()));
    }

    /**
     * Add the given city at the given position in this tour
     *
     * @param position Index in the tour
     * @param city     City to add to put in this position
     */
    void setCity(int position, City city) {
        tour.set(position, city);
        fitness = 0;
        distance = 0;
    }

    /**
     * @return The fitness of this tour
     */
    double getFitness() {
        if (fitness == 0) {
            fitness = 1 / getDistance() * getDistance();
        }
        return fitness;
    }

    /**
     * @return The total distance of this tour, starting from the first city,
     * and returning to the first city after visiting all other cities
     */
    double getDistance() {
        if (distance == 0) {
            int tourDistance = 0;
            for (int i = 0; i < tourSize(); i++) {
                City origin = getCity(i);
                City destination;
                if (i + 1 < tourSize()) {
                    destination = getCity(i + 1);
                } else {
                    destination = getCity(0);
                }
                if (origin == null || destination == null) {
                    tourDistance += 0;
                } else {
                    tourDistance += origin.getDistance(destination);
                }
            }
            distance = tourDistance;
        }
        return distance;
    }

    /**
     * @return How many cities are in this tour
     */
    int tourSize() {
        return tour.size();
    }

    /**
     * Get the city in the given index
     *
     * @param cityIndex An index in the tour
     * @return The city at the given index in this tour
     */
    City getCity(int cityIndex) {
        return tour.get(cityIndex);
    }

    /**
     * Whether a given city is in this tour
     *
     * @param city City to check if it's in the tour
     * @return True if city in tour, false otherwise
     */
    boolean containsCity(City city) {
        return tour.contains(city);
    }

    /**
     * Mutate a Tour using swap mutation
     *
     */
    void mutate() {
        for (int i = 0; i < tourSize(); i++) {
            if (Math.random() < mutationRate) {
                int posToSwap = (int) (Math.random() * tourSize());
                // do the swap
                City city1 = getCity(i);
                City city2 = getCity(posToSwap);

                setCity(i, city2);
                setCity(posToSwap, city1);
            }
        }
    }

    /**
     *
     * @return A string representation of a tour
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (City c : tour) {
            sb.append(c).append(" | ");
        }
        return sb.toString();
    }

    /**
     * Draw the given tour on the screen
     * Connect each city with the next one in the array, and add a connection from the last city to
     * the first
     */
    void render() {
        for (int i = 0; i < tourSize(); i++) {
            City c = getCity(i);
            c.render();
            if (i + 1 < tourSize()) {
                c.connect(getCity(i + 1), i + 1);
            } else {
                c.connect(getCity(0), i + 1);
            }
        }
    }
}
