package Chess_Project.Board.Pieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess_Project.Board.Square;

import java.awt.image.BufferedImage;


public class King extends Piece
{
    String fileNameW = "Chess_Project/Board/Pieces/images/white/king.png";
    String fileNameB = "Chess_Project/Board/Pieces/images/black/king.png";

    BufferedImage image;
    Color color;

    Square square;

    public King(PieceType type, Color color, Square square)
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

        // Directions for King
        int[][] directions = {
            { -1, -1 }, { -1, 0 }, { -1, 1 }, // Diagonal / up
            { 0, -1 }, { 0, 1 },              // Straight
            { 1, -1 }, { 1, 0 }, { 1, 1 }     // Diagonal / down
        };

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (isValidMove(board, newRow, newCol))
            {
                moves.add(new Point(newRow, newCol));
            }
        }

        return moves;
    }

    private boolean isValidMove(Square[][] board, int newRow, int newCol)
    {
        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length)
        {
            Square targetSquare = board[newRow][newCol];
            if(targetSquare.getPiece() == null || targetSquare.getPiece().getColor() != this.color)
            {
                return isSquareUnderAttack(board, newRow, newCol, this.color) ? false : true;
            }
        }
        return false;
    }

    private boolean isSquareUnderAttack(Square[][] board, int row, int col, Color kingColor)
    {
        for (Square[] squareRow : board)
        {
            for (Square square : squareRow)
            {
                Piece piece = square.getPiece();
                if (piece != null && piece.getColor() != kingColor)
                {
                    ArrayList<Point> attacks = piece.getCaptureMoves(board);
                    for (Point attack : attacks)
                    {
                        if (attack.x == row && attack.y == col)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<Point> getCaptureMoves(Square[][] board)
    {
        return getPossibleMoves(board);
    }

    @Override
    public void updateSquareLocation()
    {
        this.square = super.getSquare();
    }
}
