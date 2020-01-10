package main;

import processing.core.PApplet;
import processing.core.PFont;

import static main.Population.mutationRate;

public class Render extends PApplet {
    PFont f;
    Population population;
    int numberOfGenerations;

    public static void main(String[] args) {
        PApplet.main("main.Render");
    }

    @Override
    public void settings() {
        // TODO: Customize screen size and so on here
        City city = new City(this, 60, 200);
        TourManager.addCity(city);
        City city2 = new City(this, 180, 200);
        TourManager.addCity(city2);
        City city3 = new City(this, 80, 180);
        TourManager.addCity(city3);
        City city4 = new City(this, 140, 180);
        TourManager.addCity(city4);
        City city5 = new City(this, 20, 160);
        TourManager.addCity(city5);
        City city6 = new City(this, 100, 160);
        TourManager.addCity(city6);
        City city7 = new City(this, 200, 160);
        TourManager.addCity(city7);
        City city8 = new City(this, 140, 140);
        TourManager.addCity(city8);
        City city9 = new City(this, 40, 120);
        TourManager.addCity(city9);
        City city10 = new City(this, 100, 120);
        TourManager.addCity(city10);
        City city11 = new City(this, 180, 100);
        TourManager.addCity(city11);
        City city12 = new City(this, 60, 80);
        TourManager.addCity(city12);
        City city13 = new City(this, 120, 80);
        TourManager.addCity(city13);
        City city14 = new City(this, 180, 60);
        TourManager.addCity(city14);
        City city15 = new City(this, 20, 40);
        TourManager.addCity(city15);
        City city16 = new City(this, 100, 40);
        TourManager.addCity(city16);
        City city17 = new City(this, 200, 40);
        TourManager.addCity(city17);
        City city18 = new City(this, 20, 20);
        TourManager.addCity(city18);
        City city19 = new City(this, 60, 20);
        TourManager.addCity(city19);
        City city20 = new City(this, 160, 20);
        TourManager.addCity(city20);

        size(400, 400);

        population = new Population(5, true);
        numberOfGenerations = 100;
        System.out.println("Initial Distance: " + population.getFittest().getDistance());
    }

    @Override
    public void setup() {
        // TODO: Your custom drawing and setup on applet start belongs here
        f = createFont("Tahoma", 10);
    }

    @Override
    public void draw() {
        // TODO: Do your drawing for each frame here
        System.out.println("Initial Distance: " + population.getFittest().getDistance());

        population = population.evolvePopulation();

        population.getFittest().render();

        displayInfo();
    }

    void displayInfo() {
        background(255);
        // Display current status of populationation
        Tour fittest = population.getFittest();
        textFont(f);
        textAlign(LEFT);
        fill(0);


        textSize(16);
        text("Best phrase:", 20, 30);
        textSize(32);
        text(fittest.toString(), 20, 75);

        textSize(12);
        //text("total generations: " + population., 20, 140);
        text("total populationation: " + population.populationSize(), 20, 170);
        text("mutation rate: " + (mutationRate * 100) + "%", 20, 185);

        textSize(10);
        //text("All phrases:\n" + population.allPhrases(), 450, 10);
    }


}
