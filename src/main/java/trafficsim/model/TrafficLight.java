package trafficsim.model;

import trafficsim.observer.IObserver;
import trafficsim.state.ILightState;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
    private ILightState lightState;
    private final Intersection intersection;

    private int timer = 0;

    private static final int RED_DURATION = 5;
    private static final int GREEN_DURATION = 5;
    private static final int YELLOW_DURATION = 2;

    private List<IObserver> observers = new ArrayList<>();

    public TrafficLight(ILightState initialState, Intersection intersection) {
        this.lightState = initialState;
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
