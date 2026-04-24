package trafficsim.strategy;

import trafficsim.model.Intersection;

public class AdaptiveTimingStrategy implements ITimingStrategy{

    private final Intersection intersection;

    public AdaptiveTimingStrategy(Intersection intersection) {
        this.intersection = intersection;
    }

    @Override
    public int getGreenDuration() {
        int queueSize = intersection.getCurrentDirectionQueueSize();

        if(queueSize > 5) { return 8; }
        if(queueSize > 2) { return 6; }

        return 5;
    }

    @Override
    public int getRedDuration() {
        return 5;
    }

    @Override
    public int getYellowDuration() {
        return 2;
    }
}
