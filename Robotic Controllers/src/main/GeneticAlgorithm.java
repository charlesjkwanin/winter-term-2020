package main;

import java.util.Random;

public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }

    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(chromosomeLength);
        return population;
    }

    double evaluateFitness(Entity entity) {
        // keep track of correct genes
        int correctGenes = 0;
        for (int i = 0; i < entity.chromosomeLength(); i++) {
            if (entity.getGene(i) == 1) {
                correctGenes++;
            }
        }
        double fitness = (double) correctGenes / entity.chromosomeLength();
        fitness *= fitness;
        entity.setFitness(fitness);
        return fitness;
    }

    void evaluatePopulationFitness(Population population) {
        double popFitness = 0.0;
        for (Entity entity : population.getEntities()) {
            popFitness += evaluateFitness(entity);
        }
        population.setPopulationFitness(popFitness);
    }

    public int getElitismCount() {
        return elitismCount;
    }

    public boolean terminationConditionsMet(Population population) {
        return true;
    }

    /**
     * Select a parent from the population using the Wheel of Fortune method
     *
     * @param population Population to select the parent from
     * @return Return a parent selected from the given population
     */
    private Entity selectParent(Population population) {
        Random rand = new Random();
        // generate two random indices
        int index = rand.nextInt(population.size());
        int index2 = rand.nextInt(population.size());
        while (index < index2) {
            index = rand.nextInt(population.size());
            index2 = rand.nextInt(population.size());
        }
        return population.getEntity(index);
    }

    /**
     * Crossover between two parents where either parent has an equal chance of contributing a gene
     *
     * @param parent1 Parent 1
     * @param parent2 Parent 2
     * @return A child made using a uniform distribution crossover
     */
    Entity equalChanceCrossover(Entity parent1, Entity parent2) {
        Entity child = new Entity(parent1.chromosomeLength());
        for (int i = 0; i < child.chromosomeLength(); i++) {
            int idx = (int) (Math.random() * 2);
            if (idx == 0) {
                child.setGene(idx, parent1.getGene(idx));
            } else {
                child.setGene(idx, parent2.getGene(idx));
            }
        }
        return child;
    }

    /**
     * Create a new generation of entities to enter the next round of evolution from a given population
     *
     * @param population A population to evaluate and select the best entities to enter the next generation
     * @return A new population evolved from the given population
     */
    Population naturalSelection(Population population) {
        Population newPopulation = new Population(population.size());
        for (int index = 0; index < population.size(); index++) {
            Entity parent1 = population.getFittest(index);
            // only apply crossover and mutation if the entity is not considered elite
            if (this.crossoverRate > Math.random() && index > elitismCount) {
                Entity parent2 = selectParent(population);
                Entity child = equalChanceCrossover(parent1, parent2);
                mutate(child); // mutate the child
                newPopulation.setEntity(index, child); // add child to the next generation of solutions
            } else {
                newPopulation.setEntity(index, parent1); // this entity is considered elite, so it is added to next generation without mutation
            }
        }
        return newPopulation;
    }

    /**
     * If a generated random number is less than the mutation rate, then we flip the current value at the current index
     *
     * @param entity An entity to mutate
     */
    void mutate(Entity entity) {
        for (int idx = 0; idx < entity.chromosomeLength(); idx++) {
            if (Math.random() < mutationRate) {
                if (entity.getGene(idx) == 0) {
                    entity.setGene(idx, 1);
                } else {
                    entity.setGene(idx, 1);
                }
            }
        }
    }


}
