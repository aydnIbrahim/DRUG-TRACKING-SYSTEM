package Windows;

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

public class ACCOUNT_PAGE {

    private final JFrame frame;
    private BufferedImage photo;

    public ACCOUNT_PAGE() throws IOException {

        frame = new JFrame();
        String name = "IBRAHIM HAKKI AYDIN";

        JTextPane namePane = new JTextPane();
        StyledDocument doc = namePane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(center, "Pt Mono");
        StyleConstants.setFontSize(center, 20);
        StyleConstants.setBold(center, true);
        StyleConstants.setForeground(center, new Color(202, 204, 220));
        namePane.setText(name);
        namePane.setBounds(250, 120, 200, 100);
        namePane.setBackground(new Color(32, 34,46));
        namePane.setEditable(false);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JButton ppButton = getPpButton();

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
        changeEmailButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        changeEmailButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeEmailButton.setBounds(275, 200, 150, 30);

        return changeEmailButton;
    }

    private JButton getChangePasswordButton(){
        JButton changePasswordButton = new JButton("Change Your Password");
        changePasswordButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        changePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePasswordButton.setBounds(275, 200, 150, 30);

        return changePasswordButton;
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


}
