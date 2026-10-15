package Controller;

import DAO.PenyetorDAO;
import DAO.PetugasDAO;
import DAO.SampahDAO;
import Model.LayananDaurUlang;
import Model.PenyetorSampah;
import Model.PetugasSampah;
import Model.Sampah;
import Model.SampahAnorganik;
import Model.SampahOrganik;
import java.util.ArrayList;
import java.util.Scanner;

public class SampahCRUD {

    private SampahDAO sampahDAO;
    private PenyetorDAO penyetorDAO;
    private PetugasDAO petugasDAO;

    public SampahCRUD() {
        sampahDAO = new SampahDAO();
        penyetorDAO = new PenyetorDAO();
        petugasDAO = new PetugasDAO();
    }

    private String ulang(String simbol, int jumlah) {

        String hasil = "";

        for (int i = 0; i < jumlah; i++) {
            hasil += simbol;
        }

        return hasil;
    }

    private String tengah(String teks, int lebar) {

        int kiri = (lebar - teks.length()) / 2;
        String hasil = ulang(" ", kiri) + teks;

        while (hasil.length() < lebar) {
            hasil += " ";
        }

        return hasil;
    }

    private String formatRupiah(double nilai) {
        return "Rp " + String.format("%,.0f", nilai);
    }

    private String garisTabel() {
        return "+" + ulang("-", 6)
                + "+" + ulang("-", 11)
                + "+" + ulang("-", 11)
                + "+" + ulang("-", 16)
                + "+" + ulang("-", 16)
                + "+" + ulang("-", 13)
                + "+" + ulang("-", 7) + "+";
    }

    private int hitungTotalPoin(PenyetorSampah penyetor, ArrayList<Sampah> semuaSampah) {

        int total = 0;

        for (Sampah sampah : semuaSampah) {
            if (sampah.getPenyetor().getIdPenyetor() == penyetor.getIdPenyetor()) {
                total += sampah.hitungPoinHijau();
            }
        }

        return total;
    }

    private String tentukanLevel(int poin) {

        if (poin >= 100) {
            return "Emas";
        } else if (poin >= 50) {
            return "Perak";
        } else {
            return "Perunggu";
        }
    }

    private PenyetorSampah pilihPenyetor(Scanner scanner) {

        ArrayList<PenyetorSampah> daftar = penyetorDAO.ambilSemua();

        System.out.println("\nDaftar penyetor:");

        for (PenyetorSampah p : daftar) {
            System.out.println(p.getIdPenyetor() + ". " + p.getNamaPenyetor());
        }

        PenyetorSampah penyetor = null;

        while (penyetor == null) {

            int id = InputCek.cekAngka(scanner, "Masukkan ID penyetor: ", 1, 99999);
            penyetor = penyetorDAO.cariById(id);

            if (penyetor == null) {
                System.out.println("ID penyetor tidak ditemukan!");
            }
        }

        return penyetor;
    }

    private PetugasSampah pilihPetugas(Scanner scanner) {

        ArrayList<PetugasSampah> daftar = petugasDAO.ambilSemua();

        System.out.println("\nDaftar petugas:");

        for (PetugasSampah t : daftar) {
            System.out.println(t.getIdPetugas() + ". " + t.getNamaPetugas());
        }

        PetugasSampah petugas = null;

        while (petugas == null) {

            int id = InputCek.cekAngka(scanner, "Masukkan ID petugas: ", 1, 99999);
            petugas = petugasDAO.cariById(id);

            if (petugas == null) {
                System.out.println("ID petugas tidak ditemukan!");
            }
        }

        return petugas;
    }

    private void barisStruk(String label, String isi) {
        System.out.printf("| %-13s: %-21.21s |%n", label, isi);
    }

    private void cetakStruk(Sampah sampah) {

        String garis = "+" + ulang("-", 38) + "+";

        System.out.println();
        System.out.println(garis);
        System.out.println("|" + tengah("STRUK SETORAN SAMPAH", 38) + "|");
        System.out.println(garis);

        barisStruk("ID Sampah", "" + sampah.getIdSampah());
        barisStruk("Jenis", sampah.getJenisSampah());
        barisStruk("Berat", String.format("%.2f kg", sampah.getBerat()));
        barisStruk("Penyetor", sampah.getPenyetor().getNamaPenyetor());
        barisStruk("Petugas", sampah.getPetugas().getNamaPetugas());
        barisStruk("Nilai Setoran", formatRupiah(sampah.hitungNilaiSetoran()));
        barisStruk("Poin Hijau", "+" + sampah.hitungPoinHijau());

        int totalPoin = hitungTotalPoin(sampah.getPenyetor(), sampahDAO.ambilSemua());
        barisStruk("Total Poin", totalPoin + " (" + tentukanLevel(totalPoin) + ")");

        System.out.println(garis);
        System.out.println("Terima kasih telah menyetor sampah!");
    }

