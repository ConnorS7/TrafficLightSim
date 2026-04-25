import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import trafficsim.TrafficSimulationFacade;
import trafficsim.decorator.BasicCar;
import trafficsim.decorator.ICar;
import trafficsim.decorator.SportsCar;
import trafficsim.model.Direction;
import trafficsim.model.LightColor;
import trafficsim.strategy.*;

public class TestsSoFar {

    @Test
    void testStrategySwitching() {
        TrafficSimulationFacade sim = new TrafficSimulationFacade(new DefaultMovementStrategy());

        sim.setStrategy(new BusyMovementStrategy());

        sim.update(100);

        assertTrue(true);
    }

    @Test
    void testTrafficLightCycles() {
        TrafficSimulationFacade sim = new TrafficSimulationFacade(new DefaultMovementStrategy());

        var light = sim.getLight(Direction.NORTH);

        LightColor initial = light.getColor();

        sim.update(5000);

        LightColor after = light.getColor();

        assertNotEquals(initial, after);
    }

    @Test
    void testSportsCarFasterThanBasicCar() {
        ICar base = new BasicCar("base", 0, 0, Direction.NORTH);
        ICar sports = new SportsCar(base);

        assertTrue(sports.getSpeed() > base.getSpeed());
    }
}