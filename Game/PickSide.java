package Chess_Project.Game;

import javax.swing.*;
import java.awt.*;

import Chess_Project.Board.Chessboard;

public class PickSide extends JFrame
{
    public PickSide()
    {
        setTitle("Chess Game");
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

        JButton blackButton = new JButton("Play as Black");
        JButton whiteButton = new JButton("Play as White");

        styleButton(blackButton);
        styleButton(whiteButton);

        blackButton.addActionListener(e -> {
            new Chessboard(false);
            SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
        });

        whiteButton.addActionListener(e -> {
            new Chessboard(true);
            SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
        });

        buttonPanel.add(Box.createVerticalGlue()); // Push everything to the center
        buttonPanel.add(whiteButton);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel.add(blackButton);
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

