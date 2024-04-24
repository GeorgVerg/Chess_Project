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

    public void resetDraw(Graphics g, int x, int y, int width, int height)
    {
        g.dispose();
    }

    abstract public void draw(Graphics g, int x, int y, int width, int height);

    abstract public ArrayList<Point> getPossibleMoves(Square[][] board);
}
