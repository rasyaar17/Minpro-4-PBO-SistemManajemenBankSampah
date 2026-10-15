  <h1> SISTEM MANAJEMEN BANK SAMPAH </h1>

<table>
  <tr>
    <td width="150"><b>Nama</b></td>
    <td>: Rasya Aditya Ramadani </td>
  </tr>
  <tr>
    <td><b>NIM</b></td>
    <td>: 2509116082</td>
  </tr>
  <tr>
</table>

## <b>1. Deskripsi Singkat Tentang Program</b>

Sistem Manajemen Bank Sampah adalah aplikasi berbasis Java yang dirancang untuk mengelola pendataan operasional bank sampah secara terstruktur. 
Program ini mencakup pengelolaan data penyetor, petugas, serta pencatatan kategori sampah organik dan anorganik beserta layanan kalkulasi daur ulangnya

## <b>2. Penjelasan Struktur Package</b>
Program ini menerapkan arsitektur Model-View-Controller (MVC) dengan pemisahan package yang rapi untuk membedakan antara pemrosesan data, tampilan antarmuka, dan logika aplikasi

<img width="257" height="299" alt="Cuplikan layar 2026-10-08 210139" src="https://github.com/user-attachments/assets/77633c84-48de-483f-b4af-8f9df7600e37" />

| Package | File | Fungsi |
|---------|------|--------|
| `com.mycompany.minpro3pbobanksampah` | `Minpro3PBOBankSampah.java` | Titik masuk program (main method) |
| `controller` | `SampahCRUD.java` | Mengatur logika CRUD dan interaksi data |
| `Controller` | `PenyetorCRUD.java` | Mengatur logika CRUD untuk data penyetor |
| `Controller` | `PetugasCRUD.java` | Mengatur logika CRUD untuk data petugas |
| `Controller` | `InputCek.java` | Memvalidasi dan mengecek keamanan input user |
| `model` | `Sampah.java` | Parent class untuk data sampah |
| `model` | `SampahOrganik.java` | Model data khusus untuk sampah organik |
| `model` | `SampahAnorganik.java` | Model data khusus untuk sampah anorganik |
| `model` | `PenyetorSampah.java` | Model data untuk penyetor sampah |
| `model` | `PetugasSampah.java` | Model data untuk petugas bank sampah |
| `model` | `LayananDaurUlang.java` | Interface untuk metode layanan daur ulang|
| `view` | `Menu.java` | Menampilkan menu utama dan menerima input user |

## <b>3. Penjelasan Alur Program</b>

Awal mulanya, class Main bakal bikin objek Controller sama View dulu pas aplikasi baru jalan. Di sini, Controller langsung menyediakan data awal (dummy) untuk sampel sampah, jadi program nggak kosong dan datanya bisa langsung diakses tanpa harus diketik satu-satu dari awal

Setelah itu, sistem akan menampilkan antarmuka SISTEM MANAJEMEN BANK SAMPAH dan Menu Utama melalui class Menu (view).

1. Tambah Data Sampah

2. Tampilkan Tabel Sampah

3. Cari/Lihat Detail Sampah

4. Update Data Sampah

5. Hapus Data Sampah

6. Layanan Pengolahan Sampah

7. Data penyetor

8. Data Petugas

9. Peringkat Penyetor

10. Ringkasan Setoran

0. Keluar

### <b>3.1 Tambah Data Sampah</b>

<img width="222" height="446" alt="image" src="https://github.com/user-attachments/assets/96bbdd37-4443-4bee-a3db-083830549adf" />

Proses pada gambar menampilkan alur penambahan data setoran sampah organik. Pengguna menginput jenis dan berat sampah, memilih ID penyetor (Budi Santoso) serta ID petugas (Dewi Lestari), lalu mengisi detail khusus sampah organik berupa kelayakan urai dan estimasi hari kompos. Setelah data berhasil disimpan, sistem secara otomatis mencetak **Struk Setoran Sampah** yang memuat rincian transaksi, nilai rupiah, hingga kalkulasi akumulasi poin penyetor.

### <b>3.2 Tampilkan Tabel Sampah</b>

<img width="461" height="158" alt="image" src="https://github.com/user-attachments/assets/893519f3-b749-476e-9b46-e0de4d2b1584" />

Tampilan pada gambar menunjukkan fitur untuk menampilkan seluruh daftar data sampah yang telah tersimpan di sistem (pilihan menu 2). Data disajikan dalam bentuk tabel rapi yang memuat informasi lengkap, mulai dari ID transaksi, jenis sampah (organik/anorganik), berat, nama penyetor, nama petugas, nilai nominal rupiah, hingga perolehan poin setoran.

