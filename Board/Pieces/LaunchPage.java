package Chess_Project.Board.Pieces;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import Chess_Project.Board.Square;

public class LaunchPage implements ActionListener
{
    JFrame frame = new JFrame();
    JButton queenButton = new JButton("Queen");
    JButton rookButton = new JButton("Rook");
    JButton knightButton = new JButton("Knight");
    JButton bishopButton = new JButton("Bishop");


    Color color;
    Square square;
    Square squareBefore;

    public LaunchPage(Color color, Square square, Square squareBefore)
    {
        queenButton.setBounds(50, 200, 200, 40);
        queenButton.setFocusable(false);
        queenButton.addActionListener(this);
        frame.add(queenButton);
        
        rookButton.setBounds(50, 150, 200, 40);
        rookButton.setFocusable(false);
        rookButton.addActionListener(this);
        frame.add(rookButton);
    
        knightButton.setBounds(50, 100, 200, 40);
        knightButton.setFocusable(false);
        knightButton.addActionListener(this);
        frame.add(knightButton);

        bishopButton.setBounds(50, 50, 200, 40);
        bishopButton.setFocusable(false);
        bishopButton.addActionListener(this);
        frame.add(bishopButton);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setTitle("Promote your pawn to:");
        frame.setVisible(true);

        this.color = color;
        this.square = square;
        this.square = squareBefore;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == queenButton)
        {
            frame.dispose();
            square.setPiece(new Queen(PieceType.QUEEN, color, square));
            // squareBefore.setPiece(null);
        } else if(e.getSource() == rookButton)
        {
            frame.dispose();
            square.setPiece(new Rook(PieceType.ROOK, color, square));
            // squareBefore.setPiece(null);
        } else if(e.getSource() == knightButton)
        {
            frame.dispose();
            square.setPiece(new Knight(PieceType.KNIGHT, color, square));
            // squareBefore.setPiece(null);
        } else if(e.getSource() == bishopButton)
        {
            frame.dispose();
            square.setPiece(new Bishop(PieceType.BISHOP, color, square));
            // squareBefore.setPiece(new Piece());
        }
        square.repaint();
    }
}
