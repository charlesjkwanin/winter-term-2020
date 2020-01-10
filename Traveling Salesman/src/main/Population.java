package main;

public class Population {
    static double mutationRate = 0.01;
    private Tour[] tours;
    private int tournamentSize = 5;
    private int generations = 1;

    Population(int populationSize, boolean initialize) {
        tours = new Tour[populationSize];
        if (initialize) {
            for (int i = 0; i < populationSize; i++) {
                Tour newTour = new Tour();
                newTour.generateTour();
                saveTour(i, newTour);
            }
        }
    }

    Tour getTour(int tourIndex) {
        return tours[tourIndex];
    }

    void saveTour(int idx, Tour tour) {
        tours[idx] = tour;
    }

    Tour getFittest() {
        Tour fittest = tours[0];
        for (int i = 1; i < populationSize(); i++) {
            if (getTour(i).getFitness() > fittest.getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    int populationSize() {
        return tours.length;
    }

    /**
     * Run a tournament to select a Tour to add to the new generation
     *
     * @return The tour that wins the tournament
     */
    private Tour tournamentSelection() {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int idx = (int) (MonteCarlo() * populationSize());
            tournament.saveTour(i, getTour(idx));
        }
        Tour fittest = tournament.getFittest();
        return fittest;
    }

    /**
     * Generate a random number using the Monte Carlo simulation
     *
     * @return A random nunber between 0 and 1
     */
    private double MonteCarlo() {
        double random;
        while (true) {
            double rand = Math.random();
            double qualifying = Math.random();
            if (qualifying > rand) {
                random = rand;
                break;
            }
        }
        return random;
    }

    /**
     * Create a new child using the genes from the two given parents
     *
     * @param parent1 Parent 1
     * @param parent2 Parent 2
     * @return A child created using the genes of the parents
     */
    private Tour crossover(Tour parent1, Tour parent2) {
        Tour child = new Tour();

        int startPosition = (int) (Math.random() * parent1.tourSize());
        int endPosition = (int) (Math.random() * parent1.tourSize());

        // add tours from the first parent to make the new child
        for (int i = 0; i < child.tourSize(); i++) {
            if (startPosition < endPosition && i > startPosition && i < endPosition) {
                child.setCity(i, parent1.getCity(i));
            } else {
                if (!(i < startPosition && i > endPosition)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // add tours from parent2 to complete the crossover
        for (int i = 0; i < parent2.tourSize(); i++) {
            // if this spot is empty in child
            if (!child.containsCity(parent2.getCity(i))) {
                // loop till we find an empty spot in the child
                for (int j = 0; j < child.tourSize(); j++) {
                    // we found an empty spot. add gene here
                    if (child.getCity(j) == null) {
                        child.setCity(j, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    /**
     * Evolve a new generation of Tours
     */
    void naturalSelection() {
        Population newPopulation = new Population(this.populationSize(), false);

        for (int i = 0; i < newPopulation.populationSize(); i++) {
            // select parents
            Tour parent1 = tournamentSelection();
            Tour parent2 = tournamentSelection();

            Tour child = crossover(parent1, parent2);
            child.mutate();
            newPopulation.saveTour(i, child);
        }
        generations++;
        this.tours = newPopulation.tours;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Tour t : tours) {
            sb.append(t).append("\n");
        }
        return sb.toString();
    }

    boolean isFinished() {
        return generations == 500;
    }


    int getGenerations() {
        return generations;
    }

}
