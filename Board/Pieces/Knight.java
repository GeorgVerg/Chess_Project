package Chess_Project.Board.Pieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess_Project.Board.Square;

import java.awt.image.BufferedImage;


public class Knight extends Piece
{
    String fileNameW = "Chess_Project/Board/Pieces/images/white/knight.png";
    String fileNameB = "Chess_Project/Board/Pieces/images/black/knight.png";

    BufferedImage image;
    Color color;

    Square square;

    public Knight(PieceType type, Color color, Square square)
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

        // All potential moves of a Knight
        int[][] potentialMoves = {
            {row - 2, col + 1}, {row - 1, col + 2}, {row + 1, col + 2}, {row + 2, col + 1},
            {row + 2, col - 1}, {row + 1, col - 2}, {row - 1, col - 2}, {row - 2, col - 1}
        };

        for (int[] move : potentialMoves) {
            if (isValidMove(board, move[0], move[1])) {
                moves.add(new Point(move[0], move[1]));
            }
        }

        return moves;
    }
    
    private boolean isValidMove(Square[][] board, int newRow, int newCol)
    {
        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
            Square targetSquare = board[newRow][newCol];
            if (targetSquare.getPiece() == null || targetSquare.getPiece().getColor() != this.color) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ArrayList<Point> getCaptureMoves(Square[][] board)
    {
        ArrayList<Point> captureMoves = new ArrayList<>();
        
        for(Point move : getPossibleMoves(board))
        {
            int x = move.x;
            int y = move.y;
            Square s = board[x][y].getSquare();

            if(s.getPiece() != null && s.getPiece().getColor() != this.color)
            {
                captureMoves.add(new Point(x, y));
            }
        }
        return captureMoves;
    }

    @Override
    public void updateSquareLocation()
    {
        this.square = super.getSquare();
    }
}
