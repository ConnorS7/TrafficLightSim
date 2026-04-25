package trafficsim;

import javax.swing.*;
import java.awt.*;

public class  Main {
    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Traffic Simultion");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SimulationPanel simulationPanel = new SimulationPanel();

            frame.setContentPane(simulationPanel);
            frame.pack();

            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        });
    }
}