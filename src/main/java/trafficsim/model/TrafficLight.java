package trafficsim.model;

import trafficsim.state.ILightState;
import trafficsim.state.RedState;

public class TrafficLight {
    private ILightState lightState = new RedState(RED_DURATION);
    private long timeInState;

    private static final int RED_DURATION = 7000;
    private static final int GREEN_DURATION = 7000;
    private static final int YELLOW_DURATION = 4000;

    public void update(long deltaTime) {
        timeInState += deltaTime;
        lightState.handle(this, timeInState);
    }

    public void setLightState(ILightState lightState) {
        this.lightState = lightState;
        this.timeInState = 0;
    }

    public LightColor getColor(){
        return lightState.getColor();
    }

    public int getRedDuration() {
        return RED_DURATION;
    }

    public int getYellowDuration() {
        return YELLOW_DURATION;
    }

    public int getGreenDuration() {
        return GREEN_DURATION;
    }
}
