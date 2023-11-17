import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Java GUI arayüzü sınıfı, ilaç takip sistemi ile etkileşimli bir arayüz oluşturur
public class IlacTakipArayuzu {

    JFrame frame_giris = new JFrame();

    JButton kaydet_buton = new JButton("İlaç Kaydet");
    JButton sorgula_buton = new JButton("İlaç Sorgula");

    public IlacTakipArayuzu() {

        kaydet_buton.setBounds(50, 50, 200, 40);
        sorgula_buton.setBounds(300, 50, 200, 40);

        kaydet_buton.setFocusable(false);
        sorgula_buton.setFocusable(false);

        kaydet_buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window_Kaydet window_kaydet = new Window_Kaydet();
            }
        });
        sorgula_buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window_Sorgula windows_sorgula = new Window_Sorgula();
            }
        });

        frame_giris.add(kaydet_buton);
        frame_giris.add(sorgula_buton);

        frame_giris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_giris.setSize(550, 200);
        frame_giris.setTitle("İlaç Takip Sistemi");
        frame_giris.setLayout(null);
        frame_giris.setVisible(true);

    }



}










