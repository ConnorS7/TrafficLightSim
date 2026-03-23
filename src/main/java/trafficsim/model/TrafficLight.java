package trafficsim.model;

import trafficsim.state.ILightState;
import trafficsim.strategy.ITimingStrategy;

public class TrafficLight {
    private ILightState lightState;
    private final ITimingStrategy timingStrategy;
    private int timer = 0;

    public TrafficLight(ILightState initialState, ITimingStrategy timingStrategy) {
        this.lightState = initialState;
        this.timingStrategy = timingStrategy;
    }

    public void update(){
        timer++;
        lightState.handle(this);
    }

    public void setLightState(ILightState lightState) {
        this.lightState = lightState;
        this.timer = 0;
    }

    public int getTimer(){
        return timer;
    }

    public ITimingStrategy getTimingStrategy() {
        return timingStrategy;
    }

    public LightColor getColor(){
        return lightState.getColor();
    }
}
