package Calculator;

import javax.swing.*;
import java.awt.*;

public class Add {
    public static void main(String[] args) {
        // add two numbers

        // using flow layout
        JFrame frame = new JFrame("Calculator.Add number");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLayout(new FlowLayout());

        JTextField firstField = new JTextField(10);
        JTextField secondField = new JTextField(10);
        JTextField resultField = new JTextField(10);

        resultField.setEditable(false);

        JButton btn = new JButton("Calculator.Add");
        frame.add(firstField);
        frame.add(new JLabel("+"));
        frame.add(secondField);
        frame.add(btn);
        frame.add(new JLabel("="));
        frame.add(resultField);

        btn.addActionListener(e -> {
            try{
                int a = Integer.parseInt(firstField.getText());
                int b = Integer.parseInt(secondField.getText());
                resultField.setText(String.valueOf(a+b));
            } catch (NumberFormatException err){
                resultField.setText("Invalid Input");
            }
        });

        frame.setVisible(true);
     /*   JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setTitle("Calculator.Add number");
        frame.setLayout(null);

        JTextField firstField = new JTextField();
        firstField.setBounds(20,20,100,30);
        JTextField secondField = new JTextField();
        secondField.setBounds(20,60,100,30);

        JTextField resultField = new JTextField();
        resultField.setBounds(20,100,100,30);
        resultField.setEditable(false); // cannot edit it

        JButton btn = new JButton("Calculator.Add");
        btn.setFocusable(false);
        btn.setBounds(20,150,100,30);

        frame.add(btn);
        frame.add(firstField);
        frame.add(secondField);
        frame.add(resultField);



        btn.addActionListener(e -> {
           try {
               int a = Integer.parseInt(firstField.getText());
               int b = Integer.parseInt(secondField.getText());
               resultField.setText(String.valueOf(a+b));
           } catch (NumberFormatException ex) {
               resultField.setText("Invalid Input");
           }
        });





        frame.setVisible(true);


      */

    }
}
