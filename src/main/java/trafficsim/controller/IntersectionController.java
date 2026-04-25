package trafficsim.controller;

import trafficsim.model.Direction;
import trafficsim.model.TrafficLight;
import trafficsim.state.GreenState;
import trafficsim.state.ILightState;
import trafficsim.state.RedState;
import trafficsim.state.YellowState;

import java.util.Map;

public class IntersectionController {

    private final Map<Direction, TrafficLight> trafficLights;
    private long phaseTime = 0;

    private Phase phase = Phase.NS_GREEN;

    public IntersectionController(Map<Direction, TrafficLight> trafficLights) {
        this.trafficLights = trafficLights;
        applyPhase();
    }

    public void update(long deltaTime){
        phaseTime += deltaTime;
        updatePhaseLogic();
    }

    private void updatePhaseLogic(){

        switch (phase) {

            case NS_GREEN -> {
                if (phaseTime >= 5000) switchPhase(Phase.NS_YELLOW);
            }

            case NS_YELLOW -> {
                if (phaseTime >= 1000) switchPhase(Phase.EW_GREEN);
            }

            case EW_GREEN -> {
                if (phaseTime >= 5000) switchPhase(Phase.EW_YELLOW);
            }

            case EW_YELLOW -> {
                if (phaseTime >= 1000) switchPhase(Phase.NS_GREEN);
            }
        }
    }

    private void switchPhase(Phase newPhase){
        phase = newPhase;
        phaseTime = 0;
        applyPhase();
    }

    private void applyPhase() {

        long greenDuration = 7000;
        long yellowDuration = 4000;
        long redDuration = 7000;

        switch (phase) {

            case NS_GREEN -> {
                setNS(new GreenState(greenDuration));
                setEW(new RedState(redDuration));
            }

            case NS_YELLOW -> {
                setNS(new YellowState(yellowDuration));
                setEW(new RedState(redDuration));
            }

            case EW_GREEN -> {
                setNS(new RedState(redDuration));
                setEW(new GreenState(greenDuration));
            }

            case EW_YELLOW -> {
                setNS(new RedState(redDuration));
                setEW(new YellowState(yellowDuration));
            }
        }
    }

    private void setNS(ILightState state) {
        trafficLights.get(Direction.NORTH).setLightState(state);
        trafficLights.get(Direction.SOUTH).setLightState(state);
    }

    private void setEW(ILightState state) {
        trafficLights.get(Direction.EAST).setLightState(state);
        trafficLights.get(Direction.WEST).setLightState(state);
    }
}
