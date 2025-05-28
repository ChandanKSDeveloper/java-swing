package Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class gridLayout {
    public static void main(String[] args){
        JFrame frame = new JFrame("Grid layout");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(350,550);
        frame.setLocationRelativeTo(null); // Center the frame

        // main container panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));


        JLabel nameLabel = new JLabel("Calculator",SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        nameLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(nameLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3,3,5,5));
        for(int i = 1; i<10; i++){
            JButton btn = new JButton(String.valueOf(i));
            String value = String.valueOf(i);
            btn.setFocusable(false);
            btn.setFont(new Font("SansSerif", Font.BOLD,20));
            btn.setBorder(new LineBorder(Color.DARK_GRAY,2));
            btn.setBackground(Color.lightGray);
            btn.addActionListener(e -> {
                System.out.println(value);
            });
            gridPanel.add(btn);
        }

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        // Set the main panel as the content pane
        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }
}
