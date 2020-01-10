package main;

import java.util.Arrays;
import java.util.Collections;

public class Population {
    private Entity[] population;
    private double populationFitness = -1;

    Population(int populationSize) {
        this.population = new Entity[populationSize];
    }

    public Population(int populationSize, int chromosomeLenght) {
        this(populationSize);

        for (int i = 0; i < populationSize; i++) {
            Entity entity = new Entity(chromosomeLenght);
            this.population[i] = entity;
        }
    }

    Entity[] getEntities() {
        return this.population;
    }

    Entity getFittest(int index) {
        Arrays.sort(this.population, (o1, o2) -> Double.compare(o2.getFitness(), o1.getFitness()));
        return population[index];
    }

    public double getPopulationFitness() {
        return populationFitness;
    }

    void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }

    int size() {
        return this.population.length;
    }

    Entity setEntity(int idx, Entity entity) {
        return this.population[idx] = entity;
    }

    Entity getEntity(int idx) {
        return this.population[idx];
    }

    public void shuffle() {
        Collections.shuffle(Arrays.asList(population));
    }
}
