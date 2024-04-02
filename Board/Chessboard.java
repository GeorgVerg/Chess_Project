package Chess_Project.Board;

import javax.swing.*;
import java.awt.*;

public class Chessboard extends JFrame {
    private static final int BOARD_SIZE = 8;
    public static final int SQUARE_SIZE = 80;
    

    public Chessboard() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE);
        setResizable(false);

        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        getContentPane().add(boardPanel);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Square square = new Square(row, col);
                boardPanel.add(square);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Chessboard::new);
    }
}