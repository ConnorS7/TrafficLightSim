package trafficsim;

import trafficsim.controller.IntersectionController;
import trafficsim.model.TrafficLight;
import trafficsim.state.RedState;
import trafficsim.strategy.FixedTimingStrategy;

public class  Main {
    public static void main(String[] args) throws InterruptedException {
        TrafficLight trafficLight = new TrafficLight(
                new RedState(),
                new FixedTimingStrategy()
        );

        IntersectionController controller = new IntersectionController(trafficLight);

        while(true) {
            controller.update();
            Thread.sleep(1000);
        }
    }
}