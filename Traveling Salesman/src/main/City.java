package main;

import processing.core.PApplet;

import java.util.Random;

public class City {
    private PApplet sketch;
    private int x;
    private int y;

    City(PApplet sketch) {
        this.sketch = sketch;
        Random rand = new Random();
        this.x = rand.nextInt(200);
        this.y = rand.nextInt(200);
    }

    City(PApplet sketch, int x, int y) {
        this.sketch = sketch;
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    double getDistance(City city) {
        double distX = Math.pow(this.getX() - city.getX(), 2);
        double distY = Math.pow(this.getY() - city.getY(), 2);
        return Math.sqrt(distX + distY);
    }

    public String toString() {
        return getX() + ", " + getY();
    }

    public void render() {
        sketch.noFill();
        sketch.ellipse(getX(), getY(), 25, 25);
    }

    void connect(City city, int idx) {
        sketch.line(getX(), getY(), city.getX(), city.getY());
        String num = idx + "";
        sketch.fill(0);
        sketch.text(num, getX() - 10, getY() + 3);
    }
}
