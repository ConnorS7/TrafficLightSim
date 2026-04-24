package trafficsim.factory;

import trafficsim.model.Intersection;
import trafficsim.model.TrafficLight;
import trafficsim.state.RedState;

public class TrafficLightFactory {
    public static TrafficLight createIntersection() {
        Intersection intersection = new Intersection();

        TrafficLight light = new TrafficLight(
                new RedState(),
                intersection
        );

        light.addObserver(intersection);

        return light;
    }
}
