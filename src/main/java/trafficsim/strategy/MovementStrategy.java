package trafficsim.strategy;

import trafficsim.model.*;
import trafficsim.decorator.ICar;

public interface MovementStrategy {
    boolean canMove(ICar car, ICar frontCar, Direction dir, LightColor lightColor);

    long getSpawnRate();
}