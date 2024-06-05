package Chess_Project.Game;

import javax.swing.*;
import java.awt.*;

import Chess_Project.Board.Chessboard;

public class EndGame extends JFrame
{
    JPanel chessFrame;

    public EndGame(JPanel frame)
    {
        chessFrame = frame;

        setTitle("You won");
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

        JButton playAgainButton = new JButton("Play again");
        JButton closeButton = new JButton("Close");

        styleButton(playAgainButton);
        styleButton(closeButton);

        playAgainButton.addActionListener(e -> {
            new PickSide();
            SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
        });

        closeButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((Component)chessFrame).dispose();
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

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(PickSide::new);
    }
}
