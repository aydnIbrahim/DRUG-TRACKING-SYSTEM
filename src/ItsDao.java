import javax.print.DocFlavor;
import java.sql.*; // SQL kütüphanesini içe aktarır

// İlaç takip sistemi için veritabanı işlemlerini yapan bir sınıf
public class ItsDao {

    String url = "jdbc:mysql://localhost:3306/ilacdb"; // Veritabanı bağlantı adresi
    String username = "root"; // Veritabanı kullanıcı adı
    String passwd = ""; // Veritabanı şifresi

    public ItsDao() {
        // Yapıcı metot (constructor) içinde istisna fırlatmayın
    }

    // Veritabanında ilaç sorgusu yapar
    public ResultSet ilac_sorgu(String barkod) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC sürücüsünü yükler
            Connection connection = DriverManager.getConnection(url, username, passwd); // Veritabanına bağlanır
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Bir ifade nesnesi oluşturur
            ResultSet resultSet = statement.executeQuery("select * from ilac"); // Veritabanından ilaç tablosunu seçer

            while (resultSet.next()){ // Sonuç kümesindeki her satır için
                if (resultSet.getString(2).equals(barkod)){ // Barkod numarası eşleşiyorsa
                    return resultSet;
                }
            }
            return resultSet;

        } catch (ClassNotFoundException | SQLException e) { // Hata yakalar
            // İstisna durumunu burada yönetin
            e.printStackTrace(); // Hata mesajını yazdırır
        }
        return null; // Hata durumunda null döndürür
    }

    // Veritabanına ilaç ekler
    public void ilac_ekle(String name, String barkod, String p){
        try {
            int price = Integer.parseInt(p); // Fiyatı tam sayıya dönüştürür
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC sürücüsünü yükler
            Connection connection = DriverManager.getConnection(url, username, passwd); // Veritabanına bağlanır
            // Tablo adını ve kolonlarını belirtin
            String sql = "INSERT INTO ilac (name, barkod, price) VALUES (?, ?, ?)"; // Ekleme sorgusunu oluşturur
            // PreparedStatement nesnesi oluşturur
            PreparedStatement ps = connection.prepareStatement(sql);
            // Parametreleri ayarlar
            ps.setString(1, name); // İlaç ismini ayarlar
            ps.setString(2, barkod); // Barkod numarasını ayarlar
            ps.setInt(3, price); // Fiyatı ayarlar
            // Sorguyu çalıştırır
            ps.executeUpdate();
            // Kaynakları serbest bırakır
            ps.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) { // Hata yakalar
            // İstisna durumunu burada yönetin
            e.printStackTrace(); // Hata mesajını yazdırır
        }
    }

    public boolean ilac_sil(String barkod){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ilac");

            while (resultSet.next()){ // Sonuç kümesindeki her satır için
                if (resultSet.getString(2).equals(barkod)){ // Barkod numarası eşleşiyorsa
                    statement.executeUpdate("DELETE FROM ilac WHERE barkod = '" + barkod + "'");
                    return true;
                }
            }
            return false;

        }catch (Exception e2){
            e2.printStackTrace();
            return false;
        }
    }

    /***
    public void control(String username, String password){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, this.username, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery();
        }catch (Exception e3){
            e3.printStackTrace();
        }
    }***/


}
