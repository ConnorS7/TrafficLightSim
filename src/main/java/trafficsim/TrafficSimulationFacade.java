package trafficsim;

import trafficsim.controller.IntersectionController;
import trafficsim.decorator.ICar;
import trafficsim.model.Direction;
import trafficsim.model.Intersection;
import trafficsim.model.TrafficLight;
import trafficsim.strategy.MovementStrategy;

import java.util.List;

public class TrafficSimulationFacade {
    private final Intersection intersection;
    private final IntersectionController controller;

    private boolean paused = false;
    private double speedMultiplier = 1.0;

    public TrafficSimulationFacade(MovementStrategy movementStrategy) {
        this.intersection = new Intersection(movementStrategy);
        this.controller = new IntersectionController(intersection.getTrafficLights());
    }

    public void update(long deltaTime){
        if(paused){return;}

        long adjustedDelta = (long)(deltaTime * speedMultiplier);

        controller.update(adjustedDelta);
        intersection.update(adjustedDelta);
    }

    public List<ICar> getCars(){
        return intersection.getAllCars();
    }

    public TrafficLight getLight(Direction dir) {
        return intersection.getTrafficLight(dir);
    }

    public void setStrategy(MovementStrategy strategy) {
        intersection.setMovementStrategy(strategy);
    }

    public void setSpeedMultiplier(double multiplier) {
        this.speedMultiplier = multiplier;
    }

    public void pause(){
        paused = true;
    }

    public void resume(){
        paused = false;
    }
}
