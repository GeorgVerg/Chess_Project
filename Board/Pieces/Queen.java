package Chess_Project.Board.Pieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess_Project.Board.Square;

import java.awt.image.BufferedImage;


public class Queen extends Piece
{
    String fileNameW = "Chess_Project/Board/Pieces/images/white/queen.png";
    String fileNameB = "Chess_Project/Board/Pieces/images/black/queen.png";

    BufferedImage image;
    Color color;

    public Queen(PieceType type, Color color, Square square)
    {
        super(type, color, square);

        this.color = color;

        try 
        {
            image = ImageIO.read(new File(color == Color.WHITE ? fileNameW : fileNameB));
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        if(image != null)
        {
            g.drawImage(image, x, y, width, height, null);
        } else
        {
            System.exit(0);
        }
    }

    @Override
    public ArrayList<Point> getPossibleMoves(Square[][] board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPossibleMoves'");
    }
}
