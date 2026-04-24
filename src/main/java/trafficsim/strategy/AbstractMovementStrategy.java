package trafficsim.strategy;

import trafficsim.model.*;
import trafficsim.observer.Car;

public abstract class AbstractMovementStrategy implements MovementStrategy {

    protected final int GAP;
    protected final int NORTH_STOP_Y;
    protected final int SOUTH_STOP_Y;
    protected final int EAST_STOP_X;
    protected final int WEST_STOP_X;

    protected static final int INTERSECTION_NORTH = 200;
    protected static final int INTERSECTION_SOUTH = 295;
    protected static final int INTERSECTION_EAST = 295;
    protected static final int INTERSECTION_WEST = 200;

    public AbstractMovementStrategy(
            int gap,
            int northStop,
            int southStop,
            int eastStop,
            int westStop
    ) {
        this.GAP = gap;
        this.NORTH_STOP_Y = northStop;
        this.SOUTH_STOP_Y = southStop;
        this.EAST_STOP_X = eastStop;
        this.WEST_STOP_X = westStop;
    }

    @Override
    public boolean canMove(Car car, Car frontCar, Direction dir, LightColor light) {
        if (frontCar != null && isTooClose(car, frontCar, dir)) {
            return false;
        }

        return light != LightColor.RED
                || !isAtStopLine(car, dir)
                || hasPassedIntersection(car, dir);
    }

    private boolean isTooClose(Car car, Car front, Direction dir) {
        return switch (dir) {
            case NORTH -> front.getY() - car.getY() < GAP;
            case SOUTH -> car.getY() - front.getY() < GAP;
            case EAST  -> car.getX() - front.getX() < GAP;
            case WEST  -> front.getX() - car.getX() < GAP;
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

    private boolean hasPassedIntersection(Car car, Direction dir) {
        return switch (dir) {
            case NORTH -> car.getY() > INTERSECTION_NORTH;
            case SOUTH -> car.getY() < INTERSECTION_SOUTH;
            case EAST  -> car.getX() < INTERSECTION_EAST;
            case WEST  -> car.getX() > INTERSECTION_WEST;
        };
    }
}