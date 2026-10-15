package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/bank_sampah"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Makassar";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean tesKoneksi() {

        try (Connection conn = getConnection()) {
            return true;

        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
            return false;
        }
    }
}