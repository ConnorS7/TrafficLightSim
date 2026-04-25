package trafficsim;

import trafficsim.model.Direction;
import trafficsim.model.Intersection;
import trafficsim.model.TrafficLight;
import trafficsim.decorator.ICar;
import trafficsim.strategy.DefaultMovementStrategy;
import trafficsim.strategy.MovementStrategy;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;

public class SimulationPanel extends JPanel {

    private final TrafficSimulationFacade simulation;
    private MovementStrategy movementStrategy = new DefaultMovementStrategy();
    private long lastUpdate;

    public SimulationPanel() {
        this.simulation = new TrafficSimulationFacade(movementStrategy);

        lastUpdate = System.currentTimeMillis();

        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(500,500));
        setLayout(null);
        setUpControls();

        Timer timer = new Timer(33, e -> tick());
        timer.start();
    }

    private void tick(){
        long now = System.currentTimeMillis();
        long delta = now - lastUpdate;
        lastUpdate = now;

        simulation.update(delta);
        repaint();
    }

    //RENDERING

    private void setUpControls(){
        JButton pauseBtn = new JButton("Pause");
        JButton resumeBtn = new JButton("Resume");
        JSlider speedSlider = new JSlider(1, 5, 1);

        pauseBtn.setBounds(10, 10, 80, 25);
        resumeBtn.setBounds(100, 10, 90, 25);

        speedSlider.setBounds(10, 50, 125, 50);
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        pauseBtn.addActionListener(e -> simulation.pause());
        resumeBtn.addActionListener(e -> simulation.resume());

        speedSlider.addChangeListener(e -> {
            int value = speedSlider.getValue();

            simulation.setSpeedMultiplier(value);
        });

        add(pauseBtn);
        add(resumeBtn);
        add(speedSlider);
    }

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
        drawLight(g, 253, 220, simulation.getLight(Direction.NORTH));
        drawLight(g, 285, 253, simulation.getLight(Direction.EAST));
        drawLight(g, 253, 285, simulation.getLight(Direction.SOUTH));
        drawLight(g, 220, 253, simulation.getLight(Direction.WEST));
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
        for(ICar car : simulation.getCars()){
            g.setColor(car.getColor());
            g.fillRect(car.getX(), car.getY(), car.getWidth(), car.getHeight());
        }
    }
}
