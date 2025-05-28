package Calculator;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class Calculator4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator v4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));
        mainPanel.setBackground(Color.WHITE);

        // text field
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("SansSerif", Font.BOLD,28));
        textField.setBorder(new CompoundBorder(
                new LineBorder(Color.darkGray,2,true),
                new EmptyBorder(15,10,15,10)
        ));
        textField.setBackground(new Color(245,245,245));

        // Wrap TextField in a panel for spacing
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(0,0,10,0));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(textField,BorderLayout.CENTER);

        mainPanel.add(textPanel,BorderLayout.NORTH);

        // Buttons
        String[] buttons = {
                "AC", "%","DEL","/",
                "7","8","9","*",
                "4","5","6","-",
                "1","2","3","+",
                "00","0",".","="};
        JPanel gridPanel = new JPanel(new GridLayout(5,4,10,10));
        gridPanel.setBackground(Color.WHITE);

        for(String label : buttons){
            JButton btn = new JButton(label);
            styleButton(btn);

            btn.addActionListener(e -> {
                String text = textField.getText();
                String lastChar = text.isEmpty() ? "" : text.substring(text.length() - 1);
                switch (label) {
                    case "AC" -> textField.setText("");
                    case "DEL" -> {
                        if (text.equals("Error")) {
                            textField.setText("");
                        } else if (!text.isEmpty()) {
                            textField.setText(text.substring(0, text.length() - 1));
                        }
                    }
                    case "=" -> {
                        try {
                            double result = evaluate(textField.getText());
                            if(Double.isNaN(result) || Double.isInfinite(result)){
                                textField.setText("Error");
                            } else {
                                textField.setText(Double.toString(result));
                            }
                        } catch (Exception ex) {
                            textField.setText("Error");
                        }
                    }
                    case "+", "-", "*", "/" -> {
                        if(text.equals("Error") || text.isEmpty()){
                            return;
                        } else if ("+-*/.".contains(lastChar)) {
                            textField.setText(text.substring(0,text.length() - 1) + label);
                        } else {
                            textField.setText(text + label);
                        }
                    }
                    case "." -> {
                        if(text.equals("Error") || text.isEmpty()){
                            textField.setText("0.");
                            return;
                        }

                        // Find the last number segment
                        int i = text.length() - 1;
                        while(i >= 0 && Character.isDigit(text.charAt(i))) i--;
                        if(i>=0 && text.charAt(i) == '.'){
                            return;
                        }

                        // Also check the segment doesn't already contain a dot
                        String lastSegment = text.substring(i + 1);
                        if (lastSegment.contains(".")) return;
                        if("+-*/".contains(lastChar)){
                            textField.setText(text + "0.");
                        } else {
                            textField.setText(text + ".");
                        }
                    }
                    default -> {
                        if(!text.equals("Error")) {
                            textField.setText(text + label);
                        }
                    }
                }
            });

            gridPanel.add(btn);
        }
        mainPanel.add(gridPanel,BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // gives priority to operators
    public static int precedence(char op){
        return switch (op){
            case '+','-' -> 1;
            case '*','/' -> 2;
            default -> 0;
        };
    }

    public static double applyOp(double a, double b, char op){
        return switch (op){
            case '+' -> a+b;
            case '-' -> a-b;
            case '*' -> a*b;
            case '/' -> a/b;
            default -> 0;
        };
    }

    // Evaluate function
    public static double evaluate(String expression){
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for(int i = 0; i<expression.length(); i++){
            char ch = expression.charAt(i);
            if(ch == ' ') continue;

            // handling number including decimals
            if(Character.isDigit(ch) || ch == '.'){
                StringBuilder sb = new StringBuilder();
                while(i < expression.length() && (Character.isDigit((expression.charAt(i))) || expression.charAt(i) =='.')){
                    sb.append(expression.charAt(i++)); // expression without operator -> a complete number
                }
                i--; //  step back to correct the index
                values.push(Double.parseDouble(sb.toString())); // adding number to values stack
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!ops.empty() && precedence(ops.peek()) >= precedence(ch)){
                    double b = values.pop();
                    double a = values.pop();
                    char op = ops.pop();
                    values.push(applyOp(a,b,op));
                }
                ops.push(ch);
                

            }

        }
        while (!ops.isEmpty()){
            double b = values.pop();
            double a = values.pop();
            char op = ops.pop();
            values.push(applyOp(a,b,op));
        }
        return values.pop();

    }

    private static void styleButton(JButton btn){
        btn.setFocusable(false);
        btn.setFont(new Font("SansSerif", Font.BOLD,18));
        btn.setBackground(new Color(240,240,240));
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(new LineBorder(new Color(200,200,200,200),1,true));
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //adding hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(220,220,220));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btn.setBackground(new Color(240,240,240));
            }
        });

    }
}
