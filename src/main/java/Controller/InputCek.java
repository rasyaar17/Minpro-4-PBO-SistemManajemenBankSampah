package Controller;

import java.util.Scanner;

public class InputCek {

    public static int cekAngka(Scanner scanner, String pesan, int min, int max) {

        int angka = 0;
        boolean valid = false;

        while (!valid) {

            System.out.print(pesan);
            String input = scanner.nextLine();

            try {
                angka = Integer.parseInt(input.trim());

                if (angka < min || angka > max) {
                    System.out.println("Angka harus antara " + min + " sampai " + max + "!");
                } else {
                    valid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }

        return angka;
    }

    public static double cekDesimal(Scanner scanner, String pesan, double min, double max) {

        double angka = 0;
        boolean valid = false;

        while (!valid) {

            System.out.print(pesan);
            String input = scanner.nextLine();

            try {
                angka = Double.parseDouble(input.trim().replace(",", "."));

                if (!(angka >= min && angka <= max)) {
                    System.out.println("Nilai harus antara " + min + " sampai " + max + "!");
                } else if (Math.abs(angka * 100 - Math.round(angka * 100)) > 0.0001) {
                    System.out.println("Maksimal 2 angka di belakang koma!");
                } else {
                    valid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }

        return angka;
    }

    public static int cekPilihan(Scanner scanner, int maksimal) {
        return cekAngka(scanner, "Masukkan pilihan menu: ", 0, maksimal);
    }

    public static String cekJenis(Scanner scanner) {

        String jenis = "";
        boolean valid = false;

        while (!valid) {

            System.out.print("Masukkan jenis sampah (Organik/Anorganik): ");
            jenis = scanner.nextLine().trim();

            if (jenis.equalsIgnoreCase("Organik")) {
                jenis = "Organik";
                valid = true;
            } else if (jenis.equalsIgnoreCase("Anorganik")) {
                jenis = "Anorganik";
                valid = true;
            } else {
                System.out.println("Jenis sampah hanya Organik atau Anorganik!");
            }
        }

        return jenis;
    }

    public static String cekBahan(Scanner scanner) {

        String bahan = "";
        boolean valid = false;

        while (!valid) {

            System.out.print("Masukkan jenis bahan (Plastik/Kertas/Logam/Kaca): ");
            bahan = scanner.nextLine().trim();

            if (bahan.equalsIgnoreCase("Plastik")) {
                bahan = "Plastik";
                valid = true;
            } else if (bahan.equalsIgnoreCase("Kertas")) {
                bahan = "Kertas";
                valid = true;
            } else if (bahan.equalsIgnoreCase("Logam")) {
                bahan = "Logam";
                valid = true;
            } else if (bahan.equalsIgnoreCase("Kaca")) {
                bahan = "Kaca";
                valid = true;
            } else {
                System.out.println("Bahan hanya Plastik, Kertas, Logam, atau Kaca!");
            }
        }

        return bahan;
    }

    public static String cekYaTidak(Scanner scanner, String pesan) {

        String jawaban = "";
        boolean valid = false;

        while (!valid) {

            System.out.print(pesan);
            jawaban = scanner.nextLine().trim();

            if (jawaban.equalsIgnoreCase("Ya")) {
                jawaban = "Ya";
                valid = true;
            } else if (jawaban.equalsIgnoreCase("Tidak")) {
                jawaban = "Tidak";
                valid = true;
            } else {
                System.out.println("Jawaban hanya Ya atau Tidak!");
            }
        }

        return jawaban;
    }

    public static String cekNama(Scanner scanner, String pesan) {

        String nama = "";
        boolean valid = false;

        while (!valid) {

            System.out.print(pesan);
            nama = scanner.nextLine().trim();

            if (nama.length() < 3 || nama.length() > 30) {
                System.out.println("Nama harus 3 sampai 30 karakter!");
            } else {

                boolean hurufSemua = true;

                for (int i = 0; i < nama.length(); i++) {
                    char c = nama.charAt(i);
                    if (!Character.isLetter(c) && c != ' ') {
                        hurufSemua = false;
                    }
                }

                if (!hurufSemua) {
                    System.out.println("Nama hanya boleh berisi huruf dan spasi!");
                } else {
                    valid = true;
                }
            }
        }

        return nama;
    }

    public static String cekTeks(Scanner scanner, String pesan, int min, int max) {

        String teks = "";
        boolean valid = false;

        while (!valid) {

            System.out.print(pesan);
            teks = scanner.nextLine().trim();

            boolean adaHuruf = false;

            for (int i = 0; i < teks.length(); i++) {
                if (Character.isLetter(teks.charAt(i))) {
                    adaHuruf = true;
                }
            }

            if (teks.length() < min || teks.length() > max) {
                System.out.println("Teks harus " + min + " sampai " + max + " karakter!");
            } else if (!adaHuruf) {
                System.out.println("Teks harus mengandung huruf!");
            } else {
                valid = true;
            }
        }

        return teks;
    }
}