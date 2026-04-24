package trafficsim.strategy;

import trafficsim.model.*;
import trafficsim.observer.Car;

public interface MovementStrategy {
    boolean canMove(Car car, Car frontCar, Direction dir, LightColor lightColor);
}