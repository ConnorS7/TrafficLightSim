package trafficsim.strategy;

public class DefaultMovementStrategy extends AbstractMovementStrategy {

    private static final int NORTH_STOP_Y = 180; //STOPS LATER
    private static final int SOUTH_STOP_Y = 315;
    private static final int EAST_STOP_X  = 315;
    private static final int WEST_STOP_X  = 180;

    private static final int GAP = 45; //TAILGATES

    public DefaultMovementStrategy() {
        super(GAP, NORTH_STOP_Y, SOUTH_STOP_Y, EAST_STOP_X, WEST_STOP_X);
    }

    @Override
    public long getSpawnRate() {
        return 500 + (long)(Math.random() * 1500);
    }
}
