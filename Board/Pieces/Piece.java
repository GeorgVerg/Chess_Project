package Chess_Project.Board.Pieces;

import java.awt.*;

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


    abstract public void draw(Graphics g, int x, int y, int width, int height);
}
