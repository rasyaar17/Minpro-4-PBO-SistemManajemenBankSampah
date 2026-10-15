package Model;

public class SampahAnorganik extends Sampah implements LayananDaurUlang {

    private String dapatDidaurUlang;
    private String jenisBahan;

    private final double TARIF_DAUR_ULANG = 300;

    public SampahAnorganik(int idSampah, double berat, PenyetorSampah penyetor,
            PetugasSampah petugas, String dapatDidaurUlang, String jenisBahan) {

        super(idSampah, "Anorganik", berat, penyetor, petugas);
        this.dapatDidaurUlang = dapatDidaurUlang;
        this.jenisBahan = jenisBahan;
    }

    public String getDapatDidaurUlang() {
        return dapatDidaurUlang;
    }

    public String getJenisBahan() {
        return jenisBahan;
    }

    public void setDapatDidaurUlang(String dapatDidaurUlang) {
        if (dapatDidaurUlang.equalsIgnoreCase("Ya")) {
            this.dapatDidaurUlang = "Ya";
        } else if (dapatDidaurUlang.equalsIgnoreCase("Tidak")) {
            this.dapatDidaurUlang = "Tidak";
        } else {
            System.out.println("Isi hanya Ya atau Tidak!");
        }
    }

    public void setJenisBahan(String jenisBahan) {
        if (jenisBahan.equalsIgnoreCase("Plastik")) {
            this.jenisBahan = "Plastik";
        } else if (jenisBahan.equalsIgnoreCase("Kertas")) {
            this.jenisBahan = "Kertas";
        } else if (jenisBahan.equalsIgnoreCase("Logam")) {
            this.jenisBahan = "Logam";
        } else if (jenisBahan.equalsIgnoreCase("Kaca")) {
            this.jenisBahan = "Kaca";
        } else {
            System.out.println("Jenis bahan harus Plastik, Kertas, Logam, atau Kaca!");
        }
    }

    // OVERRIDING abstract method dari Sampah
    @Override
    public void tampilkanInfo() {
        tampilkanInfoDasar();
        System.out.println("Dapat Didaur Ulang : " + dapatDidaurUlang);
        System.out.println("Jenis Bahan        : " + jenisBahan);
    }

    // OVERRIDING abstract method dari Sampah
    @Override
    public double hitungNilaiSetoran() {
        if (!dapatDidaurUlang.equalsIgnoreCase("Ya")) {
            return 0;
        }

        double hargaPerKg = 0;

        switch (jenisBahan) {
            case "Plastik":
                hargaPerKg = 2000;
                break;
            case "Kertas":
                hargaPerKg = 1500;
                break;
            case "Logam":
                hargaPerKg = 4000;
                break;
            case "Kaca":
                hargaPerKg = 1000;
                break;
            default:
                hargaPerKg = 500;
                break;
        }

        return berat * hargaPerKg;
    }

    // OVERRIDING abstract method dari Sampah
    @Override
    public int hitungPoinHijau() {
        if (!dapatDidaurUlang.equalsIgnoreCase("Ya")) {
            return (int) (berat * 1);
        }
        return (int) (berat * 10);
    }

    // IMPLEMENTASI INTERFACE LayananDaurUlang
    @Override
    public double hitungBiayaPengolahan() {
        if (!dapatDidaurUlang.equalsIgnoreCase("Ya")) {
            return 0;
        }
        return berat * TARIF_DAUR_ULANG;
    }

    // IMPLEMENTASI INTERFACE LayananDaurUlang
    @Override
    public void tampilkanPetunjuk() {
        if (!dapatDidaurUlang.equalsIgnoreCase("Ya")) {
            System.out.println("Sampah ini tidak dapat didaur ulang.");
            System.out.println("Serahkan ke tempat pembuangan akhir yang sesuai.");
            return;
        }

        System.out.println("Petunjuk Daur Ulang " + jenisBahan + ":");

        switch (jenisBahan) {
            case "Plastik":
                System.out.println("1. Bersihkan sampah dari sisa makanan atau cairan.");
                System.out.println("2. Lepaskan tutup dan label bila ada.");
                System.out.println("3. Pipihkan lalu kumpulkan di tempat plastik.");
                break;
            case "Kertas":
                System.out.println("1. Pastikan kertas dalam keadaan kering.");
                System.out.println("2. Lepaskan staples, klip, atau plastik pelapis.");
                System.out.println("3. Ikat dan kumpulkan di tempat kertas.");
                break;
            case "Logam":
                System.out.println("1. Bilas kaleng atau logam dari sisa isi.");
                System.out.println("2. Pipihkan bila memungkinkan agar hemat tempat.");
                System.out.println("3. Kumpulkan di tempat logam.");
                break;
            case "Kaca":
                System.out.println("1. Bilas botol atau kaca dari sisa isi.");
                System.out.println("2. Bungkus pecahan kaca agar aman dibawa.");
                System.out.println("3. Kumpulkan di tempat kaca.");
                break;
            default:
                System.out.println("1. Pisahkan sesuai bahan lalu serahkan ke petugas.");
                break;
        }
    }
}