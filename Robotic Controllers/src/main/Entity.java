package main;

public class Entity {

    private int[] chromosome;
    private double fitness = -1;

    public Entity(int[] chromosome) {
        this.chromosome = chromosome;
    }

    Entity(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength];

        // randomly generate a chromosome of length chromosomeLength
        for (int i = 0; i < chromosomeLength; i++) {
            if (Math.random() < 0.5) {
                this.setGene(i, 1);
            } else {
                this.setGene(i, 0);
            }
        }
    }

    public void setGene(int index, int value) {
        this.chromosome[index] = value;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public int chromosomeLength() {
        return this.chromosome.length;
    }

    public int getGene(int idx) {
        return this.chromosome[idx];
    }

    double getFitness() {
        return this.fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : chromosome
        ) {
            sb.append(i);
        }
        return sb.toString();
    }
}
