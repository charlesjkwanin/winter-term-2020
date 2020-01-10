package main;

public class Run {


    public static void main(String[] args) {

        Render sketch = new Render();

        // Create and add our cities
        City city = new City(sketch, 60, 200);
        TourManager.addCity(city);
        City city2 = new City(sketch, 180, 200);
        TourManager.addCity(city2);
        City city3 = new City(sketch, 80, 180);
        TourManager.addCity(city3);
        City city4 = new City(sketch, 140, 180);
        TourManager.addCity(city4);
        City city5 = new City(sketch, 20, 160);
        TourManager.addCity(city5);
        City city6 = new City(sketch, 100, 160);
        TourManager.addCity(city6);
        City city7 = new City(sketch, 200, 160);
        TourManager.addCity(city7);
        City city8 = new City(sketch, 140, 140);
        TourManager.addCity(city8);
        City city9 = new City(sketch, 40, 120);
        TourManager.addCity(city9);
        City city10 = new City(sketch, 100, 120);
        TourManager.addCity(city10);
        City city11 = new City(sketch, 180, 100);
        TourManager.addCity(city11);
        City city12 = new City(sketch, 60, 80);
        TourManager.addCity(city12);
        City city13 = new City(sketch, 120, 80);
        TourManager.addCity(city13);
        City city14 = new City(sketch, 180, 60);
        TourManager.addCity(city14);
        City city15 = new City(sketch, 20, 40);
        TourManager.addCity(city15);
        City city16 = new City(sketch, 100, 40);
        TourManager.addCity(city16);
        City city17 = new City(sketch, 200, 40);
        TourManager.addCity(city17);
        City city18 = new City(sketch, 20, 20);
        TourManager.addCity(city18);
        City city19 = new City(sketch, 60, 20);
        TourManager.addCity(city19);
        City city20 = new City(sketch, 160, 20);
        TourManager.addCity(city20);

//        Population population = new Population(5, true);
//
//        System.out.println("Initial Distance: " + population.getFittest().getDistance());
//
//        population = population.evolvePopulation();
//        int numberOfGenerations = 100;
//        for (int i = 0; i < numberOfGenerations; i++) {
//            population = population.evolvePopulation();
//        }
//
//        System.out.println("Completed");
//        System.out.println("Final Distance: " + population.getFittest().getDistance());
//        System.out.println("Solution:");
//        System.out.println(population.getFittest());
    }


}
