package Chess_Project.Board;

import java.awt.*;

public class Piece
{
    private PieceType type;
    private Color color;

    public Piece(PieceType type, Color color)
    {
        this.type = type;
        this.color = color;
    }

    public void draw(Graphics g, int x, int y, int width, int height)
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

            default:
                break;
        }
    }

    
    void drawPawn(Graphics g, int x, int y, int width, int height)
    {
        int[] xPoints = {x + width / 2, x + width * 3 / 4, x + width / 4};
        int[] yPoints = {y + height / 4, y + height * 3 / 4, y + height * 3 / 4};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    void drawRook(Graphics g, int x, int y, int width, int height) {
        g.fillRect(x + width / 4, y + height / 4, width / 2, height / 2);
        g.fillRect(x + width / 6, y + height / 2, width * 2 / 3, height / 4);
    }
}
