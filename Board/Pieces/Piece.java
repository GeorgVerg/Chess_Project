package Chess_Project.Board.Pieces;

import java.awt.*;
import java.util.ArrayList;

import Chess_Project.Board.Square;

abstract public class Piece
{
    private PieceType type;
    private Color color;
    private Square square;

    public Piece(PieceType type, Color color, Square square)
    {
        this.type = type;
        this.color = color;
        this.square = square;
    }

    
    public Color getColor()
    {
        return color;
    }
    
    public PieceType getType()
    {
        return type;
    }
    
    public Square getSquare()
    {
        return square;
    }

    public void movePiece(Square newSquare)
    {
        square = newSquare;
    }

    public void resetDraw(Graphics g, int x, int y, int width, int height)
    {
        g.dispose();
    }

    public boolean addMoveIfPossible(Square[][] board, ArrayList<Point> moves, int newRow, int newCol) {
        if (board[newRow][newCol].getPiece() == null) {
            moves.add(new Point(newRow, newCol));
            return true; // Continue in this direction
        } else {
            if (board[newRow][newCol].getPiece().getColor() != this.color) {
                moves.add(new Point(newRow, newCol)); // Capture possible
            }
            return false; // Stop - cannot move past this piece
        }
    }

    abstract public void draw(Graphics g, int x, int y, int width, int height);

    abstract public ArrayList<Point> getPossibleMoves(Square[][] board);
    
    abstract public ArrayList<Point> getCaptureMoves(Square[][] board);
    
    abstract public ArrayList<Point> getCaptureMovesForKing(Square[][] board);

    abstract public void updateSquareLocation();
}
