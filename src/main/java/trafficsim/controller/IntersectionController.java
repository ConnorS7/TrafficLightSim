package trafficsim.controller;

import trafficsim.model.TrafficLight;

public class IntersectionController {
    private final TrafficLight trafficLight;

    public IntersectionController(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public void update() {
        trafficLight.update();
        System.out.println("Light is: " + trafficLight.getColor());
    }
}
