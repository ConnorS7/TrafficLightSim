package trafficsim.strategy;

public class CalmMovementStrategy extends AbstractMovementStrategy {

    private static final int NORTH_STOP_Y = 180 - 10; //STOPS LATER
    private static final int SOUTH_STOP_Y = 315 + 10;
    private static final int EAST_STOP_X  = 315 + 10;
    private static final int WEST_STOP_X  = 180 - 10;

    private static final int GAP = 60; //TAILGATES

    public CalmMovementStrategy() {
        super(GAP, NORTH_STOP_Y, SOUTH_STOP_Y, EAST_STOP_X, WEST_STOP_X);
    }

    @Override
    public long getSpawnRate() {
        return 750 + (long)(Math.random() * 1750);
    }
}