### <b>3.3 Cari/Lihat Detail Sampah</b>

<img width="231" height="242" alt="image" src="https://github.com/user-attachments/assets/e88033e1-5c90-4d42-a44f-ede768ded658" />

Pencarian Berdasarkan ID (Gambar 1): Pengguna memasukkan ID sampah spesifik (contoh: ID 2), lalu sistem menampilkan detail lengkap transaksi untuk satu data sampah tersebut secara spesifik

<img width="218" height="410" alt="image" src="https://github.com/user-attachments/assets/417258db-6fef-4bfb-b85a-88933f13b206" />

Pencarian Berdasarkan Rentang Berat (Gambar 2): Pengguna menentukan batas minimal dan maksimal berat (contoh: 2 kg sampai 4 kg), kemudian sistem menyaring dan menampilkan seluruh data sampah yang beratnya masuk dalam rentang tersebut (ID 2 dan ID 3)

### <b>3.4 Update Data Sampah</b>

<img width="282" height="259" alt="image" src="https://github.com/user-attachments/assets/d8a3dbe6-7e05-4670-ba96-611025e439ee" />

Pada gambar menunjukkan fitur untuk memperbarui data sampah yang sudah ada di sistem (pilihan menu 4). Pengguna cukup memasukkan ID sampah yang ingin diubah (contoh: ID 2), lalu sistem akan memunculkan detail data lama. Setelah itu, pengguna dapat menginput data baru berupa berat, kelayakan daur ulang, serta jenis bahan untuk memperbarui informasi sampah tersebut

### <b>3.5 Hapus Data Sampah</b>

<img width="232" height="221" alt="image" src="https://github.com/user-attachments/assets/a32a94c7-4622-4edb-af92-aba4e96b279b" />

Tampilan pada gambar menunjukkan fitur untuk menghapus data sampah dari sistem (pilihan menu 5). Pengguna memasukkan ID sampah yang ingin dihapus (contoh: ID 4), lalu sistem menampilkan rincian data tersebut dan meminta konfirmasi tindakan terlebih dahulu untuk memastikan data tidak terhapus secara tidak sengaja

### <b>3.6 Layanan Pengolahan Sampah</b>

<img width="307" height="223" alt="image" src="https://github.com/user-attachments/assets/c8604b32-a278-4e24-86dd-6205442502c4" />

Gambar diatas menunjukkan fitur Layanan Pengolahan Sampah (pilihan menu 6). Pengguna memasukkan ID sampah yang ingin diolah (contoh: ID 1), lalu sistem menghitung biaya pengolahan secara otomatis sekaligus menampilkan petunjuk teknis pemrosesannya (seperti langkah pembuatan kompos untuk sampah organik)

### <b>3.7 Data Penyetor</b>

<img width="208" height="223" alt="Cuplikan layar 2026-10-08 215320" src="https://github.com/user-attachments/assets/65551662-b73b-4e05-b313-ca421d44f4a7" />

Fitur **Tambah Penyetor** digunakan untuk mendaftarkan nasabah atau penyetor baru ke dalam sistem bank sampah. Saat memilih opsi ini, pengguna cukup menginputkan identitas penyetor seperti ID penyetor (contoh: `PYT-003`), nama lengkap, dan alamat tempat tinggal. Setelah data disimpan, penyetor baru tersebut otomatis terdaftar di database dan bisa dipilih saat melakukan transaksi setoran sampah.

### <b>3.8 Data Petugas</b>

<img width="271" height="244" alt="Cuplikan layar 2026-10-08 215555" src="https://github.com/user-attachments/assets/41d6fcff-1d76-4e30-ba73-cd3e0c7778a9" />

Fitur **Tambah Petugas** digunakan untuk mendaftarkan staf atau pengelola baru ke dalam sistem bank sampah. Saat memilih opsi ini, pengguna menginput informasi identitas petugas seperti ID petugas (contoh: `PTG-003`), nama lengkap, jabatan, hingga deskripsi tugasnya. Setelah tersimpan, data petugas baru tersebut resmi terdaftar dan bisa dipilih untuk menangani pencatatan transaksi setoran sampah.

### <b>3.9 Peringkat Penyetor</b>

<img width="405" height="137" alt="image" src="https://github.com/user-attachments/assets/59e22132-37ed-4417-94c9-ec677e792b30" />

Tampilan pada gambar menunjukkan fitur **Peringkat Penyetor** (pilihan menu 9). Fitur ini menampilkan papan peringkat (*leaderboard*) nasabah berdasarkan total poin yang dikumpulkan, lengkap dengan tingkatan levelnya seperti Perunggu (< 50 poin), Perak (50 – 99 poin), dan Emas (>= 100 poin).

