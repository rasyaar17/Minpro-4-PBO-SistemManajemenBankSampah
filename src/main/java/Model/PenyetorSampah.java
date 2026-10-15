package Model;

public class PenyetorSampah {

    private final int idPenyetor;
    private String namaPenyetor;
    private String alamat;

    public PenyetorSampah(int idPenyetor, String namaPenyetor, String alamat) {
        this.idPenyetor = idPenyetor;
        setNamaPenyetor(namaPenyetor);
        setAlamat(alamat);
    }

    public int getIdPenyetor() {
        return idPenyetor;
    }

    public String getNamaPenyetor() {
        return namaPenyetor;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setNamaPenyetor(String namaPenyetor) {
        if (namaPenyetor.trim().equals("")) {
            System.out.println("Nama penyetor tidak boleh kosong.");
        } else {
            this.namaPenyetor = namaPenyetor;
        }
    }

    public void setAlamat(String alamat) {
        if (alamat.trim().equals("")) {
            System.out.println("Alamat tidak boleh kosong.");
        } else {
            this.alamat = alamat;
        }
    }

    public void tampilkanInfo() {
        System.out.println("ID Penyetor : " + idPenyetor);
        System.out.println("Nama        : " + namaPenyetor);
        System.out.println("Alamat      : " + alamat);
    }
}