package trafficsim.view;

import trafficsim.model.Direction;
import trafficsim.model.Intersection;
import trafficsim.model.TrafficLight;
import trafficsim.observer.Car;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel {

    private final TrafficLight light;
    private final Intersection intersection;

    private long startTime;
    private JLabel timerLabel;

    private int numCars = 0;
    private int maxCars = 4;

    public SimulationPanel(TrafficLight light) {
        this.light = light;
        this.intersection = light.getIntersection();

        setPreferredSize(new Dimension(500,500));
        setLayout(null);
        setBackground(Color.DARK_GRAY);

        setupTimer();
        startLoop();
    }

    private void startLoop(){
        new Timer(50, e -> {
            spawnCars();
            intersection.updateCarPositions();

            updateTimer();
            repaint();
        }).start();

        new Timer(1000, e -> {
            light.update();

            updateTimer();
            repaint();
        }).start();
    }

    //STOPWATCH LOGIC

    private void setupTimer() {
        startTime = System.currentTimeMillis();

        timerLabel = new JLabel("Time: 0.0s");
        timerLabel.setBounds(425, 10, 120, 20);

        timerLabel.setForeground(Color.WHITE);
        add(timerLabel);
    }

    private void updateTimer() {
        double seconds = (System.currentTimeMillis() - startTime) / 1000.0;
        timerLabel.setText(String.format("Time: %.1fs", seconds));
    }

    //CAR HANDLING

    private void spawnCars(){
        while(numCars < maxCars){
            Direction dir = Direction.values()[(int)(Math.random() * 4)];
            //Direction dir = Direction.NORTH;

            intersection.addCar(dir, "Car" + numCars++);
        }
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

    private void drawTrafficLights(Graphics g){
        Color color = switch (light.getColor()){
            case RED -> Color.RED;
            case YELLOW -> Color.YELLOW;
            case GREEN -> Color.GREEN;
        };

        g.setColor(color);
        g.fillOval(275, 205, 20, 20);
    }

    private void drawCars(Graphics g){
        g.setColor(Color.ORANGE);

        List<Car> cars = intersection.getAllCars();

        for(Car car : cars){
            g.fillRect(car.getX(), car.getY(), 25, 25);
        }
    }
}
