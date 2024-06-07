package Chess_Project.Board;

import Chess_Project.Board.Pieces.*;
import Chess_Project.Game.EndGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard extends JFrame {
    private static final int BOARD_SIZE = 8;
    public static final int SQUARE_SIZE = 80;

    static Square[][] chessboardSquares = new Square[8][8];
    private Square selectedSquare;
    Boolean isWhite;
    Boolean isWhiteTurn = true;

    JPanel boardJPanel;

    public Chessboard(Boolean isWhite) {
        this.isWhite = isWhite;

        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE);
        setResizable(false);

        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        boardJPanel = boardPanel;
        getContentPane().add(boardPanel);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Square square = new Square(this, row, col);
                chessboardSquares[row][col] = square;
                boardPanel.add(square);
            }
        }
        initializePieces();

        setVisible(true);
    }

    private void initializePieces() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Square square = chessboardSquares[row][col];

                // ADDING PIECES TO SQUARES
                if (row == 1 || row == 6) {
                    square.setPiece(new Pawn(PieceType.PAWN, row == 1 ? Color.BLACK : Color.WHITE, square));
                }
                if ((row == 0 || row == 7) && (col == 0 || col == 7)) {
                    square.setPiece(new Rook(PieceType.ROOK, row == 0 ? Color.BLACK : Color.WHITE, square));
                }
                if ((row == 0 || row == 7) && (col == 2 || col == 5)) {
                    square.setPiece(new Bishop(PieceType.BISHOP, row == 0 ? Color.BLACK : Color.WHITE, square));
                }
                if ((row == 0 || row == 7) && (col == 1 || col == 6)) {
                    square.setPiece(new Knight(PieceType.KNIGHT, row == 0 ? Color.BLACK : Color.WHITE, square));
                }
                if ((row == 0 || row == 7) && col == 3) {
                    square.setPiece(new Queen(PieceType.QUEEN, row == 0 ? Color.BLACK : Color.WHITE, square));
                }
                if ((row == 0 || row == 7) && col == 4) {
                    square.setPiece(new King(PieceType.KING, row == 0 ? Color.BLACK : Color.WHITE, square));
                }
            }
        }
    }

    public Square getSelectedSquare() {
        return selectedSquare;
    }

    public void setSelectedSquare(Square square) {
        if (selectedSquare != null) {
            selectedSquare.setSelected(false);
        }
        selectedSquare = square;
        if (selectedSquare != null) {
            selectedSquare.setSelected(true);
        }
    }

    public void clearPossibleMoves() {
        if (selectedSquare != null) {
            for (Square[] row : chessboardSquares) {
                for (Square s : row) {
                    s.setPossibleMove(false);
                    s.setCaptureMove(false);
                }
            }
        }
    }

    public void showPossibleMoves(java.util.List<Point> possibleMoves) {
        for (Point move : possibleMoves) {
            int row = move.x;
            int col = move.y;
            if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
                chessboardSquares[row][col].setPossibleMove(true);
            }
        }
    }

    public void showCaptureMoves(java.util.List<Point> captureMoves) {
        for (Point move : captureMoves) {
            int row = move.x;
            int col = move.y;
            if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
                chessboardSquares[row][col].setCaptureMove(true);
            }
        }
    }

    // public void showForcedPossibleMoves() {
    //     ArrayList<Point> forcedPossibleMoves = new ArrayList<>();
    //     for(Square[] squares : chessboardSquares)
    //     {
    //         for(Square s : squares)
    //         {
    //             if(isWhiteTurn)
    //             {
    //                 if(s.getPiece().getColor() != Color.WHITE) { continue; }

    //                 for(Point p : s.getPiece().getPossibleMoves(chessboardSquares))
    //                 {
    //                     forcedPossibleMoves.add(p);
    //                 }
    //             } else
    //             {

    //             }
    //         }
    //     }
    // }

    public Square[][] getChessboardSquares() {
        return chessboardSquares;
    }

    public JPanel getChessJPanel()
    {
        return boardJPanel;
    }

    public boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }

    public void checkGameState() {
            Piece king = null;
            for (Square[] s : chessboardSquares) {
                for (Square i : s) {
                    Piece piece = i.getPiece();
                    if(piece == null) { continue; }
                    if (piece.getType() == PieceType.KING && piece.getColor() == (isWhiteTurn ? Color.WHITE : Color.BLACK)) {
                        king = piece;
                    }
                }
            }

            if (king.getPossibleMoves(chessboardSquares).isEmpty()) {
                if (kingIsAttacked(king)) {
                    new EndGame(boardJPanel);
                }
            }
    }

    public boolean kingIsAttacked(Piece king) {
        for (Square[] s : chessboardSquares) {
            for (Square i : s) {
                Piece piece = i.getPiece();
                if(piece == null) { continue; }
                ArrayList<Point> captureMoves = piece.getCaptureMoves(chessboardSquares);
                for (Point p : captureMoves) {
                    int row = p.x;
                    int col = p.y;

                    int kingCol = king.getSquare().getCol();
                    int kingRow = king.getSquare().getRow();
 
                    if (col == kingCol && row == kingRow) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void toggleIsWhiteTurn() {
        this.isWhiteTurn = !this.isWhiteTurn;
        checkGameState();
    }
}