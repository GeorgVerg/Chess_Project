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
    private boolean isCaptureMove;
    private Chessboard chessboard;

    private Piece piece;

    private static final Color lightColor = new Color(240, 217, 181);
    private static final Color darkColor = new Color(181, 136, 99);
    private static final Color highlightColor = new Color(162, 209, 73, 192);
    private static final Color captureHighlightColor = new Color(209, 162, 73, 192);

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
        Square selectedSquare = chessboard.getSelectedSquare();

        if(selectedSquare != null)
        {
            if(selectedSquare.getPiece() != null && selectedSquare.getPiece().getColor() == (chessboard.getIsWhiteTurn() ? Color.WHITE : Color.BLACK))
            {
                if(isMove() || isCaptureMove())
                {
                    move(selectedSquare);
                    chessboard.toggleIsWhiteTurn();
                }
            }
            chessboard.clearPossibleMoves();
        }

        chessboard.setSelectedSquare(this);
        selectedSquare = chessboard.getSelectedSquare();

        if(piece != null && selectedSquare.getPiece().getColor() == (chessboard.getIsWhiteTurn() ? Color.WHITE : Color.BLACK))
        {
            chessboard.showPossibleMoves(piece.getPossibleMoves(chessboard.getChessboardSquares()));
            chessboard.showCaptureMoves(piece.getCaptureMoves(chessboard.getChessboardSquares()));
        }
        repaint();
    }
    
    private void move(Square selectedSquare)
    {
        Piece selectedPiece = selectedSquare.getPiece();

        this.setPiece(selectedPiece);
        selectedSquare.setPiece(null, this);
    }

    private boolean isMove()
    {
        for(Square[] row : chessboard.getChessboardSquares())
            {
                for(Square s : row)
                {
                    if(!s.isPossibleMove) { continue; }

                    if(s == this) { return true; }
                }
            }

        return false;
    }

    private boolean isCaptureMove()
    {
        for(Square[] row : chessboard.getChessboardSquares())
        {
            for(Square s : row)
            {
                if(!s.isCaptureMove) { continue; }

                if(s == this) { return true; }
            }
        }

    return false;
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
    
    public void setPiece(Piece piece, Square newSquare)
    {
        if(this.piece != null)
        {
            this.piece.movePiece(newSquare);
            this.piece.updateSquareLocation();
        }

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

    public void setCaptureMove(boolean captureMove)
    {
        isCaptureMove = captureMove;
        if(isCaptureMove && isPossibleMove) { isPossibleMove = false; }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground((row + col) % 2 == 0 ? lightColor : darkColor);

        if(isSelected || isPossibleMove && piece == null)
        {
            g.setColor(highlightColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if(isCaptureMove && piece != null)
        {
            g.setColor(captureHighlightColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if(piece != null)
        {
            piece.draw(g, 5, 5, getWidth() - 10, getHeight() - 10);
        }
    }
}
