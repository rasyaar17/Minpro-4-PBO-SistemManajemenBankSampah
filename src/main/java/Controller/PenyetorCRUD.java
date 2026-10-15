package Controller;

import DAO.PenyetorDAO;
import Model.PenyetorSampah;
import java.util.ArrayList;
import java.util.Scanner;

public class PenyetorCRUD {

    private PenyetorDAO penyetorDAO;

    public PenyetorCRUD() {
        penyetorDAO = new PenyetorDAO();
    }

    public void tambahPenyetor(Scanner scanner) {

        System.out.println("\n--- TAMBAH DATA PENYETOR ---");

        String nama = "";
        boolean namaValid = false;

        while (!namaValid) {

            nama = InputCek.cekNama(scanner, "Masukkan nama penyetor: ");

            if (penyetorDAO.namaSudahAda(nama)) {
                System.out.println("Nama penyetor sudah terdaftar!");
            } else {
                namaValid = true;
            }
        }

        String alamat = InputCek.cekTeks(scanner, "Masukkan alamat penyetor: ", 3, 50);

        // ID 0 hanya penampung sementara, ID asli dibuat oleh database
        int idBaru = penyetorDAO.tambah(new PenyetorSampah(0, nama, alamat));

        if (idBaru > 0) {
            System.out.println("Penyetor berhasil disimpan dengan ID " + idBaru);
        } else {
            System.out.println("Penyetor gagal disimpan.");
        }
    }

    public void tampilkanPenyetor() {

        System.out.println("\n--- DAFTAR PENYETOR ---");

        ArrayList<PenyetorSampah> daftar = penyetorDAO.ambilSemua();

        if (daftar.size() == 0) {
            System.out.println("Data masih kosong!");
        } else {

            for (PenyetorSampah penyetor : daftar) {
                System.out.println("------------------------------------");
                penyetor.tampilkanInfo();
            }

            System.out.println("------------------------------------");
        }
    }
}