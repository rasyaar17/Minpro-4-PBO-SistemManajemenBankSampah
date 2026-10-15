package Model;

public class PetugasSampah {

    private final int idPetugas;
    private String namaPetugas;
    private String jabatan;

    public PetugasSampah(int idPetugas, String namaPetugas, String jabatan) {
        this.idPetugas = idPetugas;
        setNamaPetugas(namaPetugas);
        setJabatan(jabatan);
    }

    public int getIdPetugas() {
        return idPetugas;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setNamaPetugas(String namaPetugas) {
        if (namaPetugas.trim().equals("")) {
            System.out.println("Nama petugas tidak boleh kosong.");
        } else {
            this.namaPetugas = namaPetugas;
        }
    }

    public void setJabatan(String jabatan) {
        if (jabatan.trim().equals("")) {
            System.out.println("Jabatan tidak boleh kosong.");
        } else {
            this.jabatan = jabatan;
        }
    }

    public void tampilkanInfo() {
        System.out.println("ID Petugas : " + idPetugas);
        System.out.println("Nama       : " + namaPetugas);
        System.out.println("Jabatan    : " + jabatan);
        System.out.println("Tugas      : Menerima dan mencatat setoran sampah.");
    }
}