package trafficsim.decorator;

import java.awt.*;

public class SportsCar extends CarDecorator {

    public SportsCar(ICar car) {
        super(car);
    }

    @Override
    public int getSpeed() {
        return car.getSpeed() + 20;
    }

    @Override
    public Color getColor() {
        return Color.RED;
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
