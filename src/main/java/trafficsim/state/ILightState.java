package trafficsim.state;

import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;

public interface ILightState {
    void handle(TrafficLight trafficLight, long timeInState);
    LightColor getColor();
}
