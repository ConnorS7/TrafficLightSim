package trafficsim.observer;

import trafficsim.model.Direction;
import trafficsim.model.LightColor;

public class Car {

    private final String name;
    private int x, y;
    private final Direction direction;

    public Car(String name,  int x, int y, Direction direction) {
        this.name = name;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public void move(){
        switch (direction){
            case NORTH:
                y += 3; //move up
                break;
            case SOUTH:
                y -= 3; //move down
                break;
            case EAST:
                x -= 3; //move right
                break;
            case WEST:
                x += 3; //move left
                break;
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Direction getDirection(){
        return direction;
    }

    public String getName(){
        return name;
    }
}
