package trafficsim.model;

import trafficsim.observer.Car;
import trafficsim.observer.IObserver;

import java.util.*;

public class Intersection implements IObserver {

    private final Map<Direction, Queue<Car>> lanes = new HashMap<>();

    private Direction currentGreen = Direction.NORTH;
    private LightColor currentLightColor = LightColor.RED;

    public Intersection() {
        for (Direction direction : Direction.values()) {
            lanes.put(direction, new LinkedList<>());
        }
    }

    //CAR MANAGEMENT

    public void addCar(Direction direction, String name){
        int x = 0, y = 0;

        switch (direction) {
            case NORTH -> { x = 230; y = 0; }
            case SOUTH -> { x = 265; y = 475; }
            case EAST  -> { x = 475; y = 230; }
            case WEST  -> { x = 0; y = 265; }
        }

        Car car = new Car(name, x, y, direction);
        lanes.get(direction).add(car);
    }

    public List<Car> getAllCars(){
        List<Car> allCars = new ArrayList<>();

        for(Queue<Car> queue : lanes.values()){
            allCars.addAll(queue);
        }

        return allCars;
    }

    //LIGHT OBSERVER UPDATE

    @Override
    public void update(LightColor color) {
        this.currentLightColor = color;
        System.out.println("Light updated to: " + color);

//        if(color == LightColor.GREEN){
//            allowCarsToPass();
//        }
    }

    //CAR MOVEMENT LOGIC

    public void updateCarPositions(){
        for (Queue<Car> lane : lanes.values()){
            for (Car car : lane) {
                if(lane == lanes.get(currentGreen) && currentLightColor == LightColor.GREEN){
                    car.move();
                }
            }
        }
    }

    private void allowCarsToPass() {
        Queue<Car> carQueue = lanes.get(currentGreen);

        int numCarsPassing = Math.min(2, carQueue.size());

        for (int i = 0; i < numCarsPassing; i++){
            Car car = carQueue.poll();
            if(car != null){
                System.out.println(car.getName() + " passes intersection");
            }
        }
    }

    //DIRECTION CONTROL

    public void nextDirection(){
        currentGreen = switch (currentGreen){
            case NORTH -> Direction.NORTH;
            case EAST -> Direction.EAST;
            case SOUTH -> Direction.SOUTH;
            case WEST -> Direction.WEST;
        };
    }

    public Direction getCurrentDirection(){
        return currentGreen;
    }

    //UI HELP

    public int getCurrentDirectionQueueSize(){
        return lanes.get(currentGreen).size();
    }

    public int getDirectionQueueSize(Direction direction){
        return lanes.get(direction).size();
    }
}
