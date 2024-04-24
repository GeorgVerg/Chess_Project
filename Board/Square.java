package Chess_Project.Board;
import Chess_Project.Board.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square extends JPanel
{
    private int row;
    private int col;
    private String id;

    private boolean isSelected;
    private boolean isPossibleMove;
    private Chessboard chessboard;

    private Piece piece;

    private static final Color lightColor = new Color(240, 217, 181);
    private static final Color darkColor = new Color(181, 136, 99);
    private static final Color highlightColor = new Color(162, 209, 73);

    public Square(Chessboard board, int row, int col)
    {
        this.chessboard = board;
        this.row = row;
        this.col = col;
        this.id = "" + (char) ('a' + col) + (8 - row);
        setPreferredSize(new Dimension(Chessboard.SQUARE_SIZE, Chessboard.SQUARE_SIZE));
        setBackground((row + col) % 2 == 0 ? lightColor : darkColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                handleSquareSelection();
            }
        });
    }

    private void handleSquareSelection()
    {
        if(chessboard.getSelectedSquare() != null)
        {
            chessboard.clearPossibleMoves();
        }
        chessboard.setSelectedSquare(this);
        if(piece != null)
        {
            chessboard.showPossibleMoves(piece.getPossibleMoves(chessboard.getChessboardSquares()));
        }
        repaint();
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

    public void setSelected(boolean isSelected)
    {
        this.isSelected = isSelected;
        repaint();
    }

    public void setPossibleMove(boolean possibleMove)
    {
        isPossibleMove = possibleMove;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground((row + col) % 2 == 0 ? lightColor : darkColor);

        if(piece != null)
        {
            piece.draw(g, 5, 5, getWidth() - 10, getHeight() - 10);
        }

        if(isSelected || isPossibleMove)
        {
            g.setColor(highlightColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
