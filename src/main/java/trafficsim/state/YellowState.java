package trafficsim.state;

import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;

public class YellowState implements ILightState{
    @Override
    public void handle(TrafficLight trafficLight) {
        if(trafficLight.getTimer() >= trafficLight.getTimingStrategy().getYellowDuration()){
            trafficLight.setLightState(new RedState());
        }
    }

    @Override
    public LightColor getColor() {
        return LightColor.YELLOW;
    }
}
