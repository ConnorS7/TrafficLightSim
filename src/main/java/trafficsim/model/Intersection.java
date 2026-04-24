package trafficsim.model;

import trafficsim.observer.Car;
import trafficsim.observer.IObserver;

import java.util.*;

public class Intersection implements IObserver {

    private final Map<Direction, Queue<Car>> lanes = new HashMap<>();
    private final Map<Direction, LightColor> lights = new HashMap<>();

    private Direction currentGreen = Direction.NORTH;
    private LightColor currentLightColor = LightColor.RED;

    private static final int NORTH_STOP_Y = 180;
    private static final int SOUTH_STOP_Y = 315;
    private static final int EAST_STOP_X  = 315;
    private static final int WEST_STOP_X  = 180;

    public Intersection() {
        for (Direction direction : Direction.values()) {
            lanes.put(direction, new LinkedList<>());
            lights.put(direction, LightColor.RED);
        }

        lights.put(Direction.NORTH, LightColor.RED);
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
        for (Direction dir : lanes.keySet()){

            Queue<Car> lane = lanes.get(dir);

            Car frontCar = null;

            for (Car car : lane) {

                boolean canMove = canCarMove(car, frontCar, dir);

                if (canMove) {
                    car.move();
                }

                frontCar = car;
            }
        }
    }

    private boolean canCarMove(Car car, Car frontCar, Direction dir){
        LightColor light = lights.get(dir);

        if(frontCar != null && isTooClose(car, frontCar, dir)){
            return false;
        }

        return light != LightColor.RED || !isAtStopLine(car, dir);
    }

    private boolean isTooClose(Car car, Car front, Direction dir) {

        int gap = 45;

        return switch (dir) {
            case NORTH -> front.getY() - car.getY() < gap;
            case SOUTH -> car.getY() - front.getY() < gap;
            case EAST  -> car.getX() - front.getX() < gap;
            case WEST  -> front.getX() - car.getX() < gap;
        };
    }

    private boolean isAtStopLine(Car car, Direction dir) {

        return switch (dir) {
            case NORTH -> car.getY() >= NORTH_STOP_Y;
            case SOUTH -> car.getY() <= SOUTH_STOP_Y;
            case EAST  -> car.getX() <= EAST_STOP_X;
            case WEST  -> car.getX() >= WEST_STOP_X;
        };
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

    public void setGreen(Direction direction){
        for(Direction dir : Direction.values()){
            lights.put(dir, LightColor.RED);
        }

        lights.put(direction, LightColor.GREEN);
    }

    public void nextDirection(){
        currentGreen = switch (currentGreen){
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };

        setGreen(currentGreen);
    }

    public Direction getCurrentDirection(){
        return currentGreen;
    }

    //UI HELP

    public int getCurrentDirectionQueueSize(){
        return lanes.get(currentGreen).size();
    }

    public LightColor getTrafficLight(Direction direction){
        return lights.get(direction);
    }

    public int getDirectionQueueSize(Direction direction){
        return lanes.get(direction).size();
    }
}