    private void tampilkanDetail(Sampah sampah) {

        System.out.println("------------------------------------");

        // POLYMORPHISM: method yang berjalan sesuai jenis objek
        sampah.tampilkanInfo();
        sampah.cetakStatus();
        System.out.println("Nilai Setoran : " + formatRupiah(sampah.hitungNilaiSetoran()));

        // OVERLOADING: versi dengan parameter bonus untuk setoran besar
        if (sampah.getBerat() >= 10) {
            System.out.println("Nilai + Bonus 10% : " + formatRupiah(sampah.hitungNilaiSetoran(10)));
        }

        System.out.println("Poin Hijau    : " + sampah.hitungPoinHijau());
        System.out.println("------------------------------------");
    }

    // CREATE
    public void tambahSampah(Scanner scanner) {

        System.out.println("\n--- TAMBAH DATA SAMPAH ---");

        if (penyetorDAO.ambilSemua().size() == 0) {
            System.out.println("Belum ada data penyetor. Tambahkan lewat menu Data Penyetor.");
            return;
        }

        if (petugasDAO.ambilSemua().size() == 0) {
            System.out.println("Belum ada data petugas. Tambahkan lewat menu Data Petugas.");
            return;
        }

        String jenis = InputCek.cekJenis(scanner);
        double berat = InputCek.cekDesimal(scanner, "Masukkan berat (kg, 0.5 - 100): ", 0.5, 100);
        PenyetorSampah penyetor = pilihPenyetor(scanner);
        PetugasSampah petugas = pilihPetugas(scanner);

        Sampah baru;

        // ID 0 hanya penampung sementara. ID asli dibuat otomatis oleh database.
        if (jenis.equalsIgnoreCase("Organik")) {

            String mudahTerurai = InputCek.cekYaTidak(scanner,
                    "Apakah mudah terurai (Ya/Tidak): ");
            int hari = InputCek.cekAngka(scanner,
                    "Masukkan estimasi hari kompos (1 - 365): ", 1, 365);

            baru = new SampahOrganik(0, berat, penyetor, petugas, mudahTerurai, hari);

        } else {

            String dapatDidaurUlang = InputCek.cekYaTidak(scanner,
                    "Apakah dapat didaur ulang (Ya/Tidak): ");
            String bahan = InputCek.cekBahan(scanner);

            baru = new SampahAnorganik(0, berat, penyetor, petugas, dapatDidaurUlang, bahan);
        }

        int idBaru = sampahDAO.tambah(baru);

        if (idBaru > 0) {

            System.out.println("Data berhasil disimpan ke database dengan ID " + idBaru);

            Sampah tersimpan = sampahDAO.cariById(idBaru);

            if (tersimpan != null) {
                cetakStruk(tersimpan);
            }

        } else {
            System.out.println("Data gagal disimpan.");
        }
    }

    // READ
    public void tampilkanSampah() {

        System.out.println("\n--- DAFTAR DATA SAMPAH ---");

        ArrayList<Sampah> daftar = sampahDAO.ambilSemua();

        if (daftar.size() == 0) {
            System.out.println("Data masih kosong!");
            return;
        }

        String garis = garisTabel();

        System.out.println(garis);
        System.out.printf("| %-4s | %-9s | %-9s | %-14s | %-14s | %11s | %5s |%n",
                "ID", "Jenis", "Berat", "Penyetor", "Petugas", "Nilai (Rp)", "Poin");
        System.out.println(garis);

        for (Sampah sampah : daftar) {

            // POLYMORPHISM: hitungNilaiSetoran() dan hitungPoinHijau() sesuai jenis objek
            System.out.printf("| %-4d | %-9s | %6.2f kg | %-14.14s | %-14.14s | %,11.0f | %5d |%n",
                    sampah.getIdSampah(),
                    sampah.getJenisSampah(),
                    sampah.getBerat(),
                    sampah.getPenyetor().getNamaPenyetor(),
                    sampah.getPetugas().getNamaPetugas(),
                    sampah.hitungNilaiSetoran(),
                    sampah.hitungPoinHijau());
        }

        System.out.println(garis);
        System.out.println("Total data: " + daftar.size());
        System.out.println("Gunakan menu 3 untuk melihat detail setiap data.");
    }

