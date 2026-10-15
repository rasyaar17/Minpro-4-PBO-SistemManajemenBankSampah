package Model;

public abstract class Sampah {

    private final int idSampah;
    protected String jenisSampah;
    protected double berat;
    private PenyetorSampah penyetor;
    private PetugasSampah petugas;

    public Sampah(int idSampah, String jenisSampah, double berat,
            PenyetorSampah penyetor, PetugasSampah petugas) {

        this.idSampah = idSampah;
        this.jenisSampah = jenisSampah;
        this.berat = berat;
        this.penyetor = penyetor;
        this.petugas = petugas;
    }

    public int getIdSampah() {
        return idSampah;
    }

    public String getJenisSampah() {
        return jenisSampah;
    }

    public double getBerat() {
        return berat;
    }

    public PenyetorSampah getPenyetor() {
        return penyetor;
    }

    public PetugasSampah getPetugas() {
        return petugas;
    }

    public void setBerat(double berat) {
        if (berat < 0.5) {
            System.out.println("Berat minimal 0.5 kg!");
        } else if (berat > 100) {
            System.out.println("Berat maksimal 100 kg!");
        } else {
            this.berat = berat;
        }
    }

    public void setPenyetor(PenyetorSampah penyetor) {
        if (penyetor == null) {
            System.out.println("Penyetor tidak boleh kosong!");
        } else {
            this.penyetor = penyetor;
        }
    }

    public void setPetugas(PetugasSampah petugas) {
        if (petugas == null) {
            System.out.println("Petugas tidak boleh kosong!");
        } else {
            this.petugas = petugas;
        }
    }

    // ABSTRACT METHOD: wajib diisi oleh setiap subclass
    public abstract void tampilkanInfo();

    // ABSTRACT METHOD: cara hitung nilai setoran berbeda tiap jenis sampah
    public abstract double hitungNilaiSetoran();

    // ABSTRACT METHOD: cara hitung poin hijau berbeda tiap jenis sampah
    public abstract int hitungPoinHijau();

    // OVERLOADING: nama sama dengan method hitungNilaiSetoran(), parameter berbeda
    public double hitungNilaiSetoran(double bonusPersen) {
        double nilai = hitungNilaiSetoran();
        return nilai + (nilai * bonusPersen / 100);
    }

    public void tampilkanInfoDasar() {
        System.out.println("ID Sampah    : " + idSampah);
        System.out.println("Jenis Sampah : " + jenisSampah);
        System.out.println("Berat        : " + berat + " kg");
        System.out.println("Penyetor     : " + penyetor.getNamaPenyetor());
        System.out.println("Petugas      : " + petugas.getNamaPetugas());
    }

    public final void cetakStatus() {
        if (berat >= 10) {
            System.out.println("Status       : Setoran besar");
        } else {
            System.out.println("Status       : Setoran biasa");
        }
    }
}