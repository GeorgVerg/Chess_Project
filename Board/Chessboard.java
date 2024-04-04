package Chess_Project.Board;
import Chess_Project.Board.Pieces.*;

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
                // ADDING PIECES TO SQUARES
                if(row == 1 || row == 6)
                {
                    square.setPiece(new Pawn(PieceType.PAWN, row == 1 ? Color.BLACK : Color.WHITE));
                }
                if((row == 0 || row == 7) && (col == 0 || col == 7))
                {
                    square.setPiece(new Rook(PieceType.ROOK, row == 0 ? Color.BLACK : Color.WHITE));
                }
                if((row == 0 || row == 7) && (col == 2 || col == 5))
                {
                    square.setPiece(new Bishop(PieceType.BISHOP, row == 0 ? Color.BLACK : Color.WHITE));
                }
                if((row == 0 || row == 7) && (col == 1 || col == 6))
                {
                    square.setPiece(new Knight(PieceType.KNIGHT, row == 0 ? Color.BLACK : Color.WHITE));
                }
                if((row == 0 || row == 7) && col == 3)
                {
                    square.setPiece(new Queen(PieceType.QUEEN, row == 0 ? Color.BLACK : Color.WHITE));
                }
                if((row == 0 || row == 7) && col == 4)
                {
                    square.setPiece(new King(PieceType.KING, row == 0 ? Color.BLACK : Color.WHITE));
                }
                boardPanel.add(square);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Chessboard::new);
    }
}