### <b>3.10 Ringkasan Setoran</b>

<img width="220" height="149" alt="image" src="https://github.com/user-attachments/assets/a99cac6d-ece1-4902-a35d-23529c00a63b" />

Tampilan pada gambar menunjukkan fitur **Ringkasan Setoran** (pilihan menu 10). Fitur ini menyajikan rekapitulasi statistik transaksi, mulai dari jumlah data dan total berat sampah organik/anorganik, akumulasi nilai rupiah, hingga total poin. Selain itu, terdapat pula grafik bar berbasis teks yang menampilkan persentase komposisi perbandingan berat antara sampah organik dan anorganik.

## <b>4. Penjelasan Penerapan Encapsulation dan Inheritance</b>

<img width="685" height="454" alt="image" src="https://github.com/user-attachments/assets/e072d906-41d2-4bca-ad74-0a43198f3f45" />

Program menerapkan encapsulation dengan membuat atribut pada setiap class model menggunakan access modifier private, Contohnya pada class PetugasSampah, atribut idPetugas, namaPetugas, dan jabatan tidak dapat diakses secara langsung dari class lain.

Program menerapkan inheritance pada pengelolaan data sampah. Class Sampah digunakan sebagai superclass dan memiliki dua subclass, yaitu:

    Sampah Organik & Sampah Anorganik

Class SampahOrganik dan SampahAnorganik menggunakan keyword extends Sampah, sehingga keduanya mewarisi atribut dan method yang terdapat pada superclass Sampah

<img width="446" height="86" alt="image" src="https://github.com/user-attachments/assets/8486b60e-9da5-4bf6-94a4-1873e17f6273" />

<img width="461" height="86" alt="image" src="https://github.com/user-attachments/assets/c3c71651-5c92-4a47-98a2-ffe237827a67" />

Kedua gambar tersebut menunjukkan penerapan **Inheritance** dan **Interface** pada kelas `SampahAnorganik.java` dan `SampahOrganik.java`. Kedua kelas anak (*subclass*) ini sama-sama menginduk ke kelas `Sampah` menggunakan kata kunci `extends`, serta menerapkan antarmuka `LayananDaurUlang` dengan kata kunci `implements` untuk menangani fitur pengolahan. Bedanya terletak pada atribut khusus yang dimiliki masing-masing kelas: `SampahAnorganik` menyimpan data `dapatDidaurUlang` dan `jenisBahan`, sedangkan `SampahOrganik` menyimpan data `mudahTerurai` dan `estimasiHariKompos`.

## <b>5. Penjelasan Penerapan Polymorphism dan Abstraction</b>

<img width="467" height="91" alt="Cuplikan layar 2026-09-24 210318" src="https://github.com/user-attachments/assets/f27ffe20-90b4-48d1-aaae-f297befd2cbe" />

<img width="414" height="101" alt="Cuplikan layar 2026-09-24 210335" src="https://github.com/user-attachments/assets/2e86d1fe-1945-40d6-a12d-f03c591a6f73" />

Polymorphism pada program ini diterapkan melalui mekanisme Method Overriding

Superclass Sampah menyediakan method dasar tampilkanInfo() untuk mencetak data umum sampah. Subclass SampahOrganik dan SampahAnorganik kemudian melakukan override (penimpaan) terhadap method tersebut menggunakan anotasi @Override

Saat method tampilkanInfo() dipanggil dari objek subclass, program secara dinamis mengeksekusi method versi subclass masing-masing—menampilkan detail khusus seperti mudahTerurai atau dapatDidaurUlang—tanpa merusak struktur data superclass Sampah

<img width="399" height="140" alt="image" src="https://github.com/user-attachments/assets/3ca15206-93ac-40c6-9817-1fd62a651898" />

Penerapan Abstraction pada program ini wujudkan melalui penggunaan abstract class Sampah dan interface LayananDaur Ulang. Pada kelas induk Sampah, konsep ini diterapkan melalui deklarasi abstract method seperti tampilkanInfo(), hitungNilaiSetoran(), dan hitungPoinHijau() yang sengaja dibuat tanpa isi kode agar setiap kelas turunan wajib menentukan logikanya sendiri. Sementara itu, interface LayananDaur Ulang digunakan sebagai cetak biru untuk mendefinisikan metode hitungBiayaPengolahan() dan tampilkanPetunjuk() yang diimplementasikan oleh kelas SampahOrganik dan SampahAnorganik

## <b>6. Penerapan Interface (Nilai Tambah)</b>

<img width="275" height="112" alt="image" src="https://github.com/user-attachments/assets/eee21e5f-cf05-4aee-ba80-f2eb0461ceb4" />

