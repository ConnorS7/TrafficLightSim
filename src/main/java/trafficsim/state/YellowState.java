package trafficsim.state;

import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;

public class YellowState implements ILightState{

    private final long duration;

    public YellowState(long duration) {
        this.duration = duration;
    }

    @Override
    public void handle(TrafficLight trafficLight, long timeInState) {
        if(timeInState >= duration) {
            trafficLight.setLightState(new RedState(trafficLight.getRedDuration()));
        }
    }

    @Override
    public LightColor getColor() {
        return LightColor.YELLOW;
    }
}
