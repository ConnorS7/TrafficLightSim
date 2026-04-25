package trafficsim.model;

import trafficsim.controller.IntersectionController;
import trafficsim.decorator.BasicCar;
import trafficsim.decorator.*;
import trafficsim.strategy.MovementStrategy;

import java.util.*;

public class Intersection {

    private final Map<Direction, Queue<ICar>> lanes = new HashMap<>();
    private final Map<Direction, TrafficLight>  lights = new HashMap<>();

    private MovementStrategy movementStrategy;
    private IntersectionController controller;

    private long spawnTimer = 0;

    public Intersection(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;

        for(Direction direction : Direction.values()){
            lanes.put(direction, new LinkedList<>());
        }

        lights.put(Direction.NORTH, new TrafficLight());
        lights.put(Direction.EAST, new TrafficLight());
        lights.put(Direction.SOUTH, new TrafficLight());
        lights.put(Direction.WEST, new TrafficLight());

        controller = new IntersectionController(lights);
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    //UPDATE

    public void update(long deltaTime) {
        controller.update(deltaTime);
        updateCars(deltaTime);

        handleSpawning(deltaTime);
    }

    //CAR MANAGEMENT

    public void handleSpawning(long deltaTime) {
        spawnTimer += deltaTime;

        if(spawnTimer >= movementStrategy.getSpawnRate()){
            spawnTimer = 0;

            Direction dir = Direction.values()[(int)(Math.random() * 4)];
            addCar(dir, "Car-" + (System.currentTimeMillis()/1000));
        }
    }

    public void addCar(Direction direction, String name){
        int x = 0, y = 0;

        switch (direction) {
            case NORTH -> { x = 230; y = 0; }
            case SOUTH -> { x = 265; y = 475; }
            case EAST  -> { x = 475; y = 230; }
            case WEST  -> { x = 0; y = 265; }
        }

        ICar car = new BasicCar(name, x, y, direction);

        double rand = Math.random();

        if (rand < 0.20) {
            car = new SportsCar(car);
        } else if (rand > 0.80) {
            car = new Truck(car);
        }

        lanes.get(direction).add(car);
    }

    private void updateCars(long deltaTime) {
        for(Direction direction : lanes.keySet()){
            Queue<ICar> lane = lanes.get(direction);

            ICar front = null;

            for(ICar car : lane) {
                LightColor lightColor = lights.get(direction).getColor();

                boolean canMove = movementStrategy.canMove(car, front, direction, lightColor);

                if(canMove){
                    car.move(deltaTime);
                }

                front = car;
            }
        }
    }

    //UI HELP

    public TrafficLight getTrafficLight(Direction direction){
        return lights.get(direction);
    }

    public Map<Direction, TrafficLight> getTrafficLights(){
        return lights;
    }

    public List<ICar> getAllCars(){
        List<ICar> allCars = new ArrayList<>();

        for(Queue<ICar> queue : lanes.values()){
            allCars.addAll(queue);
        }

        return allCars;
    }
}
