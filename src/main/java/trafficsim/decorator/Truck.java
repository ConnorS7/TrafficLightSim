package trafficsim.decorator;

import java.awt.*;

public class Truck extends CarDecorator {

    public Truck(ICar car) {
        super(car);
    }

    @Override
    public int getSpeed() {
        return car.getSpeed() - 3;
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public int getWidth() {
        return 25;
    }

    @Override
    public int getHeight() {
        return 25;
    }
}
