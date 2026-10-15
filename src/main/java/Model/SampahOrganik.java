package Model;

public class SampahOrganik extends Sampah implements LayananDaurUlang {

    private String mudahTerurai;
    private int estimasiHariKompos;

    private final double HARGA_PER_KG = 500;
    private final double TARIF_KOMPOS = 10;

    public SampahOrganik(int idSampah, double berat, PenyetorSampah penyetor,
            PetugasSampah petugas, String mudahTerurai, int estimasiHariKompos) {

        super(idSampah, "Organik", berat, penyetor, petugas);
        this.mudahTerurai = mudahTerurai;
        this.estimasiHariKompos = estimasiHariKompos;
    }

    public String getMudahTerurai() {
        return mudahTerurai;
    }

    public int getEstimasiHariKompos() {
        return estimasiHariKompos;
    }

    public void setMudahTerurai(String mudahTerurai) {
        if (mudahTerurai.equalsIgnoreCase("Ya")) {
            this.mudahTerurai = "Ya";
        } else if (mudahTerurai.equalsIgnoreCase("Tidak")) {
            this.mudahTerurai = "Tidak";
        } else {
            System.out.println("Isi hanya Ya atau Tidak!");
        }
    }

    public void setEstimasiHariKompos(int estimasiHariKompos) {
        if (estimasiHariKompos < 1 || estimasiHariKompos > 365) {
            System.out.println("Estimasi hari kompos harus 1 sampai 365!");
        } else {
            this.estimasiHariKompos = estimasiHariKompos;
        }
    }

    // OVERRIDING abstract method dari Sampah
    @Override
    public void tampilkanInfo() {
        tampilkanInfoDasar();
        System.out.println("Mudah Terurai   : " + mudahTerurai);
        System.out.println("Estimasi Kompos : " + estimasiHariKompos + " hari");
    }

    // OVERRIDING abstract method dari Sampah
    @Override
    public double hitungNilaiSetoran() {
        if (mudahTerurai.equalsIgnoreCase("Ya")) {
            return berat * HARGA_PER_KG;
        } else {
            return berat * HARGA_PER_KG / 2;
        }
    }

    // OVERRIDING abstract method dari Sampah
    @Override
    public int hitungPoinHijau() {
        if (mudahTerurai.equalsIgnoreCase("Ya")) {
            return (int) (berat * 5);
        } else {
            return (int) (berat * 2);
        }
    }

    // IMPLEMENTASI INTERFACE LayananDaurUlang
    @Override
    public double hitungBiayaPengolahan() {
        return berat * estimasiHariKompos * TARIF_KOMPOS;
    }

    // IMPLEMENTASI INTERFACE LayananDaurUlang
    @Override
    public void tampilkanPetunjuk() {
        System.out.println("Petunjuk Pembuatan Kompos:");
        System.out.println("1. Pisahkan sampah dari plastik dan benda non-organik.");
        System.out.println("2. Cacah sampah menjadi potongan kecil.");
        System.out.println("3. Campur sampah basah dan sampah kering secara seimbang.");
        System.out.println("4. Simpan di wadah berlubang dan aduk setiap beberapa hari.");
        System.out.println("5. Kompos siap digunakan sekitar " + estimasiHariKompos + " hari lagi.");

        if (!mudahTerurai.equalsIgnoreCase("Ya")) {
            System.out.println("Catatan: sampah ini sulit terurai, cacah lebih halus dan campur dengan sampah yang mudah terurai.");
        }
    }
}