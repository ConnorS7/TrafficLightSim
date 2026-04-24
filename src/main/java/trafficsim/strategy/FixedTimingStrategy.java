package trafficsim.strategy;

public class FixedTimingStrategy implements ITimingStrategy{
    private static final int RED_DURATION = 5;
    private static final int GREEN_DURATION = 5;
    private static final int YELLOW_DURATION = 2;


    @Override
    public int getRedDuration() {
        return RED_DURATION;
    }

    @Override
    public int getYellowDuration() {
        return YELLOW_DURATION;
    }

    @Override
    public int getGreenDuration() {
        return GREEN_DURATION;
    }
}
