package id.kakarental.store.Model.auth;


import com.google.gson.annotations.SerializedName;


public class DataLogin {

    @SerializedName("foto_ktp")
    private String fotoktp;

    @SerializedName("id_konsumen")
    private String idKonsumen;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("nama_lengkap")
    private String namaLengkap;

    @SerializedName("tempat_lahir")
    private String tempatLahir;

    @SerializedName("tanggal_lahir")
    private String tanggalLahir;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("alamat_lengkap")
    private String alamatLengkap;

    @SerializedName("kota_id")
    private String kotaId;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("email")
    private String email;

    public DataLogin(String idKonsumen,String fotoktp, String username, String password, String namaLengkap, String tempatLahir, String tanggalLahir, String noHp, String alamatLengkap, String kotaId, String jenisKelamin, String email) {
        this.idKonsumen = idKonsumen;
        this.fotoktp = fotoktp;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.noHp = noHp;
        this.alamatLengkap = alamatLengkap;
        this.kotaId = kotaId;
        this.jenisKelamin = jenisKelamin;
        this.email = email;
    }

    public String getFotoktp() {
        return fotoktp;
    }

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }

    public String getIdKonsumen() {
        return idKonsumen;
    }

    public void setIdKonsumen(String idKonsumen) {
        this.idKonsumen = idKonsumen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}