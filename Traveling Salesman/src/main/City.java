package main;

import processing.core.PApplet;

public class City {
    private PApplet sketch;
    private int x;
    private int y;

    City(PApplet sketch, int x, int y) {
        this.sketch = sketch;
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate of this city
     */
    private int getX() {
        return x;
    }

    /**
     *
     * @return The y coordinate of this city
     */
    private int getY() {
        return y;
    }

    /**
     * Return the distance between this city, and nothera given city
     * @param city A city to find the distance to
     * @return The Euclidean distance between two points
     */
    double getDistance(City city) {
        double distX = this.getX() - city.getX();
        double distY = this.getY() - city.getY();
        return distX * distX + distY * distY;
    }

    public String toString() {
        return getX() + ", " + getY();
    }

    /**
     * Draw the given city on the screen as a circle
     */
    void render() {
        sketch.noFill();
        sketch.ellipse(getX(), getY(), 25, 25);
    }

    /**
     * Connect this city with a given city, writing the given number inside this city's representation
     *
     * @param city City to connect to
     * @param idx  The index of this city in the given tour
     */
    void connect(City city, int idx) {
        sketch.line(getX(), getY(), city.getX(), city.getY());
        String num = idx + "";
        sketch.fill(0);
        sketch.text(num, getX() - 10, getY() + 3);
    }
}
