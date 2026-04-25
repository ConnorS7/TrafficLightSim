package trafficsim.decorator;

import java.awt.*;

public class CarDecorator implements ICar {
    protected ICar car;

    public CarDecorator(ICar car) {
        this.car = car;
    }

    @Override
    public void move(long deltaTime) {
        car.move(deltaTime);
    }

    @Override public int getSpeed() { return car.getSpeed(); }
    @Override public int getX() { return car.getX(); }
    @Override public int getY() { return car.getY(); }

    @Override public int getWidth() { return car.getWidth(); }
    @Override public int getHeight() { return car.getHeight(); }
    @Override public Color getColor() { return car.getColor(); }
}
