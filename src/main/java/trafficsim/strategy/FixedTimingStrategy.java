package trafficsim.strategy;

public class FixedTimingStrategy implements ITimingStrategy{
    @Override
    public int getRedDuration() {
        return 5;
    }

    @Override
    public int getYellowDuration() {
        return 2;
    }

    @Override
    public int getGreenDuration() {
        return 5;
    }
}
