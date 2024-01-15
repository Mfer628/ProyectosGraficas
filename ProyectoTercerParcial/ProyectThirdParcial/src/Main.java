import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RotacionBote jPanel = new RotacionBote();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(jPanel, BorderLayout.CENTER);

        frame.setSize(500, 500);
        frame.setVisible(true);

        while (true) {
            Thread.sleep(50);
            jPanel.repaint();
        }
    }

}