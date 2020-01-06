import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 */

/**
 * @author ckwanin
 *
 */
public class Population {

    ArrayList<Entity> entities; // list of entities that form this population
    public final static int POPULATION_SIZE = 1000;
    String target;
    ArrayList<Entity> matingPool;
    String generationBestString;
    double generationBestFitnessScore;
    Map<Entity, Double> fitnessScores;

    /**
     * 
     * @param target
     */
    public Population(String target) {
	fitnessScores = new HashMap<>();
	generationBestString = "";
	generationBestFitnessScore = 0.0;

	this.target = target;

	matingPool = new ArrayList<Entity>();

	entities = new ArrayList<Entity>(POPULATION_SIZE);

	for (int i=0; i<POPULATION_SIZE; i++) {
	    entities.add(new Entity(target));
	}

    }

    public Population() {
	this("");
	this.clear();
    }

    /**
     * A string representation of the Population of Entities
     * @return
     */
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (Entity e: entities) {
	    sb.append(e.toString()).append("\n");
	}
	return sb.toString();
    }
    
    void findBest() {
	String bestStringSoFar = "";
	double bestFitnessScoreSoFar = Integer.MIN_VALUE;
	for (Entity e: entities) {
	    double fitnessScore = e.evaluateFitness();
	    if (fitnessScore > bestFitnessScoreSoFar) {
		bestStringSoFar = e.toString();
		bestFitnessScoreSoFar = fitnessScore;
	    }
     }
	generationBestString = bestStringSoFar;
	generationBestFitnessScore = bestFitnessScoreSoFar;
    }

    /**
     * 
     * @return A mating pool that is proportionate to the fitness score for each string
     */
    void generateMatingPoolWheelOfFortune() {
	matingPool = new ArrayList<Entity>();
	for (Entity e: entities) {
	    double fitnessScore = e.evaluateFitness();
	    for (double i=0; i<fitnessScore; ++i) {
		matingPool.add(e);
	    }
	}

	
    }

    public ArrayList<Entity> generateParentsWheel() {
	generateMatingPoolWheelOfFortune();
	Random rand = new Random();

	if (matingPool.size() == 0) {
	    return new ArrayList<Entity>();
	}

	int index1 = rand.nextInt(matingPool.size());
	int index2 = rand.nextInt(matingPool.size());
	while (index1 == index2) {
	    index1 = rand.nextInt(matingPool.size());
	    index2 = rand.nextInt(matingPool.size());
	}

	ArrayList<Entity> parents = new ArrayList<>();
	parents.add(matingPool.get(index1));
	parents.add(matingPool.get(index2));

	return parents;
    }

    /**
     * Return a list of two parents to be used in the crossover/reproduction methods.
     * @return
     */
    public ArrayList<Entity> generateParentsMonteCarlo() {
	ArrayList<Entity> parents = new ArrayList<Entity>();
	int numberOfParents = 2;
	Random rand = new Random(); 
	for (int i=0; i<numberOfParents; ++i) {
	    int iterationCount = 0;
	    int previousIndex = 0;
	    while (true) {
		// generate random number to use to index into the population
		int index = rand.nextInt(POPULATION_SIZE);

		while (previousIndex == index) {
		    index = rand.nextInt(POPULATION_SIZE);
		}

		Entity e = entities.get(index);
		double qualifying = rand.nextDouble(); // generate a qualifying random number

		double fitnessScore = e.evaluateFitness();

		if  (fitnessScore > qualifying) {
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
     * @returnp.findBest();
	
	System.out.println(generationBestString + " ");
     */
    public String matingPoolPretty(ArrayList<Entity> list) {
	StringBuilder sb = new StringBuilder();

	for (Entity e: list) {
	    sb.append(e.toString()).append("\n");
	}
	return sb.toString();
    }

    private void clear() {
	entities = new ArrayList<Entity>();
    }

    public void add(Entity e) {
	this.entities.add(e);
    }

    public void run() {
	int generations = 1;
	Population p = new Population("cart");
	Population newPopulation = new Population();
	
	System.out.println(p);
	
	p.findBest();
	
	//System.out.println("The best string of generation" + generations + " is: " + generationBestString);

	while (!generationBestString.equals(target)) {
	    //System.out.println("Generation " + generations);

	    int count = 0;

	    String closest = "";
	    
	    while(count < POPULATION_SIZE) {
		ArrayList<Entity> parents = generateParentsWheel();
		Entity child = parents.get(0).equalChanceCrossover(parents.get(1));
		child.mutate(0.01);
		newPopulation.add(child);
		count++;
	    }
	    
	    System.out.println(newPopulation);
	    newPopulation.findBest();
	    
	    newPopulation = new Population();
	    
	    
	    //System.out.println(generationBestString + " ");
	    
	    generations++;

	    if (generations > 3) {
		break;
	    }
	}

	//System.out.println("The overall best string is: " + generationBestString);
    }


}