    // UPDATE
    public void updateSampah(Scanner scanner) {

        System.out.println("\n--- UPDATE DATA SAMPAH ---");

        int id = InputCek.cekAngka(scanner, "Masukkan ID sampah yang akan diupdate: ", 1, 99999);
        Sampah sampah = cariSampah(id);

        if (sampah == null) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        System.out.println("Data saat ini:");
        tampilkanDetail(sampah);

        double berat = InputCek.cekDesimal(scanner, "Masukkan berat baru (kg, 0.5 - 100): ", 0.5, 100);
        sampah.setBerat(berat);

        if (sampah instanceof SampahOrganik) {

            SampahOrganik organik = (SampahOrganik) sampah;

            String mudahTerurai = InputCek.cekYaTidak(scanner,
                    "Apakah mudah terurai (Ya/Tidak): ");
            int hari = InputCek.cekAngka(scanner,
                    "Masukkan estimasi hari kompos (1 - 365): ", 1, 365);

            organik.setMudahTerurai(mudahTerurai);
            organik.setEstimasiHariKompos(hari);

        } else if (sampah instanceof SampahAnorganik) {

            SampahAnorganik anorganik = (SampahAnorganik) sampah;

            String dapatDidaurUlang = InputCek.cekYaTidak(scanner,
                    "Apakah dapat didaur ulang (Ya/Tidak): ");
            String bahan = InputCek.cekBahan(scanner);

            anorganik.setDapatDidaurUlang(dapatDidaurUlang);
            anorganik.setJenisBahan(bahan);
        }

        if (sampahDAO.ubah(sampah)) {
            System.out.println("Data berhasil diupdate di database!");
        } else {
            System.out.println("Data gagal diupdate.");
        }
    }