Kode program tersebut menerapkan konsep **Abstraction** melalui pembuatan sebuah *interface* bernama `LayananDaurUlang` di dalam paket `Model`. Antarmuka ini bertindak sebagai kontrak atau kerangka kerja yang mendefinisikan dua metode abstrak, yaitu `hitungBiayaPengolahan()` yang mengembalikan nilai bertipe `double` dan `tampilkanPetunjuk()` bertipe `void`. Metode-metode ini dibuat tanpa memiliki isi (*body code*), sehingga setiap kelas yang mengimplementasikannya—seperti `SampahOrganik` dan `SampahAnorganik`—diwajibkan untuk menyediakan logika perhitungan biaya serta petunjuk teknis pengolahan yang spesifik sesuai dengan jenis sampahnya masing-masing.

## <b>7. Penerapan JDBC</b>

<img width="583" height="377" alt="image" src="https://github.com/user-attachments/assets/792b0579-4e7d-4d1f-bfa2-ba5df86507c7" />

Dalam pengerjaan proyek mini riset atau tugas pemrograman berorientasi objek ini, penerapan **JDBC (Java Database Connectivity)** yang saya lakukan dimulai dengan menghubungkan program Java ke database MySQL—dalam hal ini menggunakan Laragon. Supaya project-nya bisa mengenali driver MySQL, saya menambahkan dependensi Maven seperti `mysql-connector-j` ke dalam file konfigurasi `pom.xml`. Dari situ, program Java jadi bisa menjembatani komunikasi untuk melakukan operasi CRUD ke database `bank_sampah` yang sudah dibuat, sehingga data dari form aplikasi bisa langsung tersimpan otomatis ke tabel MySQL secara mulus

## <b>8. Dependency MySQL Connector</b>

<img width="365" height="100" alt="image" src="https://github.com/user-attachments/assets/35ad283e-53e5-4221-91af-cbe12f4afcfd" />

Dalam konfigurasi file pom.xml tersebut, saya menambahkan dependensi mysql-connector-j versi 8.4.0 yang berfungsi sebagai jembatan penghubung atau driver resmi agar aplikasi berbasis Java yang saya buat bisa terkoneksi dan berkomunikasi langsung dengan database MySQL

## <b>9. Penerapan DAO</b>

<img width="126" height="62" alt="image" src="https://github.com/user-attachments/assets/9718107e-ab54-422f-aad9-b512220c74da" />

Pada bagian struktur proyek ini, saya menerapkan pola DAO (Data Access Object) yang dibungkus di dalam package Dao. Package ini berisi kelas-kelas seperti `PenyetorDAO.java` , `PetugasDAO.java` , dan `SampahDAO.java` yang bertugas khusus untuk mengani semua kueri SQL dan operasi CRUD (Create, Read, Update, Delete) langsung ke tabel MySQL yang bersangkutan

## <b>10. Penggunaan PreparedStatement</b>

<img width="366" height="132" alt="image" src="https://github.com/user-attachments/assets/0bfea23b-8536-4c81-97f8-2efae4ede13a" />

Pada baris kode PreparedStatement ps = conn.prepareStatement(sql);, saya memanfaatkan objek PreparedStatement untuk mengeksekusi perintah SQL ke database. Penggunaan PreparedStatement ini sangat penting karena perintah SQL akan dicompile dan disiapkan terlebih dahulu oleh database sebelum dijalankan

## <b>11. Struktur Database dan Relasi</b>

<img width="184" height="130" alt="image" src="https://github.com/user-attachments/assets/431acb84-7bd4-429e-9d30-f2d86b47fb0c" />

Database menggunakan tiga tabel utama: penyetor, petugas, dan sampah. Tabel sampah memiliki foreign key id_penyetor yang merujuk ke tabel penyetor, serta foreign key id_petugas yang merujuk ke tabel petugas

Relasi ini sesuai dengan model OOP pada program: satu transaksi penyetoran sampah dicatat oleh satu petugas dan dilakukan oleh satu penyetor. Foreign key juga membantu menjaga konsistensi data, misalnya data penyetor atau petugas tidak dapat dihapus apabila masih memiliki riwayat transaksi pada data sampah

## <b>12. Persistensi Data</b>

Setelah integrasi database, data utama tidak lagi dibuat ulang dari constructor. Data dummy awal dimasukkan melalui file SQL, kemudian seluruh data dibaca dari MySQL saat fitur tampil data atau pencarian dijalankan.

Dengan pendekatan ini, data tetap tersedia setelah aplikasi ditutup dan dijalankan ulang. Hal tersebut membuktikan bahwa aplikasi telah menerapkan persistensi data menggunakan MySQL.
