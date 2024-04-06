package Chess_Project.Board;
import Chess_Project.Board.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Square extends JPanel
{
    private int row;
    private int col;
    private String id;
    private boolean isPossibleMove;
    private boolean isSelected;
    private ArrayList<String> possibleMoves;

    private Piece piece;

    Color lightColor = new Color(240, 217, 181);
    Color darkColor = new Color(181, 136, 99);

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.id = "" + (char) ('a' + col) + (row + 1);
        this.possibleMoves = getPossibleMoves();
        setPreferredSize(new Dimension(Chessboard.SQUARE_SIZE, Chessboard.SQUARE_SIZE));
        setBackground((row + col) % 2 == 0 ? lightColor : darkColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(!isSelected)
                {
                    clearSelections();
                    isSelected = true;
                    getPossibleMoves();
                    repaint();
                }
            }
        });
    }

    public String getSquareId()
    {
        return id;
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
        if(piece == null) { return null; }
        return piece;
    }
    
    public Square getSquare() {
        return this;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }

    public void setPossibleMove(boolean possibleMove)
    {
        isPossibleMove = possibleMove;
        repaint();
    }

    public ArrayList<String>  getPossibleMoves()
    {
        if(piece == null) { return new ArrayList<>(); }
        switch(piece.getType())
        {
            case BISHOP:
                break;
            case KING:
                break;
            case KNIGHT:
                break;
            case PAWN:
                return piece.getPossibleMoves(Chessboard.getChessboardSquares());
            case QUEEN:
                break;
            case ROOK:
                break;
            default:
                break;
        }
        return possibleMoves;
    }

    public void clearSelections()
    {
        isSelected = false;
        possibleMoves.clear();
        repaint();
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
