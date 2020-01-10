package src.GAStrings.src;

import processing.core.PApplet;
import processing.core.PFont;

public class GUI extends PApplet {
    String target;
    Population population;
    PFont f;

    public static void main(String[] args) {
        PApplet.main("src.GAStrings.src.GUI");
    }

    @Override
    public void settings() {
        size(640, 200);
        target = "to be or not to be.";
        population = new Population(target);

    }

    @Override
    public void setup() {
        f = createFont("Tahoma", 16);
    }

    @Override
    public void draw() {
        background(255);
        population.naturalSelection();
        displayInfo();

        if (population.isFinished()) {
            noLoop();
        }
    }

    void displayInfo() {
        background(255);
        // Display current status of population
        String fittest = population.getBest();
        textFont(f);
        textAlign(LEFT);
        fill(0);


        textSize(16);
        text("Best phrase:", 20, 30);
        textSize(32);
        text(fittest, 20, 75);

        textSize(12);
        text("total generations: " + population.getGenerations(), 20, 140);
        text("total population: " + Population.POPULATION_SIZE, 20, 170);

        textSize(10);
        text("All phrases:\n" + population.allPhrases(), 450, 10);
    }
}
