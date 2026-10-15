package Controller;

import DAO.PetugasDAO;
import Model.PetugasSampah;
import java.util.ArrayList;
import java.util.Scanner;

public class PetugasCRUD {

    private PetugasDAO petugasDAO;

    public PetugasCRUD() {
        petugasDAO = new PetugasDAO();
    }

    public void tambahPetugas(Scanner scanner) {

        System.out.println("\n--- TAMBAH DATA PETUGAS ---");

        String nama = "";
        boolean namaValid = false;

        while (!namaValid) {

            nama = InputCek.cekNama(scanner, "Masukkan nama petugas: ");

            if (petugasDAO.namaSudahAda(nama)) {
                System.out.println("Nama petugas sudah terdaftar!");
            } else {
                namaValid = true;
            }
        }

        String jabatan = InputCek.cekTeks(scanner, "Masukkan jabatan petugas: ", 3, 30);

        // ID 0 hanya penampung sementara, ID asli dibuat oleh database
        int idBaru = petugasDAO.tambah(new PetugasSampah(0, nama, jabatan));

        if (idBaru > 0) {
            System.out.println("Petugas berhasil disimpan dengan ID " + idBaru);
        } else {
            System.out.println("Petugas gagal disimpan.");
        }
    }

    public void tampilkanPetugas() {

        System.out.println("\n--- DAFTAR PETUGAS ---");

        ArrayList<PetugasSampah> daftar = petugasDAO.ambilSemua();

        if (daftar.size() == 0) {
            System.out.println("Data masih kosong!");
        } else {

            for (PetugasSampah petugas : daftar) {
                System.out.println("------------------------------------");
                petugas.tampilkanInfo();
            }

            System.out.println("------------------------------------");
        }
    }
}