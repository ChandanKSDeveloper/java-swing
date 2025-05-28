package Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Calculator3 {

     static int result = 0;
     static String lastOperator = "";
     static boolean justEvaluated = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator v3");
        frame.setSize(350,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center the frame on window

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10,10,10,10)); // setting padding like feature

        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("SansSerif", Font.BOLD,25));
        textField.setBorder(new LineBorder(Color.darkGray,2));

        JPanel gridPanel = new JPanel(new GridLayout(4,3,5,5));

        for (int i = 0; i<= 9; i++){
            JButton btn = new JButton(String.valueOf(i));
            String val = String.valueOf(i);
            btn.setFocusable(false);
            btn.setBorder(new LineBorder(Color.DARK_GRAY, 2));
            btn.setBackground(Color.lightGray);
            btn.setFont(new Font("Roboto", Font.BOLD,20));
            btn.addActionListener(e -> textField.setText(textField.getText() + val));
            gridPanel.add(btn);
        }

        JButton deleteBtn = new JButton("X");
        deleteBtn.setFocusable(false);
        deleteBtn.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        deleteBtn.setBackground(Color.lightGray);
        deleteBtn.setFont(new Font("Roboto", Font.BOLD,20));
        deleteBtn.addActionListener(e -> deleteLastDigit(textField));
        gridPanel.add(deleteBtn);

        JButton additionBtn = new JButton("+");
        additionBtn.setFocusable(false);
        additionBtn.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        additionBtn.setBackground(Color.lightGray);
        additionBtn.setFont(new Font("Roboto", Font.BOLD,20));
        additionBtn.addActionListener(e -> {
            String text = textField.getText();
            if(!text.isEmpty()){
                int num = Integer.parseInt(text);
                if(lastOperator.equals("+")){
                    result += num;
                } else {
                    result = num;
                }
            }
            lastOperator = "+";
            textField.setText("");
        });
        gridPanel.add(additionBtn);


        JButton subtractionBtn = new JButton("-");
        subtractionBtn.setFocusable(false);
        subtractionBtn.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        subtractionBtn.setBackground(Color.lightGray);
        subtractionBtn.setFont(new Font("Roboto", Font.BOLD,20));
        subtractionBtn.addActionListener(e -> {
            String text = textField.getText();
            if(!text.isEmpty()){
                int num = Integer.parseInt(text);
                if(lastOperator.equals("-")){
                    result -= num;
                } else {
                    result = num;
                }
            }
            lastOperator = "-";
            textField.setText("");
        });
        gridPanel.add(subtractionBtn);


        JButton multiplyBtn = new JButton("*");
        multiplyBtn.setFocusable(false);
        multiplyBtn.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        multiplyBtn.setBackground(Color.lightGray);
        multiplyBtn.setFont(new Font("Roboto", Font.BOLD,20));
        multiplyBtn.addActionListener(e -> {
            String text = textField.getText();
            if(!text.isEmpty()){
                int num = Integer.parseInt(text);
                if(lastOperator.equals("*")){
                    result *= num;
                } else {
                    result = num;
                }
            }
            lastOperator = "*";
            textField.setText("");
        });
        gridPanel.add(multiplyBtn);


        JButton divideBtn = new JButton("/");
        divideBtn.setFocusable(false);
        divideBtn.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        divideBtn.setBackground(Color.lightGray);
        divideBtn.setFont(new Font("Roboto", Font.BOLD,20));
        divideBtn.addActionListener(e -> {
            String text = textField.getText();
            if(!text.isEmpty()){
                int num = Integer.parseInt(text);
                if(lastOperator.equals("/")){
                    result /= num;
                } else {
                    result = num;
                }
            }
            lastOperator = "/";
            textField.setText("");
        });
        gridPanel.add(divideBtn);

        JButton equalBtn = new JButton("=");
        equalBtn.setFocusable(false);
        equalBtn.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        equalBtn.setBackground(Color.lightGray);
        equalBtn.setFont(new Font("Roboto", Font.BOLD,20));
        equalBtn.addActionListener(e -> {
            String text = textField.getText();
            if(!text.isEmpty()){
                int num = Integer.parseInt(text);
                if(lastOperator.equals("+")){
                    result+=num;
                } else if (lastOperator.equals("-")) {
                    result-=num;
                } else if (lastOperator.equals("*")) {
                    result*=num;
                } else if (lastOperator.equals("/")) {
                    result/=num;
                }
                else {
                    result = num;
                }
                textField.setText(String.valueOf(result));
                lastOperator = "";
                result = 0;
            }
        });
        gridPanel.add(equalBtn);



        mainPanel.add(gridPanel,BorderLayout.CENTER);

        mainPanel.add(textField,BorderLayout.NORTH);
        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }

    public static void deleteLastDigit(JTextField textField){
        String current = textField.getText();
        if(!current.isEmpty()){
            textField.setText(current.substring(0,current.length() - 1));

        }
    }
}
