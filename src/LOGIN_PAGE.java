import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class LOGIN_PAGE {

    private final JFrame frame;
    private final JPasswordField passwordField;
    private final JTextField emailField;


    public LOGIN_PAGE() throws IOException{

        frame = new JFrame();

        JLabel titleLabel1 = new JLabel("WELCOME TO");
        titleLabel1.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel1.setForeground(new Color(202, 204, 220));
        titleLabel1.setBounds(260, 30, 200, 50);

        JLabel titleLabel2 = new JLabel("DRUG TRACKING SYSTEM");
        titleLabel2.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel2.setForeground(new Color(202, 204, 220));
        titleLabel2.setBounds(171, 60, 500, 50);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        emailLabel.setForeground(new Color(37, 153, 252));
        emailLabel.setBounds(252, 170, 100, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(37, 153, 252));
        passwordLabel.setBounds(252, 200, 100, 30);

        emailField = new JTextField();
        emailField.setBounds(352, 170, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(352, 200, 100, 30);
        passwordField.setEchoChar('*');

        JButton login_button = getLoginButton();
        JButton signup_button = getSignupButton();

        frame.add(titleLabel1);
        frame.add(titleLabel2);
        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(emailField);
        frame.add(passwordField);
        frame.add(login_button);
        frame.add(signup_button);

        SwingUtilities.getRootPane(login_button).setDefaultButton(login_button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JButton getLoginButton() {
        JButton login_button = new JButton("LOGIN");
        login_button.setFont(new Font("Pt Mono", Font.BOLD, 15));
        login_button.setBounds(307, 270, 100, 30);

        login_button.addActionListener(e -> {
            char[] password = passwordField.getPassword ();
            DTS_DAO itsDao = new DTS_DAO();
            try {
                boolean r = itsDao.control(emailField.getText(), password);
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

    private JButton getSignupButton(){
        JButton signup_button = new JButton("DON'T HAVE AN ACCOUNT? SIGN UP");
        signup_button.setFont(new Font("Pt Mono", Font.BOLD, 10));
        signup_button.setForeground(Color.white);
        signup_button.setBorderPainted(false);
        signup_button.setFocusable(false);
        signup_button.setBounds(235, 320, 250, 30);

        signup_button.addActionListener(e -> {
            try {
                new SIGNUP_PAGE();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
        });
        return signup_button;
    }

}
