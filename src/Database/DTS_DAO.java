package Database;

import java.sql.*;

public class DTS_DAO {

    String url = "jdbc:mysql://localhost:3306/ilacdb";
    String username = "root";
    String passwd = "";

    public DTS_DAO() {
    }


    public boolean query(String barcode) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, this.username, passwd);
        String query = "SELECT * FROM drugs WHERE barkod = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, barcode);
        ResultSet result = statement.executeQuery();

        return result.next();
    }

    public void add(String name, String barcode, String p, String type) throws ClassNotFoundException, SQLException{
        int price = Integer.parseInt(p);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, passwd);
        String sql = "INSERT INTO drugs (name, barkod, price, type) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, barcode);
        ps.setInt(3, price);
        ps.setString(4, type);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    public boolean remove(String barcode) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, passwd);
        String query = "DELETE FROM drugs WHERE barkod = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, barcode);
        int rows = statement.executeUpdate();

        return rows > 0;
    }

    public boolean control(String username, char[] password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, this.username, passwd);
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, String.valueOf(password));
        ResultSet result = statement.executeQuery();

        return result.next();
    }

    public boolean save(String companyName, String username, char[] password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, this.username, passwd);
        String sql = "SELECT * FROM login WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 ,username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return false;
        }

        String query = "INSERT INTO login (companyName, username, password) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1, companyName);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, String.valueOf(password));
        int result = preparedStatement.executeUpdate();

        return result > 0;
    }

    public String getDrugInformation(String barcode) throws ClassNotFoundException, SQLException{

        String drugInfo = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, this.username, passwd);
        String query = "SELECT name, barkod, price FROM drugs WHERE barkod = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, barcode);
        ResultSet rs = statement.executeQuery();

        if (rs.next()){
            String name = rs.getString("name");
            String bar = rs.getString("barkod");
            double price = rs.getDouble("price");

            drugInfo = "Name: " + name + "\nBarcode: " + bar + "\nPrice: " + price;
        }
        return drugInfo;
    }

    public boolean changeEmail(String previousEmail, String newEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, this.username, passwd);
        String query = "SELECT username FROM login WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, previousEmail);
        ResultSet rs = statement.executeQuery();

        if (rs.next()){
            String update = "UPDATE login SET username = ? WHERE username = ?";
            PreparedStatement updateStatement = connection.prepareStatement(update);
            updateStatement.setString(1, newEmail);
            updateStatement.setString(2, previousEmail);
            updateStatement.executeUpdate();
            return true;
        }
        else return false;
    }

    public boolean changePassword(String previousPassword, char[] newPassword) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, this.username, passwd);
        String query = "SELECT password FROM login WHERE password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, previousPassword);
        ResultSet rs = statement.executeQuery();

        if (rs.next()){
            String update = "UPDATE login SET password = ? WHERE password = ?";
            PreparedStatement updateStatement = connection.prepareStatement(update);
            updateStatement.setString(1, String.valueOf(newPassword));
            updateStatement.setString(2, previousPassword);
            updateStatement.executeUpdate();
            return true;
        }
        else return false;
    }

    public String getUserName(String email) throws ClassNotFoundException, SQLException {

        String name = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, this.username, passwd);
        String query = "SELECT commpanyName FROM login WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet rs = statement.executeQuery();

        if (rs.next()){
            name = rs.getString("companyName");
        }
        return name;
    }
}
