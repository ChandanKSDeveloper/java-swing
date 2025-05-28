package TicTacToe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToeV3 {
    private static JFrame frame;
    static boolean xTurn = true;
    static JLabel statusLabel;
    static Point lineStart = null;
    static Point lineEnd = null;
    static boolean gameOver = false;
    static JPanel drawPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Tic Tac Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(360, 450);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            mainPanel.setBackground(Color.WHITE);

            statusLabel = new JLabel("Turn : X");
            statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
            statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(statusLabel, BorderLayout.NORTH);

            JPanel board = new JPanel(new GridLayout(3, 3, 5, 5));
            board.setBackground(Color.WHITE);
            board.setBorder(new EmptyBorder(10, 10, 10, 10));
            JButton[] tile = new JButton[9];

            for (int i = 0; i < 9; i++) {
                tile[i] = new JButton("");
                styleButton(tile[i]);

                int finalI = i;
                tile[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!gameOver && tile[finalI].getText().isEmpty()) {
                            tile[finalI].setText(xTurn ? "X" : "O");
                            tile[finalI].setForeground(xTurn ? Color.BLUE : Color.RED);
                            xTurn = !xTurn;
                            statusLabel.setText("Turn : " + (xTurn ? "X" : "O"));
                            checkWinner(tile);
                        }
                    }
                });

                board.add(tile[i]);
            }

            drawPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (lineStart != null && lineEnd != null) {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(5));
                        g2.setColor(Color.GREEN);
                        g2.drawLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y);
                    }
                }
            };
            drawPanel.setOpaque(false);
            drawPanel.setLayout(new BorderLayout());
            drawPanel.add(board, BorderLayout.CENTER);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(320, 320));
            board.setBounds(0, 0, 320, 320);
            drawPanel.setBounds(0, 0, 320, 320);
            layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(drawPanel, JLayeredPane.PALETTE_LAYER);

            mainPanel.add(layeredPane, BorderLayout.CENTER);

            JButton resetBtn = new JButton("Reset");
            resetBtn.addActionListener(e -> reset(tile));
            mainPanel.add(resetBtn, BorderLayout.SOUTH);

            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }

    static void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 40));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    static void checkWinner(JButton[] tile) {
        int[][] winComb = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // cols
                {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] comb : winComb) {
            String a = tile[comb[0]].getText();
            String b = tile[comb[1]].getText();
            String c = tile[comb[2]].getText();

            if (!a.isEmpty() && a.equals(b) && b.equals(c)) {
                gameOver = true;
                statusLabel.setText("Winner: " + a);
                disableAll(tile);
                drawWinningLine(tile[comb[0]], tile[comb[2]]);
                new Timer(3000, e -> reset(tile)).start();
                return;
            }
        }

        // Check for tie
        boolean allFilled = true;
        for (JButton btn : tile) {
            if (btn.getText().isEmpty()) {
                allFilled = false;
                break;
            }
        }

        if (allFilled) {
            gameOver = true;
            statusLabel.setText("It's a Tie!");
            new Timer(3000, e -> reset(tile)).start();
        }
    }

    static void drawWinningLine(JButton start, JButton end) {
        Point s = start.getLocation();
        Point e = end.getLocation();
        int btnWidth = start.getWidth();
        int btnHeight = start.getHeight();

        lineStart = new Point(s.x + btnWidth / 2, s.y + btnHeight / 2);
        lineEnd = new Point(e.x + btnWidth / 2, e.y + btnHeight / 2);
        drawPanel.repaint();
    }

    static void disableAll(JButton[] tile) {
        for (JButton btn : tile) {
            btn.setEnabled(false);
        }
    }

    static void reset(JButton[] tile) {
        for (JButton btn : tile) {
            btn.setText("");
            btn.setEnabled(true);
        }
        xTurn = true;
        gameOver = false;
        statusLabel.setText("Turn : X");
        lineStart = null;
        lineEnd = null;
        drawPanel.repaint();
    }
}

