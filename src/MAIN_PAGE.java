import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MAIN_PAGE {

    private final JFrame frame;

    public MAIN_PAGE() throws IOException {

        frame = new JFrame();

        BufferedImage image = ImageIO.read(new File("/Users/ibrahimaydin/Desktop/Ilac_takip_sistemi/Resources/mainPageImage.jpg"));

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(50,50, 300, 300);

        JTextArea saveArea = new JTextArea("--> Click the save button for saving operations.");
        saveArea.setFont(new Font("Pt Mono", Font.BOLD, 13));
        saveArea.setForeground(Color.WHITE);
        saveArea.setBackground(new Color(32, 34,46));
        saveArea.setBounds(375, 90, 300, 40);
        saveArea.setEditable(false);
        saveArea.setLineWrap(true);
        saveArea.setWrapStyleWord(true);

        JTextArea queryArea = new JTextArea("--> Click the query button for query and delete operations.");
        queryArea.setFont(new Font("Pt Mono", Font.BOLD, 13));
        queryArea.setForeground(Color.WHITE);
        queryArea.setBackground(new Color(32, 34,46));
        queryArea.setBounds(375, 130, 300, 30);
        queryArea.setEditable(false);
        queryArea.setLineWrap(true);
        queryArea.setWrapStyleWord(true);


        JButton saveButton = getButton();
        JButton queryButton = getjButton();

        frame.add(imageLabel);
        frame.add(saveArea);
        frame.add(queryArea);
        frame.add(saveButton);
        frame.add(queryButton);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 428);
        frame.setResizable(false);
        frame.setTitle("DRUG TRACKING SYSTEM");
        frame.getContentPane().setBackground(new Color(32, 34,46));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    private JButton getButton(){
        JButton saveButton = new JButton("QUERY");
        saveButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        saveButton.setBounds(450, 230, 150, 30);

        saveButton.addActionListener(e -> {
            new SAVE_PAGE();
            frame.dispose();
        });

        return saveButton;
    }

    private JButton getjButton(){
        JButton queryButton = new JButton("SAVE");
        queryButton.setFont(new Font("Pt Mono", Font.BOLD, 15));
        queryButton.setBounds(450, 270, 150, 30);

        queryButton.addActionListener(e -> {
            new QUERY_PAGE();
            frame.dispose();
        });

        return queryButton;
    }

}
