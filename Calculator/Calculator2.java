package Calculator;

import javax.swing.*;
import java.awt.*;

public interface Calculator2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator v2");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(new BorderLayout(10,10));

        // input field
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JTextField firstField = new JTextField(10);
        JTextField secondField = new JTextField(10);

        inputPanel.add(firstField);
        inputPanel.add(new JLabel(" and "));
        inputPanel.add(secondField);

        // result field
        JTextField resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.CENTER);
        resultField.setFont(new Font("Arial", Font.BOLD, 16));

        // panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2,3,5,5));
        String[] operations = {"Calculator.Add","Subtract","Divide","Multiply","Power"};

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        for(String op : operations){
            JButton btn = new JButton(op);
            btn.setFocusable(false);
            btn.setPreferredSize(new Dimension(20,10));
            btn.addActionListener(e -> calculate(firstField, secondField, resultField, op));
            buttonPanel.add(btn);
        }




        // adding component to frame
        frame.add(inputPanel,BorderLayout.NORTH);
        frame.add(buttonPanel,BorderLayout.CENTER);
        frame.add(resultField,BorderLayout.SOUTH);


        frame.setVisible(true);



    }

    private static void calculate(JTextField firstField, JTextField secondField, JTextField resultField, String operation){

        try{
            if(firstField.getText().equals("") || secondField.getText().equals("")){
                resultField.setText("Enter value");
                return;
            }

            double a = Double.parseDouble(firstField.getText());
            double b = Double.parseDouble(secondField.getText());

            double result = switch (operation) {
                case "Calculator.Add" -> a + b;
                case "Subtract" -> a - b;
                case "Multiply" -> a * b;
                case "Divide" -> (b != 0) ? a / b : Double.POSITIVE_INFINITY;
                case "Power" -> Math.pow(a,b);
                default -> 0;
            };

            resultField.setText(String.format("%.2f",result));


        } catch (NumberFormatException err){
            resultField.setText("Invalid Input");
        }
    }
}
