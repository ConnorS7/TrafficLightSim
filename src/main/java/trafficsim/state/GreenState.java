package trafficsim.state;

import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;

public class GreenState implements ILightState {

    private final long duration;

    public GreenState(long duration) {
        this.duration = duration;
    }

    @Override
    public void handle(TrafficLight trafficLight, long timeInState) {
        if(timeInState >= duration) {
            trafficLight.setLightState(new YellowState(trafficLight.getYellowDuration()));
        }
    }

    @Override
    public LightColor getColor() {
        return LightColor.GREEN;
    }
}
