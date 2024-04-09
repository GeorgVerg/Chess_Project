package Chess_Project.Board.Pieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess_Project.Board.Square;

import java.awt.image.BufferedImage;

public class Pawn extends Piece
{
    String fileNameW = "Chess_Project/Board/Pieces/images/white/pawn.png";
    String fileNameB = "Chess_Project/Board/Pieces/images/black/pawn.png";

    BufferedImage image;
    Color color;

    Square square;

    public Pawn(PieceType type, Color color, Square square)
    {
        super(type, color, square);

        this.color = color;
        this.square = square;

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

    public ArrayList<String> getPossibleMoves(Square[][] board)
    {
        ArrayList<String> possibleMoves = new ArrayList<>();
        int currentRow = square.getRow();
        int currentCol = square.getCol();
        int direction = (color == Color.WHITE) ? -1 : 1;

        if(board[currentRow + direction][currentCol].getPiece() == null)
        {
            board[currentRow + direction][currentCol].setPossibleMove(true);
            possibleMoves.add(board[currentRow + direction][currentCol].getSquareId());
            
            if((currentRow == 1 && color == Color.WHITE) || (currentCol == 6 && color == Color.BLACK))
            {
                if(board[currentRow + 2 * direction][currentCol].getPiece() == null)
                {
                    board[currentRow + 2 * direction][currentCol].setPossibleMove(true);
                    possibleMoves.add(board[currentRow + 2 * direction][currentCol].getSquareId());
                }
            }
        }

        for(int colOffset : new int[]{-1, 1})
        {
            int targetCol = currentCol + colOffset;
            if(targetCol >= 0 && targetCol < 8)
            {
                Piece targetPiece = board[currentRow + direction][targetCol].getPiece();
                if(targetPiece != null && targetPiece.getColor() != color)
                {
                    board[currentRow + direction][targetCol].setPossibleMove(true);
                    possibleMoves.add(board[currentRow + direction][targetCol].getSquareId());
                }
            }
        }

        if((currentRow == 6 && color == Color.BLACK) || (currentRow == 1 && color == Color.WHITE))
        {
            new LaunchPage(color, board[currentRow + direction][currentCol].getSquare(), square);
        }

        return possibleMoves;
    }
}