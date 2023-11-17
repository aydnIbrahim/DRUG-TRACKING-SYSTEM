import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Window_Kaydet{

    // ilaclar listesi, tüm ilaçları tutar
    static ArrayList<Ilac> ilaclar = new ArrayList<>();

    // ilaclar.txt dosyasının yolu
    static String dosya_yolu = "ilaclar.txt";

    JFrame frame = new JFrame();

    private JPanel panel_kaydet;
    private JButton kaydet_buton;
    private JTextField ilac_isim_alan;      // İlaç isim giriş alanı
    private JTextField ilac_barkod_alan;    // İlaç barkodu giriş alanı
    private JTextField ilac_fiyat_alan;     // İlaç fiyatı giriş alanı
    private JLabel ilac_isim_etiket;        // İlaç ismi etiketi
    private JLabel ilac_barkod_etiket;      // İlaç barkod etiketi
    private JLabel ilac_fiyat_etiket;       // İlaç fiyat etiketi


    public Window_Kaydet() {

        panel_kaydet = new JPanel();
        panel_kaydet.setLayout(new GridLayout(6, 2));       // Panelin düzenini 6 satır 2 sütun olarak ayarlar

        kaydet_buton = new JButton("Kaydet");
        kaydet_buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ilac_isim_alan.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"İsim bilgisi boş bırakılamaz.", "Kaydetme Hatası", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(!(ilac_barkod_alan.getText().length() == 13)){
                    JOptionPane.showMessageDialog(frame,"Lütfen barkod no bilgisini kontrol edin.", "Kaydetme Hatası", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(ilac_fiyat_alan.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Fiyat bilgisi boş bırakılamaz.", "Kaydetme Hatası", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    Ilac yeni_ilac = new Ilac(ilac_isim_alan.getText(), ilac_barkod_alan.getText(), Integer.parseInt(ilac_fiyat_alan.getText())); // Yeni bir ilaç nesnesi oluştur
                    ilaclar.add(yeni_ilac); // ilaclar listesine ekle
                    try {
                        FileWriter writer = new FileWriter(dosya_yolu, true); // dosyayı ekleme modunda aç
                        writer.write(yeni_ilac.getIsim() + "," + yeni_ilac.getBarkod() + "," + yeni_ilac.getFiyat() + "\n"); // yeni ilacın bilgilerini dosyaya yaz
                        writer.close(); // dosyayı kapat
                    } catch (Exception ez) {
                        System.out.println("Bir hata oluştu."); // Hata durumunda mesaj yaz
                    }

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(dosya_yolu)); // dosyayı okumak için reader oluştur
                        String line = reader.readLine(); // ilk satırı oku
                        while (line != null) { // satır boş olana kadar
                            String[] parts = line.split(","); // satırı virgüle göre böl
                            String isim = parts[0]; // ilk parça ilaç ismi
                            String barkod = parts[1]; // ikinci parça barkod no
                            double fiyat = Double.parseDouble(parts[2]); // üçüncü parça satış fiyatı
                            Ilac ilac = new Ilac(isim, barkod, fiyat); // yeni bir ilaç nesnesi oluştur
                            ilaclar.add(ilac); // ilaclar listesine ekle
                            line = reader.readLine(); // bir sonraki satırı oku
                        }
                        reader.close(); // reader'ı kapat
                    } catch (Exception ea) {
                        System.out.println("Bir hata oluştu."); // Hata durumunda mesaj yaz
                    }
                }
            }
        });

        ilac_isim_alan = new JTextField();    // Yeni bir ilaç ismi giriş alanı oluşturur
        ilac_barkod_alan = new JTextField();  // Yeni bir barkod no giriş alanı oluşturur
        ilac_fiyat_alan = new JTextField();   // Yeni bir ilaç fiyat giriş alanı oluşturur

        ilac_isim_etiket = new JLabel("ilaç ismi");     // Yeni bir ilaç ismi etiketi oluşturur
        ilac_barkod_etiket = new JLabel("barkod no");   // Yeni bir barkod no etiketi oluşturur
        ilac_fiyat_etiket = new JLabel("ilaç fiyatı");  // Yeni bir ilaç fiyatı etiketi oluşturur

        panel_kaydet.add(ilac_isim_etiket);        // Panelin ilk hücresine ilaç ismi etiketini ekler
        panel_kaydet.add(ilac_isim_alan);          // Panelin ikinci hücresine ilaç ismi giriş alanını ekler

        panel_kaydet.add(ilac_barkod_etiket);      // Panelin üçüncü hücresine ilaç barkodu etiketini ekler
        panel_kaydet.add(ilac_barkod_alan);        // Panelin dördündü hücresine ilaç barkod no giriş alanını ekler

        panel_kaydet.add(ilac_fiyat_etiket);       // Panelin beşinci hücresine ilaç fiyat etiketini ekler
        panel_kaydet.add(ilac_fiyat_alan);         // Panelin altıncı hücresine ilaç fiyat giriş alanını ekler

        panel_kaydet.add(kaydet_buton);

        frame.add(panel_kaydet);
        frame.setTitle("İlaç Kaydet");                     // Arayüzün başlığını ayarlar
        frame.setSize(400, 300);                     // Arayüzün boyutunu ayarlar
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     // Arayüzün kapatılma davranışını ayarlar
        frame.setVisible(true);   // Arayüzün görünürlüğünü ayarlar
    }
}
