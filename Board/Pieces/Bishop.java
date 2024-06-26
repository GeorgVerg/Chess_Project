package Chess_Project.Board.Pieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Chess_Project.Board.Square;

import java.awt.image.BufferedImage;


public class Bishop extends Piece
{
    String fileNameW = "Chess_Project/Board/Pieces/images/white/bishop.png";
    String fileNameB = "Chess_Project/Board/Pieces/images/black/bishop.png";

    BufferedImage image;
    Color color;

    Square square;

    public Bishop(PieceType type, Color color, Square square)
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

        // Move diagonally: up-left
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (!super.addMoveIfPossible(board, moves, i, j)) break;
        }

        // Move diagonally: up-right
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            if (!super.addMoveIfPossible(board, moves, i, j)) break;
        }

        // Move diagonally: down-left
        for (int i = row + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
            if (!super.addMoveIfPossible(board, moves, i, j)) break;
        }

        // Move diagonally: down-right
        for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
            if (!super.addMoveIfPossible(board, moves, i, j)) break;
        }

        return moves;
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

    public ArrayList<Point> getCaptureMovesForKing(Square[][] board)
    {
        return getPossibleMoves(board);
    }

    @Override
    public void updateSquareLocation()
    {
        this.square = super.getSquare();
    }
}
