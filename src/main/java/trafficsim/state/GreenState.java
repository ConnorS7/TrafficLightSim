package trafficsim.state;

import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;

public class GreenState implements ILightState {
    @Override
    public void handle(TrafficLight trafficLight) {
        if(trafficLight.getTimer() >= trafficLight.getTimingStrategy().getGreenDuration()) {
            trafficLight.setLightState(new YellowState());
        }
    }

    @Override
    public LightColor getColor() {
        return LightColor.GREEN;
    }
}
