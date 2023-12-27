package Windows;

import Database.DTS_DAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ACCOUNT_PAGE {

    private BufferedImage photo;
    private final JTextField changeEmailField;
    private final JPasswordField changePasswordField;
    private final JFrame frame;
    JButton eyeButton = getEyeButton();
    JButton eyeSlashButton = getEyeSlashButton();
    BufferedImage eye;
    BufferedImage eyeSlash;

    public ACCOUNT_PAGE() throws IOException {

        frame = new JFrame();

        BufferedImage ppMask = ImageIO.read(new File("Resources/ppMask.png"));

        JLabel ppLabel = new JLabel(new ImageIcon(ppMask));
        ppLabel.setBounds(300, -1, 110, 110);

        JTextPane namePane = new JTextPane();
        StyledDocument doc = namePane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(center, "Pt Mono");
        StyleConstants.setFontSize(center, 20);
        StyleConstants.setBold(center, true);
        StyleConstants.setForeground(center, new Color(202, 204, 220));
        namePane.setText(LOGIN_PAGE.getUserName());
        namePane.setBounds(250, 110, 200, 30);
        namePane.setBackground(new Color(32, 34,46));
        namePane.setEditable(false);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JButton ppButton = getPpButton();
        JButton changeEmailButton = getChangeEmailButton();
        JButton changePasswordButton = getChangePasswordButton();
        JButton deleteAccountButton = getDeleteAccountButton();
        JButton backButton = getBackButton();

        changeEmailField = new JTextField();
        changeEmailField.setBounds(365, 160, 175, 30);
        changeEmailField.setBackground(new Color(32, 34, 46));
        changeEmailField.setForeground(Color.white);
        changeEmailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        changeEmailField.setCaretColor(new Color(37, 153, 252));

        changePasswordField = new JPasswordField();
        changePasswordField.setBounds(365, 200, 175, 30);
        changePasswordField.setEchoChar('*');
        changePasswordField.setBackground(new Color(32, 34, 46));
        changePasswordField.setForeground(Color.white);
        changePasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(37, 153, 252)));
        changePasswordField.setCaretColor(new Color(37, 153, 252));

        frame.add(deleteAccountButton);
        frame.add(backButton);
        frame.add(eyeButton);
        frame.add(eyeSlashButton);
        frame.add(changePasswordField);
        frame.add(changePasswordButton);
        frame.add(changeEmailField);
        frame.add(changeEmailButton);
        frame.add(ppLabel);
        frame.add(ppButton);
        frame.add(namePane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setTitle("Drug Tracking System");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JButton getChangeEmailButton(){
        JButton changeEmailButton = new JButton("Change Your Email");
        changeEmailButton.setFont(new Font("Pt Mono", Font.BOLD, 14));
        changeEmailButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeEmailButton.setBounds(165, 160, 180, 30);

        changeEmailButton.addActionListener(e -> {
            String currentEmail = JOptionPane.showInputDialog (frame, "Enter your current email", "Current Email Address", JOptionPane.QUESTION_MESSAGE);
            DTS_DAO dtsDao = new DTS_DAO();
            try {
                boolean status = dtsDao.changeEmail(currentEmail, changeEmailField.getText());
                if (status) JOptionPane.showMessageDialog(frame, "Your email has been updated.", "Successful", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(frame, "Email could not be changed.", "Failed", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return changeEmailButton;
    }

    private JButton getChangePasswordButton(){
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("Pt Mono", Font.BOLD, 14));
        changePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePasswordButton.setBounds(165, 200, 180, 30);

        changePasswordButton.addActionListener(e -> {
            String currentPassword = JOptionPane.showInputDialog(frame, "Enter your current password.", "Current Password", JOptionPane.QUESTION_MESSAGE);
            DTS_DAO dtsDao = new DTS_DAO();
            try {
                char[] newPassword = changePasswordField.getPassword();
                boolean status = dtsDao.changePassword(currentPassword, newPassword);
                if (status) JOptionPane.showMessageDialog(frame, "Your password has been changed.", "Successful", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(frame, "Your password could not be changed.", "Failed", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return changePasswordButton;
    }

    private JButton getDeleteAccountButton(){
        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setFont(new Font("Pt Mono", Font.BOLD, 14));
        deleteAccountButton.setForeground(new Color(216, 22, 22));
        deleteAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteAccountButton.setBounds(275, 280, 150, 30);

        deleteAccountButton.addActionListener(e -> {
            DTS_DAO dtsDao = new DTS_DAO();
            try {
                boolean status = dtsDao.deleteAccount(LOGIN_PAGE.getUserEmail());
                if (status) {
                    JOptionPane.showMessageDialog(frame, "Your Account Has Been Deleted", "Account Deleted", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new LOGIN_PAGE();
                }
                else JOptionPane.showMessageDialog(frame, "Your Account Could not be deleted.", "Failed", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException | SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return deleteAccountButton;
    }

    private JButton getPpButton() {
        JButton changePpButton = new JButton();
        changePpButton.setIcon(new ImageIcon("Resources/person.png"));
        changePpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Border border = new LineBorder(Color.BLACK, 2, true);
        changePpButton.setBorder(border);
        changePpButton.setBorderPainted(false);
        changePpButton.setFocusable(false);
        changePpButton.setBounds(300, 10, 100, 100);


        changePpButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG, JPEG", "png", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);

            int returnVal = fileChooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File photo = fileChooser.getSelectedFile();
                changePpButton.setIcon(new ImageIcon(photo.getAbsolutePath()));
            }
        });
        return changePpButton;
    }

    private JButton getEyeButton() throws IOException {
        eye = ImageIO.read(new File("Resources/eye.png"));
        JButton eyeButton = new JButton(new ImageIcon(eye));
        eyeButton.setBorder(BorderFactory.createEmptyBorder());
        eyeButton.setContentAreaFilled(false);
        eyeButton.setBackground(new Color(255, 255, 255));
        eyeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eyeButton.setBounds(503, 195, 40, 40);

        eyeButton.addActionListener(e -> {
            changePasswordField.setEchoChar((char)0);
            eyeButton.setVisible(false);
            eyeSlashButton.setVisible(true);
        });

        return eyeButton;
    }

    private JButton getEyeSlashButton() throws IOException {
        eyeSlash = ImageIO.read(new File("Resources/eye.slash.png"));
        JButton eyeSlashButton = new JButton(new ImageIcon(eyeSlash));
        eyeSlashButton.setBorder(BorderFactory.createEmptyBorder());
        eyeSlashButton.setContentAreaFilled(false);
        eyeSlashButton.setBackground(new Color(255, 255, 255));
        eyeSlashButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eyeSlashButton.setBounds(503, 195, 40, 40);

        eyeSlashButton.setVisible(false);

        eyeSlashButton.addActionListener(e -> {
            changePasswordField.setEchoChar('*');
            eyeSlashButton.setVisible(false);
            eyeButton.setVisible(true);
        });

        return eyeSlashButton;
    }

    private JButton getBackButton() throws IOException {
        BufferedImage imageBack = ImageIO.read(new File("Resources/arrowshape.backward.png"));
        JButton backButton = new JButton(new ImageIcon(imageBack));
        backButton.setBackground(new Color(32, 34,46));
        backButton.setFocusable(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setBounds(5, 5, 50, 50);

        backButton.addActionListener(e -> {
            frame.dispose();
            try {
                new MAIN_PAGE();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return backButton;
    }

}
