import javax.swing.*; // Swing kütüphanesini içe aktarır
import java.awt.*; // AWT kütüphanesini içe aktarır
import java.awt.event.ActionEvent; // ActionEvent sınıfını içe aktarır
import java.awt.event.ActionListener; // ActionListener arayüzünü içe aktarır
import java.sql.ResultSet; // ResultSet sınıfını içe aktarır

// İlaç sorgulama arayüzü sınıfı, ilaç takip sistemi ile etkileşimli bir arayüz oluşturur
public class Window_Sorgula extends JFrame {

    JFrame frame = new JFrame(); // Ana pencereyi oluşturur
    private JPanel panel_sorgu; // Sorgu panelini tanımlar
    private JButton sorgu_buton; // Sorgu butonunu tanımlar
    private JButton sil_buton;
    private JTextField ilac_barkod_alan; // İlaç barkod alanını tanımlar
    private JLabel ilac_barkod_etiket; // İlaç barkod etiketini tanımlar

    public Window_Sorgula(){

        panel_sorgu = new JPanel(); // Sorgu panelini oluşturur
        panel_sorgu.setLayout(new GridLayout(6, 2)); // Sorgu panelinin düzenini ayarlar

        sorgu_buton = new JButton("Sorgula"); // Sorgu butonunu oluşturur
        sil_buton = new JButton("Kaydı Sil");
        sorgu_buton.addActionListener(new ActionListener() { // Sorgu butonuna tıklandığında çalışacak kodu tanımlar
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ItsDao itsDao = new ItsDao(); // Yeni bir ItsDao nesnesi oluşturur
                    ResultSet rs = itsDao.ilac_sorgu(ilac_barkod_alan.getText()); // İlaç barkod alanındaki değere göre ilaç sorgusu yapar
                    if (rs.next()){ // Sorgu sonucu varsa
                        if(rs.previous())
                            JOptionPane.showMessageDialog(frame, "İlaç Adı: " + rs.getString(1) + // İlaç adını alır
                                            "\nBarkod No: " + rs.getString(2) + // Barkod no'yu alır
                                            "\nFiyat: " + rs.getInt(3), // Fiyatı alır
                                    "İlaç Bilgileri", // Mesaj başlığı
                                    JOptionPane.INFORMATION_MESSAGE); // Mesaj tipi
                    } else if (rs.previous()) {
                            JOptionPane.showMessageDialog(frame, "İlaç Adı: " + rs.getString(1) + // İlaç adını alır
                                            "\nBarkod No: " + rs.getString(2) + // Barkod no'yu alır
                                            "\nFiyat: " + rs.getInt(3), // Fiyatı alır
                                    "İlaç Bilgileri", // Mesaj başlığı
                                    JOptionPane.INFORMATION_MESSAGE); // Mesaj tipi
                    } else{
                        JOptionPane.showMessageDialog(frame, "Aranan barkoda ait sonuç bulunamadı.", "Sorgu Bulunamadı", JOptionPane.ERROR_MESSAGE); // Hata mesajı gösterir
                    }
                }
                catch (Exception ez){ // Hata yakalar
                    ez.printStackTrace();
                }
            }
        });

        sil_buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItsDao itsDao = new ItsDao();
                boolean st = itsDao.ilac_sil(ilac_barkod_alan.getText());
                if(st)
                    JOptionPane.showMessageDialog(frame, "Silme işlemi başarılı.", "Kayıt Silindi", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "Silme işlemi başarısız. Aranan barkoda ait kayıt bulunamadı.", "Silme Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });

        ilac_barkod_alan = new JTextField();  // Yeni bir barkod no giriş alanı oluşturur

        ilac_barkod_etiket = new JLabel("barkod no");   // Yeni bir barkod no etiketi oluşturur

        panel_sorgu.add(ilac_barkod_etiket); // Barkod no etiketini sorgu paneline ekler
        panel_sorgu.add(ilac_barkod_alan); // Barkod no alanını sorgu paneline ekler

        panel_sorgu.add(sorgu_buton); // Sorgu butonunu sorgu paneline ekler
        panel_sorgu.add(sil_buton);

        add(panel_sorgu); // Sorgu panelini ana pencereye ekler
        setTitle("İlaç Sorgula"); // Ana pencerenin başlığını ayarlar
        setSize(400, 300); // Ana pencerenin boyutunu ayarlar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ana pencerenin kapatılma davranışını ayarlar
        setVisible(true); // Ana pencerenin görünürlüğünü ayarlar
        setLocationRelativeTo(null); // Ana pencerenin ortalanmasını sağlar
    }
}
