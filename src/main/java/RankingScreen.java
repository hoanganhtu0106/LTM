import javax.swing.*;
import java.awt.*;

public class RankingScreen {
    public RankingScreen() {
        JFrame frame = new JFrame("Xếp Hạng");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); 
        JLabel rankingLabel = new JLabel("XẾP HẠNG", SwingConstants.CENTER);
        rankingLabel.setFont(new Font("Arial", Font.BOLD, 28));
        rankingLabel.setForeground(Color.WHITE);
        headerPanel.add(rankingLabel);

        frame.add(headerPanel, BorderLayout.NORTH);

        JPanel rankingPanel = new JPanel(new BorderLayout());
        rankingPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        rankingPanel.setBackground(new Color(240, 248, 255));

        // Danh sách xếp hạng
        JTextArea rankingList = new JTextArea();
        rankingList.setEditable(false);
        rankingList.setFont(new Font("Arial", Font.PLAIN, 18));
        rankingList.setText("1. Người Chơi A - 10 điểm\n2. Người Chơi B - 8 điểm\n3. Người Chơi C - 6 điểm");
        rankingList.setBackground(new Color(255, 255, 255));
        rankingList.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));

        JScrollPane scrollPane = new JScrollPane(rankingList);
        rankingPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(rankingPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255)); 

        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 69, 0)); 
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
        backButton.addActionListener(e -> frame.dispose()); 

        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RankingScreen::new);
    }
}
