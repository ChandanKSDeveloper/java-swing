package TicTacToe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TicTacToeV2 {
    private static JFrame frame;
    static boolean xTurn = true;
    static  JLabel statusLabel;
    static Point lineStart = null;
    static Point lineEnd = null;
    static boolean gameOver = false;
    static JPanel drawPanel;


    public static void main(String[] args) {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(340,450);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));
        mainPanel.setBackground(Color.WHITE);

        statusLabel = new JLabel("Turn : X");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel board = new JPanel(new GridLayout(3,3,5,5));
        board.setBackground(Color.WHITE);
        board.setBorder(new EmptyBorder(10,10,10,10));
        JButton[] tile = new JButton[9];



        for(int i = 0; i < 9; i++){
            tile[i] = new JButton("");
            styleButton(tile[i]);

            int finalI = i;
            tile[i].addActionListener(e -> {
                if(tile[finalI].getText().isEmpty() && !gameOver){

                    if(xTurn){
                        tile[finalI].setText("X");
                        statusLabel.setText("Turn : O");
                    } else {
                        tile[finalI].setText("O");
                        statusLabel.setText("Turn : X");
                    }

                    tile[finalI].setEnabled(false);
                    xTurn = !xTurn;
                    checkWinner(tile);
                }

            });
            board.add(tile[i]);
        }

        // line draw
        drawPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(lineStart != null && lineEnd != null){
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(5));
                    g2.setColor(Color.GREEN);
                    g2.drawLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y);
                }
            }
        };

        drawPanel.setOpaque(false);
        drawPanel.setLayout(null);
        drawPanel.setFocusable(false);
//        drawPanel.setBounds(board.getBounds());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300,300));
        board.setBounds(0,0,300,300);
        drawPanel.setBounds(0,0,300,300);
        layeredPane.add(board,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(drawPanel,JLayeredPane.PALETTE_LAYER);

        mainPanel.add(layeredPane,BorderLayout.CENTER);


        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        resetBtn.setFocusable(false);
        resetBtn.setBackground(new Color(0xF0F0F0));
        resetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resetBtn.addActionListener(e -> resetGame(tile));
        mainPanel.add(resetBtn, BorderLayout.SOUTH);


//        mainPanel.add(board, BorderLayout.CENTER);




        frame.setContentPane(mainPanel);
        frame.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            drawPanel.setBounds(0,0,board.getWidth(), board.getHeight());
        });
    }

    private static void checkWinner(JButton[] btn){
        // for rows
        for (int i = 0; i < 7; i+=3) {
            if(!btn[i].getText().isEmpty() && btn[i].getText().equals(btn[i + 1].getText()) && btn[i].getText().equals(btn[i+2].getText())){
                handleWin(btn[i].getText(), btn, i,i+2);
                return;
            }
        }

        // for columns
        for (int i = 0; i <= 2; i++) {
            if(!btn[i].getText().isEmpty() && btn[i].getText().equals(btn[i + 3].getText()) && btn[i].getText().equals(btn[i+6].getText())){
                handleWin(btn[i].getText(), btn, i, i+6);
                return;
            }

        }

        // for first diagonal
        if(!btn[0].getText().isEmpty() && btn[0].getText().equals(btn[4].getText()) && btn[0].getText().equals(btn[8].getText())){
            handleWin(btn[0].getText(), btn, 0, 8);
            return;
        }
        // for second diagonal
        if(!btn[2].getText().isEmpty() && btn[2].getText().equals(btn[4].getText()) && btn[2].getText().equals(btn[6].getText())){
         handleWin(btn[2].getText(), btn, 2,6);
            return;
        }

        // checking for tie
        boolean tie = true;

        for(int i = 0; i<9; i++){
            if(btn[i].getText().isEmpty()){
                tie = false;
                break;
            }

        }
        if(tie){
            gameOver = true;
            statusLabel.setText("Game tie");
            delayedReset(btn);

        }

    }

    public static void resetGame(JButton[] btn) {
        for (int i = 0; i < 9; i++) {
            btn[i].setText("");
            btn[i].setEnabled(true);
        }
        gameOver = false;
        lineStart = null;
        lineEnd = null;
        drawPanel.repaint();

        xTurn = true;
        statusLabel.setText("Turn : X");


    }

    private static void drawWinningLine(JButton startBtn, JButton endBtn){
        Point start = getCenter(startBtn);
        Point end = getCenter(endBtn);
        lineStart = SwingUtilities.convertPoint(startBtn.getParent(),start,drawPanel);
        lineEnd = SwingUtilities.convertPoint(endBtn.getParent(),end,drawPanel);
        drawPanel.repaint();

    }

    private static Point getCenter(JButton btn){
        int x = btn.getX() + btn.getWidth()/2;
        int y = btn.getY() + btn.getHeight()/2;

        return new Point(x,y);
    }

    private static void disableAll(JButton[] btn){
        for(JButton b : btn){
            b.setEnabled(false);
        }
    }

    private static void handleWin(String winner, JButton[] btn, int first,  int last){
        statusLabel.setText(winner + " wins!!");
        gameOver = true;
        disableAll(btn);

        drawWinningLine(btn[first], btn[last]);
        delayedReset(btn);
    }

    private static void delayedReset(JButton[] btn){
        Timer timer = new Timer(3000, e -> resetGame(btn));
        timer.setRepeats(false);
        timer.start();
    }

    private static void styleButton(JButton btn){
        btn.setFont(new Font("Arial", Font.BOLD,30));
        btn.setFocusable(false);
        btn.setBackground(new Color(240,240,240));
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(new LineBorder(new Color(200,200,200,200), 1,true));
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(220,220,220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(240,240,240));
            }
        });

    }
}
