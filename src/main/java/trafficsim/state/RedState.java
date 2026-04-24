package trafficsim.state;

import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;

public class RedState implements ILightState {
    @Override
    public void handle(TrafficLight trafficLight) {
        if(trafficLight.getTimer() >= trafficLight.getRedDuration()){

            trafficLight.getIntersection().nextDirection();
            trafficLight.setLightState(new GreenState());
        }
    }

    @Override
    public LightColor getColor() {
        return LightColor.RED;
    }
}
