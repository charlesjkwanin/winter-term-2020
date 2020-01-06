package src.GAStrings.src;

/**
 * @author ckwanin
 *
 */
public class Main {

    /**
     * @param args Command Line arguments
     */
    public static void main(String[] args) {
	String target = "to be or not to be";
	
	Population population = new Population(target);
	population.run();

    }


}
