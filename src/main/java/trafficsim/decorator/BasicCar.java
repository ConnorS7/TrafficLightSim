package trafficsim.decorator;

import trafficsim.model.Direction;

import java.awt.*;

public class BasicCar implements ICar {

    private final String name;
    protected int x, y;
    protected final Direction direction;
    protected int speed;

    public BasicCar(String name,  int x, int y, Direction direction) {
        this.name = name;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.speed = 5;
    }

    public void move(long deltaTime){
        double step = getSpeed() * (deltaTime / 50.0);

        switch (direction){
            case NORTH:
                y += (int) step; //move up
                break;
            case SOUTH:
                y -= (int) step; //move down
                break;
            case EAST:
                x -= (int) step; //move right
                break;
            case WEST:
                x += (int) step; //move left
                break;
        }
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getX(){
        return x;
    }

    @Override
    public int getY(){
        return y;
    }

    @Override
    public int getWidth(){
        return 25;
    }

    @Override
    public int getHeight(){
        return 25;
    }

    @Override
    public Color getColor(){
        return Color.BLUE;
    }

}
