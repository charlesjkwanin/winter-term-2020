package src.GAStrings.src;

import java.util.ArrayList;


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
	
	Population p = new Population(target);
	p.run();
	//run(p, target);
    }


}
