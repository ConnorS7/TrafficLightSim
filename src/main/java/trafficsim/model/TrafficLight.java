package trafficsim.model;

import trafficsim.observer.IObserver;
import trafficsim.state.ILightState;
import trafficsim.strategy.ITimingStrategy;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
    private ILightState lightState;
    private final ITimingStrategy timingStrategy;
    private final Intersection intersection;
    private int timer = 0;

    private List<IObserver> observers = new ArrayList<>();

    public TrafficLight(ILightState initialState, ITimingStrategy timingStrategy,  Intersection intersection) {
        this.lightState = initialState;
        this.timingStrategy = timingStrategy;
        this.intersection = intersection;
    }

    public void update(){
        timer++;
        lightState.handle(this);
    }

    public void setLightState(ILightState lightState) {
        this.lightState = lightState;
        this.timer = 0;

        notifyObservers();
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

    public Intersection getIntersection() {
        return intersection;
    }

    public void addObserver(IObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (IObserver observer : observers){
            observer.update(getColor());
        }
    }
}
