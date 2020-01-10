package src.GAStrings.src;

import java.util.Random;

/**
 * @author Charles Kwanin
 * 1/5/20
 */
public class Entity {
    private static final int MAX_LENGTH = 19; // the length of the DNA. Arbitrarily chosen at the moment
    private Character[] DNA = new Character[MAX_LENGTH]; // the DNA of this entity
    private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', ',', '.'};
    private String target;
    private Random rand;
    private int alphabetLength = alphabet.length;

    /**
     * Construct a new entity, with a randomly generated DNA
     */
    public Entity(String target) {
        this.target = target;
        // TODO: maybe use proportions so that some of the most frequently used letters appear more often than not
        this.rand = new Random();
        int randomNumber = rand.nextInt(alphabetLength);
        for (int i = 0; i < MAX_LENGTH; i++) {
            DNA[i] = alphabet[randomNumber];
            randomNumber = rand.nextInt(alphabetLength);
        }

    }

    public Entity() {
        this.rand = new Random();
        int randomNumber = rand.nextInt(alphabetLength);
        for (int i = 0; i < MAX_LENGTH; i++) {
            DNA[i] = alphabet[randomNumber];
            randomNumber = rand.nextInt(alphabetLength);
        }

        this.rand = new Random();
    }

    private void setTarget(String newTarget) {
        this.target = newTarget;
    }

    /**
     * @return the string representation of the DNA
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Character s : DNA) {
            output.append(s);
        }
        return output.toString();
    }

    /**
     * Evaluate the fitness of the DNA of the generated string
     *
     * @return The Fitness score of the DNA (in this case, we are looking at the proportion of characters it has in common with the target phrase)
     */
    double evaluateFitness() {
        int count = 0;
        String dna = this.toString();
        for (int i = 0; i < MAX_LENGTH; ++i) {
            if (dna.charAt(i) == target.charAt(i)) {
                count++;
            }
        }
        return 100 * Math.pow((double) count / MAX_LENGTH, 2);
    }

    /**
     * Crossover using a random midpoint
     *
     * @param otherParent Parent to crossover with
     * @return An entity which is a cross between this parent, and a given parent
     */
    public Entity randomMidpointCrossover(Entity otherParent) {
        Entity child = new Entity();
        child.setTarget(this.target);


        int idx = rand.nextInt(MAX_LENGTH);

        for (int i = 0; i < MAX_LENGTH; i++) {
            if (i < idx) {
                child.DNA[i] = this.toString().charAt(i);
            } else {
                child.DNA[i] = otherParent.toString().charAt(i);
            }
        }
        return child;
    }

    /**
     * Crossover where each parent has an equal chance of contributing each gene
     *
     * @param otherParent Parent to reproduce with
     * @return An entity which is a cross between this parent, and a given parent
     */
    Entity equalChanceCrossover(Entity otherParent) {
        Entity child = new Entity();
        child.setTarget(this.target);


        for (int i = 0; i < MAX_LENGTH; i++) {
            int idx = rand.nextInt(2);
            if (idx == 0) {
                child.DNA[i] = this.toString().charAt(i);
            } else {
                child.DNA[i] = otherParent.toString().charAt(i);
            }
        }

        return child;
    }

    /**
     * Mutate this Entity using the given mutation rate
     *
     */
    void mutate() {

        for (int i = 0; i < MAX_LENGTH; i++) {
            if (rand.nextDouble() < 0.01) {
                this.DNA[i] = alphabet[rand.nextInt(alphabetLength)];
            }
        }
    }


}