    // DELETE
    public void hapusSampah(Scanner scanner) {

        System.out.println("\n--- HAPUS DATA SAMPAH ---");

        int id = InputCek.cekAngka(scanner, "Masukkan ID sampah yang akan dihapus: ", 1, 99999);
        Sampah sampah = cariSampah(id);

        if (sampah == null) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        tampilkanDetail(sampah);

        String yakin = InputCek.cekYaTidak(scanner, "Yakin ingin menghapus data ini (Ya/Tidak): ");

        if (yakin.equalsIgnoreCase("Ya")) {

            if (sampahDAO.hapus(id)) {
                System.out.println("Data berhasil dihapus dari database!");
            } else {
                System.out.println("Data gagal dihapus.");
            }

        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    // OVERLOADING 1: cari satu data berdasarkan ID
    public Sampah cariSampah(int idSampah) {
        return sampahDAO.cariById(idSampah);
    }

    // OVERLOADING 2: cari banyak data berdasarkan rentang berat
    public void cariSampah(double beratMin, double beratMax) {

        ArrayList<Sampah> hasil = sampahDAO.cariByRentangBerat(beratMin, beratMax);

        if (hasil.size() == 0) {
            System.out.println("Tidak ada data pada rentang berat tersebut!");
            return;
        }

        for (Sampah sampah : hasil) {
            tampilkanDetail(sampah);
        }

        System.out.println("Ditemukan " + hasil.size() + " data.");
    }

    public void cariDataSampah(Scanner scanner) {

        System.out.println("\n--- CARI / LIHAT DETAIL SAMPAH ---");

        System.out.println("1. Cari berdasarkan ID");
        System.out.println("2. Cari berdasarkan rentang berat");

        int pilihan = InputCek.cekAngka(scanner, "Pilih cara pencarian: ", 1, 2);

        if (pilihan == 1) {

            int id = InputCek.cekAngka(scanner, "Masukkan ID sampah: ", 1, 99999);
            Sampah sampah = cariSampah(id);

            if (sampah == null) {
                System.out.println("Data tidak ditemukan!");
            } else {
                tampilkanDetail(sampah);
            }

        } else {

            double min = InputCek.cekDesimal(scanner, "Berat minimal (kg): ", 0.5, 100);
            double max = InputCek.cekDesimal(scanner, "Berat maksimal (kg): ", min, 100);

            cariSampah(min, max);
        }
    }

    // FITUR yang memanfaatkan INTERFACE LayananDaurUlang
    public void layananPengolahan(Scanner scanner) {

        System.out.println("\n--- LAYANAN PENGOLAHAN SAMPAH ---");

        int id = InputCek.cekAngka(scanner, "Masukkan ID sampah yang akan diolah: ", 1, 99999);
        Sampah sampah = cariSampah(id);

        if (sampah == null) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        if (sampah instanceof LayananDaurUlang) {

            LayananDaurUlang layanan = (LayananDaurUlang) sampah;

            sampah.tampilkanInfo();

            System.out.println("Biaya Pengolahan : " + formatRupiah(layanan.hitungBiayaPengolahan()));
            layanan.tampilkanPetunjuk();

        } else {
            System.out.println("Jenis sampah ini belum memiliki layanan pengolahan.");
        }
    }

    public void tampilkanPeringkat() {

        System.out.println("\n--- PERINGKAT PENYETOR ---");

        ArrayList<PenyetorSampah> urutan = penyetorDAO.ambilSemua();
        ArrayList<Sampah> semuaSampah = sampahDAO.ambilSemua();

        if (urutan.size() == 0) {
            System.out.println("Data penyetor masih kosong!");
            return;
        }

        // Urutkan dari poin terbesar ke terkecil (bubble sort sederhana)
        for (int i = 0; i < urutan.size() - 1; i++) {
            for (int j = 0; j < urutan.size() - 1 - i; j++) {

                int poinA = hitungTotalPoin(urutan.get(j), semuaSampah);
                int poinB = hitungTotalPoin(urutan.get(j + 1), semuaSampah);

                if (poinA < poinB) {
                    PenyetorSampah dipindah = urutan.remove(j);
                    urutan.add(j + 1, dipindah);
                }
            }
        }

        String garis = "+" + ulang("-", 6)
                + "+" + ulang("-", 22)
                + "+" + ulang("-", 8)
                + "+" + ulang("-", 10) + "+";

        System.out.println(garis);
        System.out.printf("| %-4s | %-20s | %6s | %-8s |%n", "No", "Nama Penyetor", "Poin", "Level");
        System.out.println(garis);

        for (int i = 0; i < urutan.size(); i++) {

            int poin = hitungTotalPoin(urutan.get(i), semuaSampah);

            System.out.printf("| %-4d | %-20.20s | %6d | %-8s |%n",
                    i + 1, urutan.get(i).getNamaPenyetor(), poin, tentukanLevel(poin));
        }

        System.out.println(garis);
        System.out.println("Level: Perunggu (< 50 poin), Perak (50 - 99 poin), Emas (100 poin ke atas).");
    }

    public void tampilkanRingkasan() {

        System.out.println("\n--- RINGKASAN SETORAN ---");

        ArrayList<Sampah> daftar = sampahDAO.ambilSemua();

        if (daftar.size() == 0) {
            System.out.println("Data masih kosong!");
            return;
        }

        int jumlahOrganik = 0;
        int jumlahAnorganik = 0;
        double beratOrganik = 0;
        double beratAnorganik = 0;
        double totalNilai = 0;
        int totalPoin = 0;

        for (Sampah sampah : daftar) {

            if (sampah.getJenisSampah().equalsIgnoreCase("Organik")) {
                jumlahOrganik++;
                beratOrganik += sampah.getBerat();
            } else {
                jumlahAnorganik++;
                beratAnorganik += sampah.getBerat();
            }

            totalNilai += sampah.hitungNilaiSetoran();
            totalPoin += sampah.hitungPoinHijau();
        }

        double totalBerat = beratOrganik + beratAnorganik;
        int persenOrganik = (int) (beratOrganik / totalBerat * 100);
        int persenAnorganik = 100 - persenOrganik;
        int blokOrganik = persenOrganik / 5;
        int blokAnorganik = persenAnorganik / 5;

        System.out.printf("Sampah Organik   : %d data, total %.2f kg%n", jumlahOrganik, beratOrganik);
        System.out.printf("Sampah Anorganik : %d data, total %.2f kg%n", jumlahAnorganik, beratAnorganik);
        System.out.println("Total Nilai Setoran : " + formatRupiah(totalNilai));
        System.out.println("Total Poin Hijau    : " + totalPoin);

        System.out.println("\nKomposisi berat sampah:");
        System.out.println("Organik   : " + ulang("#", blokOrganik)
                + ulang("-", 20 - blokOrganik) + " " + persenOrganik + "%");
        System.out.println("Anorganik : " + ulang("#", blokAnorganik)
                + ulang("-", 20 - blokAnorganik) + " " + persenAnorganik + "%");
    }
}