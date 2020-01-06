import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ckwanin
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	String target = "to be or not to be";
	
	Population p = new Population(target);
	run(p, target);
    }
    
     static void run(Population p, String target) {
	int generations = 1;
	
	while (!p.generationBestString.equals(target)) {
	    System.out.println("Generation " + generations);
	    
	    Population newPopulation = new Population();
	    
	    int count = 0;
	    
	    while(count < Population.POPULATION_SIZE) {
		ArrayList<Entity> parents = p.generateParentsWheel();
		Entity child = parents.get(0).equalChanceCrossover(parents.get(1));
		child.mutate(0.01);
		newPopulation.add(child);
		count++;
	    }
	    
	    newPopulation.findBest();
	    
	    System.out.println("The best string of generation " + generations + " is: " + newPopulation.generationBestString);
	    
	    if (newPopulation.generationBestString == target) {break;}

	    generations++;
	    
	    
	    System.out.println();
	    
	    p = newPopulation;
	    
	    p.target = target;
	    
	    p.findBest();
	    
	    

	    if (generations > 1000) {
		break;
	    }
	}
	
	System.out.println();
	System.out.println("The overall best string after " + generations + " generations is: " + p.generationBestString);
    }

}
