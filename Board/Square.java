package Chess_Project.Board;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel
{
    private int row;
    private int col;

    private Piece piece;

    Color lightColor = new Color(240, 217, 181);
    Color darkColor = new Color(181, 136, 99);

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        setPreferredSize(new Dimension(Chessboard.SQUARE_SIZE, Chessboard.SQUARE_SIZE));
        setBackground((row + col) % 2 == 0 ? lightColor : darkColor);
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Piece getPiece()
    {
        return piece;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(piece != null)
        {
            piece.draw(g, 5, 5, getWidth() - 10, getHeight() - 10);
        }
    }
}
