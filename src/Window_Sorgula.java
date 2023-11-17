import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Window_Sorgula extends JFrame {

    JFrame frame = new JFrame();

    // ilaclar listesi, tüm ilaçları tutar
    static ArrayList<Ilac> ilaclar = new ArrayList<>();

    // ilaclar.txt dosyasının yolu
    static String dosya_yolu = "ilaclar.txt";

    private JPanel panel_sorgu;
    private JButton sorgu_buton;
    private JTextField ilac_barkod_alan;
    private JLabel ilac_barkod_etiket;

    public Window_Sorgula(){

        panel_sorgu = new JPanel();
        panel_sorgu.setLayout(new GridLayout(6, 2));

        sorgu_buton = new JButton("Sorgula");
        sorgu_buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ilac ilac : ilaclar) { // ilaclar listesindeki her ilaç için
                    if (ilac.getBarkod().equals(ilac_barkod_alan.getText())) { // barkod numarası eşleşiyorsa
                        System.out.println(ilac); // ilacı ekrana yaz
                    }
                }
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(dosya_yolu)); // dosyayı okumak için reader oluştur
                    String line = reader.readLine(); // ilk satırı oku
                    while (line != null) { // satır boş olana kadar
                        String[] parts = line.split(","); // satırı virgüle göre böl
                        String isim = parts[0]; // ilk parça ilaç ismi
                        String barkod = parts[1]; // ikinci parça barkod no
                        double fiyat = Double.parseDouble(parts[2]); // üçüncü parça satış fiyatı
                        if (barkod.equals(ilac_barkod_alan.getText())) { // barkod numarası eşleşiyorsa
                            System.out.println(line); // satırı ekrana yaz
                            reader.close(); // reader'ı kapat
                        }
                        line = reader.readLine(); // bir sonraki satırı oku
                    }
                    reader.close(); // reader'ı kapat
                } catch (Exception es) {
                    System.out.println("Bir hata oluştu."); // Hata durumunda mesaj yaz
                }
            }
        });

        ilac_barkod_alan = new JTextField();  // Yeni bir barkod no giriş alanı oluşturur

        ilac_barkod_etiket = new JLabel("barkod no");   // Yeni bir barkod no etiketi oluşturur

        panel_sorgu.add(ilac_barkod_etiket);
        panel_sorgu.add(ilac_barkod_alan);

        panel_sorgu.add(sorgu_buton);

        add(panel_sorgu);
        setTitle("İlaç Sorgula");                     // Arayüzün başlığını ayarlar
        setSize(400, 300);                     // Arayüzün boyutunu ayarlar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     // Arayüzün kapatılma davranışını ayarlar
        setVisible(true);   // Arayüzün görünürlüğünü ayarlar

    }

}
