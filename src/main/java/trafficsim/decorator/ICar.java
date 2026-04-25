package trafficsim.decorator;

import java.awt.*;

public interface ICar {
    void move(long deltaTime);
    int getSpeed();
    int getX();
    int getY();

    int getWidth();
    int getHeight();
    Color getColor();
}
