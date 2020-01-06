package src.GAStrings.src;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author ckwanin
 *
 */
public class Population {

    private ArrayList<Entity> entities; // list of entities that form this population

	private final static int POPULATION_SIZE = 1000;
    private String target;
    private ArrayList<Entity> matingPool;
	private String generationBestString;


	/**
     *
     * @param target The target string to try to predict
     */
	Population(String target) {
        generationBestString = "";

		this.target = target;

		matingPool = new ArrayList<>();

		entities = new ArrayList<>(POPULATION_SIZE);

        for (int i = 0; i < POPULATION_SIZE; i++) {
            entities.add(new Entity(target));
        }

    }

    private Population() {
        this("");
        this.clear();
    }

    /**
     * A string representation of the Population of Entities
     * @return A string representation of the members of this population
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entity e : entities) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

	/**
	 * Find the best string for the current population
	 * Best is the string closest to the target string
	 */
	private void findBest() {
        String bestStringSoFar = "";
        double bestFitnessScoreSoFar = Integer.MIN_VALUE;
        for (Entity e : entities) {
            double fitnessScore = e.evaluateFitness();
            if (fitnessScore > bestFitnessScoreSoFar) {
                bestStringSoFar = e.toString();
                bestFitnessScoreSoFar = fitnessScore;
            }
        }
        generationBestString = bestStringSoFar;
	}

    /**
     *
     * A mating pool that is proportionate to the fitness score for each string
     */
	private void generateMatingPoolWheelOfFortune() {
		matingPool = new ArrayList<>();
        for (Entity e : entities) {
            double fitnessScore = e.evaluateFitness();
            for (double i = 0; i < fitnessScore; ++i) {
                matingPool.add(e);
            }
        }


    }

	/**
	 *  Generate parents using the Wheel of Fortune method
	 *  DNAs that are closer to the target string are more likely to be chosen as parents
	 * @return An arraylist containing two parents to be used in reproduction
	 */
	private ArrayList<Entity> generateParentsWheel() {
        generateMatingPoolWheelOfFortune();
        Random rand = new Random();

        if (matingPool.size() == 0) {
			return new ArrayList<>();
        }

        // generate two random indices
        int index1 = rand.nextInt(matingPool.size());
        int index2 = rand.nextInt(matingPool.size());
        while (index1 == index2) {
            index1 = rand.nextInt(matingPool.size());
            index2 = rand.nextInt(matingPool.size());
        }

        // query the mating pool for the two random parents.
        ArrayList<Entity> parents = new ArrayList<>();
        parents.add(matingPool.get(index1));
        parents.add(matingPool.get(index2));

        return parents;
    }

    /**
     * Return a list of two parents to be used in the crossover/reproduction methods.
     * @return Parents generated using Monte Carlo Method
     */
    public ArrayList<Entity> generateParentsMonteCarlo() {
		ArrayList<Entity> parents = new ArrayList<>();
        int numberOfParents = 2;
        Random rand = new Random();
        for (int i = 0; i < numberOfParents; ++i) {
            int iterationCount = 0;

            while (true) {
                // generate random number to use to index into the population
                int index = rand.nextInt(POPULATION_SIZE);
				int previousIndex = index;

                while (previousIndex == index) {
                    index = rand.nextInt(POPULATION_SIZE);
                }

                Entity e = entities.get(index);
                double qualifying = rand.nextDouble(); // generate a qualifying random number

                double fitnessScore = e.evaluateFitness();

                if (fitnessScore > qualifying) {
                    parents.add(e);
                    previousIndex = index;
                    break;
                }
                iterationCount++;

                // if the monte carlo method runs too long, just return the current entity
                if (iterationCount > 1000000) {
                    parents.add(e);
                    break;
                }
            }
        }
        return parents;
    }

    /**
     * Pretty Print the elements of an ArrayList
     * @return A string representation of the given ArrayList
     */
	public String prettyEntityList(ArrayList<Entity> list) {
        StringBuilder sb = new StringBuilder();

        for (Entity e : list) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    private void clear() {
		entities = new ArrayList<>();
    }

    private void add(Entity e) {
        this.entities.add(e);
    }

    void run() {
        Population p = this;
        String prevTarget = target;
        int generations = 1;

        while (!p.generationBestString.equals(target)) {
            System.out.println("Generation " + generations);

            Population newPopulation = new Population();
            newPopulation.target = prevTarget;
            int count = 0;

            while (count < Population.POPULATION_SIZE) {
                ArrayList<Entity> parents = p.generateParentsWheel();
                Entity child = parents.get(0).equalChanceCrossover(parents.get(1));
                child.mutate(0.01);
                newPopulation.add(child);
                count++;
            }

            newPopulation.findBest();

            System.out.println("The best string of generation " + generations + " is: " + newPopulation.generationBestString);

            p = newPopulation;

            if (newPopulation.generationBestString.equals(target)) {
                break;
            }
            generations++;

            System.out.println();

            if (generations > 1000) {
                break;
            }
        }

        System.out.println();


        System.out.println("The overall best string after " + generations + " generations is: " + p.generationBestString);
    }


}
