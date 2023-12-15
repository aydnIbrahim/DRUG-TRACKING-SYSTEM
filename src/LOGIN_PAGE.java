import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class LOGIN_PAGE {

    private final JFrame frame;
    private final JPasswordField passwordField;
    private final JTextField usernameField;


    public LOGIN_PAGE(){

        frame = new JFrame();

        JLabel titleLabel1 = new JLabel("WELCOME TO");
        titleLabel1.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel1.setForeground(new Color(202, 204, 220));
        titleLabel1.setBounds(260, 30, 200, 50);

        JLabel titleLabel2 = new JLabel("DRUG TRACKING SYSTEM");
        titleLabel2.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel2.setForeground(new Color(202, 204, 220));
        titleLabel2.setBounds(171, 60, 500, 50);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(37, 153, 252));
        usernameLabel.setBounds(252, 170, 100, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(37, 153, 252));
        passwordLabel.setBounds(252, 200, 100, 30);

        usernameField = new JTextField();
        usernameField.setBounds(352, 170, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(352, 200, 100, 30);
        passwordField.setEchoChar('*');

        JButton login_button = getButton();
        JButton signup_button = getjButton();

        frame.add(titleLabel1);
        frame.add(titleLabel2);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(login_button);
        frame.add(signup_button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JButton getButton() {
        JButton login_button = new JButton("LOGIN");
        login_button.setFont(new Font("Pt Mono", Font.BOLD, 15));
        login_button.setBounds(307, 270, 100, 30);

        login_button.addActionListener(e -> {
            char[] password = passwordField.getPassword ();
            ItsDao itsDao = new ItsDao();
            try {
                boolean r = itsDao.control(usernameField.getText(), password);
                if (r){
                    new MAIN_PAGE();
                    frame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(frame, "Check login information.", "Login Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return login_button;
    }

    private JButton getjButton(){
        JButton signup_button = new JButton("DON'T HAVE AN ACCOUNT SIGN UP");
        signup_button.setFont(new Font("Pt Mono", Font.BOLD, 8));
        signup_button.setBounds(257, 320, 200, 30);

        signup_button.addActionListener(e -> {
            new SIGNUP_PAGE();
            frame.dispose();
        });
        return signup_button;
    }
}
