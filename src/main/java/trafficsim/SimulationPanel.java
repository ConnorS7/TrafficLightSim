package trafficsim;

import trafficsim.model.Direction;
import trafficsim.model.Intersection;
import trafficsim.model.TrafficLight;
import trafficsim.decorator.ICar;
import trafficsim.strategy.DefaultMovementStrategy;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;

public class SimulationPanel extends JPanel {

    private final Intersection intersection;
    private long lastUpdate;

    public SimulationPanel() {
        this.intersection = new Intersection(new DefaultMovementStrategy());

        lastUpdate = System.currentTimeMillis();

        Timer timer = new Timer(33, e -> tick());
        timer.start();

    }

    private void tick(){
        long now = System.currentTimeMillis();
        long delta = now - lastUpdate;
        lastUpdate = now;

        intersection.update(delta);
        repaint();
    }

    //RENDERING

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        drawRoads(g);
        drawTrafficLights(g);
        drawCars(g);
    }

    private void drawRoads(Graphics g){
        g.setColor(Color.GRAY);

        g.fillRect(225,0,70,500);
        g.fillRect(0, 225, 500, 70);

        g.setColor(Color.WHITE);
        g.drawLine(260,0,260,500);
        g.drawLine(0,260,500,260);

        g.setColor(Color.GRAY);

        g.fillRect(225,225,70,70);
    }

    private void drawTrafficLights(Graphics g) {
        drawLight(g, 253, 220, intersection.getTrafficLight(Direction.NORTH));
        drawLight(g, 285, 253, intersection.getTrafficLight(Direction.EAST));
        drawLight(g, 253, 285, intersection.getTrafficLight(Direction.SOUTH));
        drawLight(g, 220, 253, intersection.getTrafficLight(Direction.WEST));
    }

    private void drawLight(Graphics g, int x, int y, TrafficLight light) {

        switch (light.getColor()) {
            case RED -> g.setColor(Color.RED);
            case GREEN -> g.setColor(Color.GREEN);
            case YELLOW -> g.setColor(Color.ORANGE);
        }

        g.fillOval(x, y, 15, 15);
    }

    private void drawCars(Graphics g){
        for(ICar car : intersection.getAllCars()){
            g.setColor(car.getColor());
            g.fillRect(car.getX(), car.getY(), car.getWidth(), car.getHeight());
        }
    }
}
