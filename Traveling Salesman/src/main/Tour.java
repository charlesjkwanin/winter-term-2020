package main;

import java.util.ArrayList;
import java.util.Collections;

import static main.Population.mutationRate;

public class Tour {
    private ArrayList<City> tour = new ArrayList<>();
    private double distance = 0;
    private double fitness = 0;

    public Tour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
        //generateTour();
    }

    void generateTour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            setCity(i, TourManager.getCity(i));
        }
        Collections.shuffle(tour.subList(0, TourManager.numberOfCities()));
    }

    public void setCity(int position, City city) {
        tour.set(position, city);
        fitness = 0;
        distance = 0;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / getDistance();
        }
        return fitness;
    }

    public double getDistance() {
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

    public int tourSize() {
        return tour.size();
    }

    City getCity(int cityIndex) {
        return tour.get(cityIndex);
    }

    Boolean containsCity(City city) {
        return tour.contains(city);
    }

    /**
     * Mutate a Tour using swap mutation
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (City c : tour) {
            sb.append(c).append(" | ");
        }
        return sb.toString();
    }

    /**
     *
     */
    public void render() {
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
