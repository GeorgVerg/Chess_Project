package Chess_Project.Board.Pieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess_Project.Board.Square;

import java.awt.image.BufferedImage;


public class Rook extends Piece
{
    String fileNameW = "Chess_Project/Board/Pieces/images/white/rook.png";
    String fileNameB = "Chess_Project/Board/Pieces/images/black/rook.png";

    BufferedImage image;
    Color color;

    Square square;

    public Rook(PieceType type, Color color, Square square)
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

    @Override
    public ArrayList<Point> getPossibleMoves(Square[][] board)
    {
        ArrayList<Point> moves = new ArrayList<>();
        int row = square.getRow();
        int col = square.getCol();

        // Move up
        for (int i = row - 1; i >= 0; i--)
        {
            if (!addMoveIfPossible(board, moves, i, col)) break;
        }

        // Move down
        for (int i = row + 1; i < 8; i++)
        {
            if (!addMoveIfPossible(board, moves, i, col)) break;
        }

        // Move left
        for (int j = col - 1; j >= 0; j--)
        {
            if (!addMoveIfPossible(board, moves, row, j)) break;
        }

        // Move right
        for (int j = col + 1; j < 8; j++)
        {
            if (!addMoveIfPossible(board, moves, row, j)) break;
        }

        return moves;
    }

    private boolean addMoveIfPossible(Square[][] board, ArrayList<Point> moves, int newRow, int newCol)
    {
        if(board[newRow][newCol].getPiece() == null)
        {
            moves.add(new Point(newRow, newCol));
            return true;
        } else
        {
            if(board[newRow][newCol].getPiece().getColor() != this.color)
            {
                moves.add(new Point(newRow, newCol));
            }
            return false;
        }
    }
}
