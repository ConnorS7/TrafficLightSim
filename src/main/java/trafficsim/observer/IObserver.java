package trafficsim.observer;

import trafficsim.model.LightColor;

public interface IObserver {
    void update(LightColor color);
}
