
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;
import model.listplayer;
import java.util.ArrayList;

public class Loginn extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private static final String CORRECT_USERNAME = "nhi";
    private static final String CORRECT_PASSWORD = "123";

    public Loginn() {
        super("Login");        
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new java.awt.Color(102, 204, 255));
        btnLogin.setForeground(new java.awt.Color(0, 0, 0));
        
        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width - 5, this.getSize().height - 20);        
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel lblHome = new JLabel("Login");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);    
        lblHome.setFont(lblHome.getFont().deriveFont(20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel pnUsername = new JPanel();
        pnUsername.setLayout(new FlowLayout());
        pnUsername.add(new JLabel("Username:"));
        pnUsername.add(txtUsername);
        pnMain.add(pnUsername);
        
        JPanel pnPass = new JPanel();
        pnPass.setLayout(new FlowLayout());
        pnPass.add(new JLabel("Password:"));
        pnPass.add(txtPassword);
        pnMain.add(pnPass);
        
        pnMain.add(btnLogin);    
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        btnLogin.addActionListener(this);    
        
        this.setSize(400, 200);                
        this.setLocation(200, 10);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() instanceof JButton) && (((JButton) e.getSource()).equals(btnLogin))) {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (CORRECT_USERNAME.equals(username) && CORRECT_PASSWORD.equals(password)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setPosition("player"); 
                
                ArrayList<listplayer> arrTable = new ArrayList<>();
                new WaittingScreen(arrTable, user).setVisible(true);
                this.dispose(); // Đóng màn hình đăng nhập
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username and/or password!");
            }
        }
    }
    
    public static void main(String[] args) {
        Loginn myFrame = new Loginn();    
        myFrame.setVisible(true);    
    }
}
