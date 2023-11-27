import javax.swing.*;
import java.awt.*;

public class menuSwitchingExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Switcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // CardLayout for switching
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        // Two menus
        JPanel menu1 = new JPanel();
        menu1.add(new JLabel("Menu 1"));
        JButton switchToMenu2 = new JButton("Switch to Menu 2");
        menu1.add(switchToMenu2);

        JPanel menu2 = new JPanel();
        menu2.add(new JLabel("Menu 2"));
        JButton switchToMenu1 = new JButton("Switch to Menu 1");
        menu2.add(switchToMenu1);

        // Add panels to container
        container.add(menu1, "Menu1");
        container.add(menu2, "Menu2");

        // Add action listeners to buttons
        switchToMenu2.addActionListener(e -> cardLayout.show(container, "Menu2"));
        switchToMenu1.addActionListener(e -> cardLayout.show(container, "Menu1"));

        frame.add(container);
        frame.setVisible(true);
    }
}