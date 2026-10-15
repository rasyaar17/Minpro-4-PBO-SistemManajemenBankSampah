package DAO;

import Database.Koneksi;
import Model.PenyetorSampah;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PenyetorDAO {

    public ArrayList<PenyetorSampah> ambilSemua() {

        ArrayList<PenyetorSampah> daftar = new ArrayList<PenyetorSampah>();
        String sql = "SELECT id_penyetor, nama, alamat FROM penyetor ORDER BY id_penyetor";

        // try-with-resources: koneksi dan statement otomatis ditutup
        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new PenyetorSampah(
                        rs.getInt("id_penyetor"),
                        rs.getString("nama"),
                        rs.getString("alamat")));
            }

        } catch (SQLException e) {
            System.out.println("Gagal membaca data penyetor: " + e.getMessage());
        }

        return daftar;
    }

    public PenyetorSampah cariById(int idPenyetor) {

        PenyetorSampah penyetor = null;
        String sql = "SELECT id_penyetor, nama, alamat FROM penyetor WHERE id_penyetor = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPenyetor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    penyetor = new PenyetorSampah(
                            rs.getInt("id_penyetor"),
                            rs.getString("nama"),
                            rs.getString("alamat"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari penyetor: " + e.getMessage());
        }

        return penyetor;
    }

    public boolean namaSudahAda(String nama) {

        boolean ada = false;
        String sql = "SELECT COUNT(*) FROM penyetor WHERE LOWER(nama) = LOWER(?)";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ada = rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal memeriksa nama penyetor: " + e.getMessage());
        }

        return ada;
    }

    // Mengembalikan ID baru dari database, atau -1 jika gagal
    public int tambah(PenyetorSampah penyetor) {

        int idBaru = -1;
        String sql = "INSERT INTO penyetor (nama, alamat) VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, penyetor.getNamaPenyetor());
            ps.setString(2, penyetor.getAlamat());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idBaru = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal menyimpan penyetor: " + e.getMessage());
        }

        return idBaru;
    }
}