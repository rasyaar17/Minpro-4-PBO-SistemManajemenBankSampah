package DAO;

import Database.Koneksi;
import Model.PetugasSampah;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PetugasDAO {

    public ArrayList<PetugasSampah> ambilSemua() {

        ArrayList<PetugasSampah> daftar = new ArrayList<PetugasSampah>();
        String sql = "SELECT id_petugas, nama, jabatan FROM petugas ORDER BY id_petugas";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new PetugasSampah(
                        rs.getInt("id_petugas"),
                        rs.getString("nama"),
                        rs.getString("jabatan")));
            }

        } catch (SQLException e) {
            System.out.println("Gagal membaca data petugas: " + e.getMessage());
        }

        return daftar;
    }

    public PetugasSampah cariById(int idPetugas) {

        PetugasSampah petugas = null;
        String sql = "SELECT id_petugas, nama, jabatan FROM petugas WHERE id_petugas = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPetugas);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    petugas = new PetugasSampah(
                            rs.getInt("id_petugas"),
                            rs.getString("nama"),
                            rs.getString("jabatan"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari petugas: " + e.getMessage());
        }

        return petugas;
    }

    public boolean namaSudahAda(String nama) {

        boolean ada = false;
        String sql = "SELECT COUNT(*) FROM petugas WHERE LOWER(nama) = LOWER(?)";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ada = rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal memeriksa nama petugas: " + e.getMessage());
        }

        return ada;
    }

    // Mengembalikan ID baru dari database, atau -1 jika gagal
    public int tambah(PetugasSampah petugas) {

        int idBaru = -1;
        String sql = "INSERT INTO petugas (nama, jabatan) VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, petugas.getNamaPetugas());
            ps.setString(2, petugas.getJabatan());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idBaru = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal menyimpan petugas: " + e.getMessage());
        }

        return idBaru;
    }
}