import org.junit.jupiter.api.Test;
import trafficsim.model.LightColor;
import trafficsim.model.TrafficLight;
import trafficsim.state.RedState;

import static org.junit.jupiter.api.Assertions.*;

public class TestsSoFar {

    @Test
    void testInitialStateIsRed() {
        TrafficLight light = new TrafficLight(
                new RedState(),
                new FixedTimingStrategy()
        );

        assertEquals(LightColor.RED, light.getColor());
    }

    @Test
    void testRedToGreenTransition() {
        TrafficLight light = new TrafficLight(
                new RedState(),
                new FixedTimingStrategy()
        );

        // Red duration is 5 → simulate 5 ticks
        for (int i = 0; i < 5; i++) {
            light.update();
        }

        assertEquals(LightColor.GREEN, light.getColor());
    }

    @Test
    void testFullCycle() {
        TrafficLight light = new TrafficLight(
                new RedState(),
                new FixedTimingStrategy()
        );

        for (int i = 0; i < 5; i++) light.update();

        for (int i = 0; i < 5; i++) light.update();

        for (int i = 0; i < 2; i++) light.update();

        assertEquals(LightColor.RED, light.getColor());
    }

    @Test
    void testDurations() {
        FixedTimingStrategy strategy = new FixedTimingStrategy();

        assertEquals(5, strategy.getRedDuration());
        assertEquals(5, strategy.getGreenDuration());
        assertEquals(2, strategy.getYellowDuration());
    }

    @Test
    void testTimerResetsAfterStateChange() {
        TrafficLight light = new TrafficLight(
                new RedState(),
                new FixedTimingStrategy()
        );

        for (int i = 0; i < 5; i++) {
            light.update();
        }

        assertEquals(0, light.getTimer());
    }
}