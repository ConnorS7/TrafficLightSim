package trafficsim.strategy;

public interface ITimingStrategy {
    int getRedDuration();
    int getYellowDuration();
    int getGreenDuration();
}
