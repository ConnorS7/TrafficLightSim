package trafficsim.state;

import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;

public class RedState implements ILightState {

    private final long duration;

    public RedState(long duration) {
        this.duration = duration;
    }

    @Override
    public void handle(TrafficLight trafficLight, long timeInState) {
        if(timeInState >= duration){
            trafficLight.setLightState(new GreenState(trafficLight.getGreenDuration()));
        }
    }

    @Override
    public LightColor getColor() {
        return LightColor.RED;
    }
}
