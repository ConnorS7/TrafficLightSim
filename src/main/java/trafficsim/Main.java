package trafficsim;

import trafficsim.factory.TrafficLightFactory;
import trafficsim.view.SimulationUI;

public class  Main {
    public static void main(String[] args) {
        var light = TrafficLightFactory.createIntersection();
        new SimulationUI(light);
    }
}