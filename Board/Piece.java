package Chess_Project.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece
{
    private PieceType type;
    private Color color;
    private BufferedImage image;

    public Piece(PieceType type, Color color)
    {
        this.type = type;
        this.color = color;
        loadImage();
    }

    private void loadImage()
    {
        String fileName = "";
        
        switch(type)
        {
            case PAWN:
                fileName = "Chess_Project/Board/images/pawn.png";
                break;
            case ROOK:
                fileName = "Chess_Project/Board/images/rook.png";
                break;
            case BISHOP:
                fileName = "Chess_Project/Board/images/bishop.png";
                break;
            case KING:
                fileName = "images/king.png";
                break;
            case KNIGHT:
                fileName = "images/knight.png";
                break;
            case QUEEN:
                fileName = "images/queen.png";
                break;
            default:
                fileName = "";
                break;
        }
        try 
        {
            image = ImageIO.read(new File(fileName));
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, int x, int y, int width, int height)
    {
        if(image != null)
        {
            g.drawImage(image, x, y, width, height, null);
        } else
        {
            g.setColor(color);
            switch (type)
            {
                case PAWN:
                    drawPawn(g, x, y, width, height);
                    break;
                case ROOK:
                    drawRook(g, x, y, width, height);
                    break;
                case BISHOP:
                    drawBishop(g, x, y, width, height);
    
                default:
                    break;
            }
        }
    }

    
    void drawPawn(Graphics g, int x, int y, int width, int height)
    {
        int[] xPoints = {x + width / 2, x + width * 3 / 4, x + width / 4};
        int[] yPoints = {y + height / 4, y + height * 3 / 4, y + height * 3 / 4};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    void drawRook(Graphics g, int x, int y, int width, int height)
    {
        g.fillRect(x + width / 4, y + height / 4, width / 2, height / 2);
        g.fillRect(x + width / 6, y + height / 2, width * 2 / 3, height / 4);
    }

    void drawBishop(Graphics g, int x, int y, int width, int height)
    {
        int[] xPoints = {x + width / 5, x + width * 2 / 4, x + width * 3 / 4};
        int[] yPoints = {y + height / 4, y + height * 3 / 4, y + height / 4};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
