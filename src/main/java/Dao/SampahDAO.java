package DAO;

import Database.Koneksi;
import Model.PenyetorSampah;
import Model.PetugasSampah;
import Model.Sampah;
import Model.SampahAnorganik;
import Model.SampahOrganik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class SampahDAO {

    // Query dasar: data sampah digabung dengan data penyetor dan petugas (JOIN)
    private final String SQL_SELECT = "SELECT s.id_sampah, s.jenis, s.berat, "
            + "s.mudah_terurai, s.estimasi_hari_kompos, s.dapat_didaur_ulang, s.jenis_bahan, "
            + "p.id_penyetor, p.nama AS nama_penyetor, p.alamat AS alamat_penyetor, "
            + "t.id_petugas, t.nama AS nama_petugas, t.jabatan AS jabatan_petugas "
            + "FROM sampah s "
            + "JOIN penyetor p ON s.id_penyetor = p.id_penyetor "
            + "JOIN petugas t ON s.id_petugas = t.id_petugas";

    // Mengubah satu baris hasil query menjadi objek SampahOrganik / SampahAnorganik
    private Sampah petakanBaris(ResultSet rs) throws SQLException {

        PenyetorSampah penyetor = new PenyetorSampah(
                rs.getInt("id_penyetor"),
                rs.getString("nama_penyetor"),
                rs.getString("alamat_penyetor"));

        PetugasSampah petugas = new PetugasSampah(
                rs.getInt("id_petugas"),
                rs.getString("nama_petugas"),
                rs.getString("jabatan_petugas"));

        int id = rs.getInt("id_sampah");
        double berat = rs.getDouble("berat");
        String jenis = rs.getString("jenis");

        if (jenis.equalsIgnoreCase("Organik")) {
            return new SampahOrganik(id, berat, penyetor, petugas,
                    rs.getString("mudah_terurai"),
                    rs.getInt("estimasi_hari_kompos"));
        } else {
            return new SampahAnorganik(id, berat, penyetor, petugas,
                    rs.getString("dapat_didaur_ulang"),
                    rs.getString("jenis_bahan"));
        }
    }

    // READ: semua data
    public ArrayList<Sampah> ambilSemua() {

        ArrayList<Sampah> daftar = new ArrayList<Sampah>();
        String sql = SQL_SELECT + " ORDER BY s.id_sampah";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(petakanBaris(rs));
            }

        } catch (SQLException e) {
            System.out.println("Gagal membaca data sampah: " + e.getMessage());
        }

        return daftar;
    }

    // READ: satu data berdasarkan ID
    public Sampah cariById(int idSampah) {

        Sampah sampah = null;
        String sql = SQL_SELECT + " WHERE s.id_sampah = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSampah);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sampah = petakanBaris(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari data sampah: " + e.getMessage());
        }

        return sampah;
    }

    // READ: data berdasarkan rentang berat
    public ArrayList<Sampah> cariByRentangBerat(double beratMin, double beratMax) {

        ArrayList<Sampah> daftar = new ArrayList<Sampah>();
        String sql = SQL_SELECT + " WHERE s.berat BETWEEN ? AND ? ORDER BY s.id_sampah";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, beratMin);
            ps.setDouble(2, beratMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    daftar.add(petakanBaris(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari data berdasarkan berat: " + e.getMessage());
        }

        return daftar;
    }

    // CREATE: mengembalikan ID baru dari database (AUTO_INCREMENT), atau -1 jika gagal
    public int tambah(Sampah sampah) {

        int idBaru = -1;
        String sql = "INSERT INTO sampah (jenis, berat, id_penyetor, id_petugas, "
                + "mudah_terurai, estimasi_hari_kompos, dapat_didaur_ulang, jenis_bahan) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, sampah.getJenisSampah());
            ps.setDouble(2, sampah.getBerat());
            ps.setInt(3, sampah.getPenyetor().getIdPenyetor());
            ps.setInt(4, sampah.getPetugas().getIdPetugas());

            if (sampah instanceof SampahOrganik) {

                SampahOrganik organik = (SampahOrganik) sampah;

                ps.setString(5, organik.getMudahTerurai());
                ps.setInt(6, organik.getEstimasiHariKompos());
                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);

            } else if (sampah instanceof SampahAnorganik) {

                SampahAnorganik anorganik = (SampahAnorganik) sampah;

                ps.setNull(5, Types.VARCHAR);
                ps.setNull(6, Types.INTEGER);
                ps.setString(7, anorganik.getDapatDidaurUlang());
                ps.setString(8, anorganik.getJenisBahan());
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idBaru = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data sampah: " + e.getMessage());
        }

        return idBaru;
    }

    // UPDATE
    public boolean ubah(Sampah sampah) {

        boolean berhasil = false;
        String sql = "UPDATE sampah SET berat = ?, mudah_terurai = ?, estimasi_hari_kompos = ?, "
                + "dapat_didaur_ulang = ?, jenis_bahan = ? WHERE id_sampah = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, sampah.getBerat());

            if (sampah instanceof SampahOrganik) {

                SampahOrganik organik = (SampahOrganik) sampah;

                ps.setString(2, organik.getMudahTerurai());
                ps.setInt(3, organik.getEstimasiHariKompos());
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.VARCHAR);

            } else if (sampah instanceof SampahAnorganik) {

                SampahAnorganik anorganik = (SampahAnorganik) sampah;

                ps.setNull(2, Types.VARCHAR);
                ps.setNull(3, Types.INTEGER);
                ps.setString(4, anorganik.getDapatDidaurUlang());
                ps.setString(5, anorganik.getJenisBahan());
            }

            ps.setInt(6, sampah.getIdSampah());

            berhasil = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data sampah: " + e.getMessage());
        }

        return berhasil;
    }

    // DELETE
    public boolean hapus(int idSampah) {

        boolean berhasil = false;
        String sql = "DELETE FROM sampah WHERE id_sampah = ?";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSampah);

            berhasil = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus data sampah: " + e.getMessage());
        }

        return berhasil;
    }
}