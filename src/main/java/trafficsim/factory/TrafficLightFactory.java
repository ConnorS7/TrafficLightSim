package trafficsim.factory;

import trafficsim.model.Intersection;
import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;
import trafficsim.state.RedState;
import trafficsim.strategy.AdaptiveTimingStrategy;
import trafficsim.strategy.FixedTimingStrategy;
import trafficsim.strategy.ITimingStrategy;

public class TrafficLightFactory {
    public static TrafficLight createSmartIntersection() {
        Intersection intersection = new Intersection();
        ITimingStrategy timingStrategy = new AdaptiveTimingStrategy(intersection);

        TrafficLight light = new TrafficLight(
                new RedState(),
                timingStrategy,
                intersection
        );

        light.addObserver(intersection);

        return light;
    }
}
