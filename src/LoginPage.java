import javax.swing.*;
import java.awt.*;

public class LoginPage {

    JFrame frame = new JFrame();
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JLabel titleLabel1;
    private JLabel titleLabel2;
    private JTextField usernameField;
    private JButton login_button;


    public LoginPage(){

        titleLabel1 = new JLabel("WELCOME TO");
        titleLabel1.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel1.setForeground(new Color(202, 204, 220));
        titleLabel1.setBounds(260, 30, 200, 50);

        titleLabel2 = new JLabel("DRUG TRACKING SYSTEM");
        titleLabel2.setFont(new Font("Pt Mono", Font.BOLD, 30));
        titleLabel2.setForeground(new Color(202, 204, 220));
        titleLabel2.setBounds(171, 60, 500, 50);

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(37, 153, 252));
        usernameLabel.setBounds(252, 170, 100, 30);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Pt Mono", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(37, 153, 252));
        passwordLabel.setBounds(252, 200, 100, 30);

        usernameField = new JTextField();
        usernameField.setBounds(352, 170, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(352, 200, 100, 30);
        passwordField.setEchoChar('*');

        login_button = new JButton("LOGIN");
        login_button.setFont(new Font("Pt Mono", Font.BOLD, 15));
        login_button.setBounds(300, 270, 100, 30);

        login_button.addActionListener(e -> {

        });

        frame.add(titleLabel1);
        frame.add(titleLabel2);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(login_button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


    }

}
