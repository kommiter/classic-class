import javax.swing.*;
import java.awt.*;

public class Lobby {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JFrame frame = new JFrame("Classic Class - Size Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1024, 600));
        frame.setSize(1024, 600);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}