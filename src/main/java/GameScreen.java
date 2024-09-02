import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameScreen {
    private List<String> allColors;
    private List<String> displayedColors;
    private List<String> correctColors;
    private List<String> selectedColors;
    private JFrame frame;
    private JPanel colorPanel;
    private JButton submitButton;

    public GameScreen() {
        frame = new JFrame("Trò Chơi Đoán Màu");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Khởi tạo danh sách tất cả các màu
        allColors = List.of("Đỏ", "Xanh", "Vàng", "Hồng", "Xám", "Cam", "Xanh lá", "Tím", "Nâu", "Tím nhạt",
                            "Xanh dương", "Hồng nhạt", "Vàng nhạt", "Xanh nước", "Xanh lá nhạt", "Xanh đậm",
                            "Cam sáng", "Màu be", "Xanh ô liu", "Xanh ngọc", "Xanh lá đậm", "Xanh bạc hà");

        // Tạo ba màu ngẫu nhiên
        correctColors = generateRandomColors(3);

        // Tạo danh sách màu hiển thị với ít nhất 20 màu, bao gồm ba màu chính xác
        displayedColors = generateRandomColorsIncludingCorrectColors(20, correctColors);
        selectedColors = new ArrayList<>();

        showColors();
    }

    private void showColors() {
        colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(4, 5)); // Hiển thị 4 hàng, 5 cột

        for (String color : displayedColors) {
            JButton colorButton = new JButton(color);
            colorButton.setBackground(getColorFromString(color));
            colorButton.setForeground(Color.WHITE);
            colorButton.setFont(new Font("Arial", Font.BOLD, 14));
            colorButton.setFocusPainted(false);
            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleColorSelection(colorButton, color);
                }
            });
            colorPanel.add(colorButton);
        }

        // Tạo và hiển thị thông báo
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel infoLabel = new JLabel("Chọn ba màu đã được hiển thị trước đó!");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        infoPanel.add(infoLabel);

        submitButton = new JButton("Gửi");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(34, 139, 34)); // Màu xanh lá cây
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmission();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);

        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(colorPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void handleColorSelection(JButton button, String color) {
        // Toggle màu đã chọn
        if (selectedColors.contains(color)) {
            selectedColors.remove(color);
            button.setBorder(BorderFactory.createEmptyBorder());
        } else {
            if (selectedColors.size() < 3) {
                selectedColors.add(color);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Đánh dấu màu đã chọn
            } else {
                JOptionPane.showMessageDialog(frame, "Bạn đã chọn đủ ba màu. Vui lòng gửi!");
            }
        }
    }

    private void handleSubmission() {
        if (selectedColors.size() == 3) {
            if (correctColors.containsAll(selectedColors)) {
                JOptionPane.showMessageDialog(frame, "Chúc mừng! Bạn đã chọn đúng ba màu!");
            } else {
                JOptionPane.showMessageDialog(frame, "Sai rồi! Các màu đúng là: " + String.join(", ", correctColors));
            }
            // Chuyển đến màn hình xếp hạng
            new RankingScreen();
            frame.dispose(); // Đóng màn hình trò chơi
        } else {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn ba màu trước khi gửi!");
        }
    }

    private Color getColorFromString(String colorName) {
        switch (colorName.toLowerCase()) {
            case "đỏ":
                return Color.RED;
            case "xanh":
                return Color.BLUE;
            case "vàng":
                return Color.YELLOW;
            case "hồng":
                return Color.PINK;
            case "xám":
                return Color.GRAY;
            case "cam":
                return Color.ORANGE;
            case "xanh lá":
                return Color.GREEN;
            case "tím":
                return Color.MAGENTA;
            case "nâu":
                return new Color(139, 69, 19); // Màu nâu
            case "tím nhạt":
                return new Color(221, 160, 221); // Màu tím nhạt
            case "xanh dương":
                return new Color(0, 0, 139); // Màu xanh dương đậm
            case "hồng nhạt":
                return new Color(255, 182, 193); // Màu hồng nhạt
            case "vàng nhạt":
                return new Color(255, 255, 224); // Màu vàng nhạt
            case "xanh nước":
                return new Color(0, 255, 255); // Màu xanh nước
            case "xanh lá nhạt":
                return new Color(144, 238, 144); // Màu xanh lá nhạt
            case "xanh đậm":
                return new Color(0, 128, 0); // Màu xanh đậm
            case "cam sáng":
                return new Color(255, 165, 0); // Màu cam sáng
            case "màu be":
                return new Color(245, 245, 220); // Màu be
            case "xanh ô liu":
                return new Color(128, 128, 0); // Màu xanh ô liu
            case "xanh ngọc":
                return new Color(0, 255, 127); // Màu xanh ngọc
            case "xanh lá đậm":
                return new Color(34, 139, 34); // Màu xanh lá đậm
            case "xanh bạc hà":
                return new Color(152, 251, 152); // Màu xanh bạc hà
            default:
                return Color.GRAY; // Màu mặc định
        }
    }

    private List<String> generateRandomColors(int count) {
        List<String> selectedColors = new ArrayList<>();
        Random rand = new Random();
        while (selectedColors.size() < count) {
            String color = allColors.get(rand.nextInt(allColors.size()));
            if (!selectedColors.contains(color)) {
                selectedColors.add(color);
            }
        }
        return selectedColors;
    }

    private List<String> generateRandomColorsIncludingCorrectColors(int totalCount, List<String> correctColors) {
        List<String> selectedColors = new ArrayList<>(correctColors);
        Random rand = new Random();

        // Thêm màu ngẫu nhiên cho đến khi đủ số lượng yêu cầu
        while (selectedColors.size() < totalCount) {
            String color = allColors.get(rand.nextInt(allColors.size()));
            if (!selectedColors.contains(color)) {
                selectedColors.add(color);
            }
        }

        // Xáo trộn danh sách màu để đảm bảo ba màu chính xác không ở cùng một chỗ
        Collections.shuffle(selectedColors);
        return selectedColors;
    }

    public static void main(String[] args) {
        new GameScreen();
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
