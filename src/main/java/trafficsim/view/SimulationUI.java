package trafficsim.view;

import trafficsim.model.Direction;
import trafficsim.model.Intersection;
import trafficsim.model.TrafficLight;
import trafficsim.observer.Car;

import javax.swing.*;
import java.awt.*;

public class SimulationUI {
    public SimulationUI(TrafficLight light){
        JFrame  frame = new JFrame("Traffic Light Sim");

        frame.add(new SimulationPanel(light));
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
