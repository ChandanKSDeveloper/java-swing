package Calculator;

import javax.swing.*;

public class Calculator1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator v1");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        JTextField firstField = new JTextField();
        firstField.setBounds(100,0,50,50);

        JTextField secondField = new JTextField();
        secondField.setBounds(200,0,50,50);

        JTextField resultField = new JTextField();
        resultField.setBounds(100,400,100,50);
        resultField.setEditable(false);

        frame.add(firstField);
        frame.add(secondField);
        frame.add(resultField);

        JButton addBtn = new JButton("Calculator.Add");
        addBtn.setFocusable(false);
        addBtn.setBounds(0,180,100,50);
        frame.add(addBtn);

        JButton subBtn = new JButton("Subtract");
        subBtn.setFocusable(false);
        subBtn.setBounds(100,180,100,50);
        frame.add(subBtn);

        JButton multiBtn = new JButton("Multiply");
        multiBtn.setFocusable(false);
        multiBtn.setBounds(200,180,100,50);
        frame.add(multiBtn);

        JButton divideBtn = new JButton("Divide");
        divideBtn.setFocusable(false);
        divideBtn.setBounds(300,180,100,50);
        frame.add(divideBtn);

        addBtn.addActionListener(e -> {
            try{
                int a = Integer.parseInt(firstField.getText());
                int b = Integer.parseInt(secondField.getText());
                resultField.setText(String.valueOf(a+b));
            } catch (NumberFormatException err){
                resultField.setText("Invalid Input");
            }
        });

        subBtn.addActionListener(e -> {
            try{
                int a = Integer.parseInt(firstField.getText());
                int b = Integer.parseInt(secondField.getText());
                resultField.setText(String.valueOf(a-b));
            } catch (NumberFormatException err){
                resultField.setText("Invalid Input");
            }
        });

        multiBtn.addActionListener(e -> {
            try{
                int a = Integer.parseInt(firstField.getText());
                int b = Integer.parseInt(secondField.getText());
                resultField.setText(String.valueOf(a*b));
            } catch (NumberFormatException err){
                resultField.setText("Invalid Input");
            }
        });

        divideBtn.addActionListener(e -> {
            try{
                int a = Integer.parseInt(firstField.getText());
                int b = Integer.parseInt(secondField.getText());
                resultField.setText(String.valueOf((double)a/b));
            } catch (NumberFormatException err){
                resultField.setText("Invalid Input");
            }
        });


        frame.setVisible(true);


    }
}
