import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.User;
import model.listplayer;

public class WaittingScreen extends JFrame implements ActionListener {
    private JTable tblTable;
    private ArrayList<listplayer> arrTable;
    private JButton btnStart;
    private listplayer selectedPlayer; 

    public WaittingScreen(ArrayList<listplayer> arrTable, User user) {
        super("Danh sách người chơi");
        this.arrTable = arrTable;
        this.selectedPlayer = null; 
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());


        tblTable = new JTable();
        tblTable.setBackground(new java.awt.Color(102, 204, 255));
        tblTable.setForeground(new java.awt.Color(0, 0, 0));
        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Nhi", "100", "online"},
                {"Nhii", "200", "offline"},
                {"Nhiii", "300", "đang chơi"},
                {null, null, null}
            },
            new String [] {
                "Name", "Score", "Status"
            }
        ));
        JScrollPane scrollPane = new JScrollPane(tblTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Thêm sự kiện khi chọn hàng trong bảng
        tblTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < arrTable.size()) {
                    selectedPlayer = arrTable.get(row); // Lưu người chơi được chọn
                }
            }
        });

        // Tạo và cấu hình nút Start
        btnStart = new JButton("Start");
        btnStart.addActionListener(this);
        panel.add(btnStart, BorderLayout.SOUTH);

        this.add(panel);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStart) {
            new GameScreen().setVisible(true);
            this.dispose(); 
        }
    }

    public static void main(String[] args) {
        // Tạo đối tượng giả cho listplayer và User
        ArrayList<listplayer> arrTable = new ArrayList<>();
        User user = new User(); // Tạo đối tượng User nếu cần thiết
        new WaittingScreen(arrTable, user).setVisible(true);
        
    }
}
