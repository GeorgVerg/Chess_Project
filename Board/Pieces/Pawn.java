package Chess_Project.Board.Pieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Pawn extends Piece
{
    String fileNameW = "Chess_Project/Board/Pieces/images/white/pawn.png";
    String fileNameB = "Chess_Project/Board/Pieces/images/black/pawn.png";

    BufferedImage image;
    Color color;

    public Pawn(PieceType type, Color color)
    {
        super(type, color);

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
}
