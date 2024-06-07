package Chess_Project.Game;

import javax.swing.*;
import java.awt.*;

import Chess_Project.Board.Chessboard;

public class errorMessage extends JFrame
{
    Chessboard chessboard;
    JPanel chessFrame;

    public errorMessage(Chessboard chessboard, JPanel j)
    {
        this.chessboard = chessboard;
        chessFrame = j;

        setTitle("You did something illegal...");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.DARK_GRAY);

        JButton playAgainButton = new JButton("Go back");
        JButton closeButton = new JButton("Game Over?");

        styleButton(playAgainButton);
        styleButton(closeButton);

        playAgainButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
        });

        closeButton.addActionListener(e -> {
            new EndGame(chessFrame);
            SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
        });

        buttonPanel.add(Box.createVerticalGlue()); // Push everything to the center
        buttonPanel.add(closeButton);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createVerticalGlue());

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void styleButton(JButton button)
    {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(160, 50));
        button.setMaximumSize(new Dimension(160, 50));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 70));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
