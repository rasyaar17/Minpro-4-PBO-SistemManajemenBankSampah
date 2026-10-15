package View;

import Controller.InputCek;
import Controller.PenyetorCRUD;
import Controller.PetugasCRUD;
import Controller.SampahCRUD;
import Database.Koneksi;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private PenyetorCRUD penyetorCRUD;
    private PetugasCRUD petugasCRUD;
    private SampahCRUD sampahCRUD;

    public Menu() {

        scanner = new Scanner(System.in);
        penyetorCRUD = new PenyetorCRUD();
        petugasCRUD = new PetugasCRUD();
        sampahCRUD = new SampahCRUD();
    }

    private String ulang(String simbol, int jumlah) {

        String hasil = "";

        for (int i = 0; i < jumlah; i++) {
            hasil += simbol;
        }

        return hasil;
    }

    private void baris(String teks) {
        System.out.printf("| %-36s |%n", teks);
    }

    private void tampilkanBanner() {

        String garis = "+" + ulang("=", 40) + "+";

        System.out.println(garis);
        System.out.println("|      SISTEM MANAJEMEN BANK SAMPAH      |");
        System.out.println("|      Pilah, setor, dan jaga bumi       |");
        System.out.println(garis);
    }

    private void tampilkanDaftarMenu() {

        String garis = "+" + ulang("-", 38) + "+";

        System.out.println();
        System.out.println(garis);
        System.out.println("|" + ulang(" ", 14) + "MENU UTAMA" + ulang(" ", 14) + "|");
        System.out.println(garis);
        baris("1. Tambah Data Sampah");
        baris("2. Tampilkan Tabel Sampah");
        baris("3. Cari / Lihat Detail Sampah");
        baris("4. Update Data Sampah");
        baris("5. Hapus Data Sampah");
        baris("6. Layanan Pengolahan Sampah");
        baris("7. Data Penyetor");
        baris("8. Data Petugas");
        baris("9. Peringkat Penyetor");
        baris("10.Ringkasan Setoran");
        baris("0. Keluar");
        System.out.println(garis);
    }

    public void tampilkanMenu() {

        tampilkanBanner();

        if (!Koneksi.tesKoneksi()) {
            System.out.println("Pastikan MySQL sudah aktif dan file bank_sampah.sql sudah di-import.");
            return;
        }

        System.out.println("Koneksi database berhasil.");

        boolean berjalan = true;

        while (berjalan) {

            tampilkanDaftarMenu();

            int pilihan = InputCek.cekPilihan(scanner, 10);

            switch (pilihan) {

                case 1:
                    sampahCRUD.tambahSampah(scanner);
                    break;

                case 2:
                    sampahCRUD.tampilkanSampah();
                    break;

                case 3:
                    sampahCRUD.cariDataSampah(scanner);
                    break;

                case 4:
                    sampahCRUD.updateSampah(scanner);
                    break;

                case 5:
                    sampahCRUD.hapusSampah(scanner);
                    break;

                case 6:
                    sampahCRUD.layananPengolahan(scanner);
                    break;

                case 7:
                    menuPenyetor();
                    break;

                case 8:
                    menuPetugas();
                    break;

                case 9:
                    sampahCRUD.tampilkanPeringkat();
                    break;

                case 10:
                    sampahCRUD.tampilkanRingkasan();
                    break;

                case 0:
                    System.out.println("\nProgram selesai. Terima kasih.");
                    scanner.close();
                    berjalan = false;
                    break;

                default:
                    System.out.println("Pilihan menu tidak tersedia!");
            }
        }
    }

    private void menuPenyetor() {

        boolean kembali = false;

        while (!kembali) {

            System.out.println("\n--- DATA PENYETOR ---");
            System.out.println("1. Tambah Penyetor");
            System.out.println("2. Tampilkan Penyetor");
            System.out.println("0. Kembali");

            int pilihan = InputCek.cekAngka(scanner, "Masukkan pilihan: ", 0, 2);

            switch (pilihan) {

                case 1:
                    penyetorCRUD.tambahPenyetor(scanner);
                    break;

                case 2:
                    penyetorCRUD.tampilkanPenyetor();
                    break;

                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void menuPetugas() {

        boolean kembali = false;

        while (!kembali) {

            System.out.println("\n--- DATA PETUGAS ---");
            System.out.println("1. Tambah Petugas");
            System.out.println("2. Tampilkan Petugas");
            System.out.println("0. Kembali");

            int pilihan = InputCek.cekAngka(scanner, "Masukkan pilihan: ", 0, 2);

            switch (pilihan) {

                case 1:
                    petugasCRUD.tambahPetugas(scanner);
                    break;

                case 2:
                    petugasCRUD.tampilkanPetugas();
                    break;

                case 0:
                    kembali = true;
                    break;
            }
        }
    }
}