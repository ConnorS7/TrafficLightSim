package trafficsim;

import javax.swing.*;
import java.awt.*;

public class  Main {
    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulationPanel simulationPanel = new SimulationPanel();

            JFrame frame = new JFrame("Traffic Simultion");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setBackground(Color.DARK_GRAY);
            frame.setContentPane(simulationPanel);
            frame.setPreferredSize(new Dimension(515,535));

            frame.pack();
            frame.setVisible(true);
        });
    }
}