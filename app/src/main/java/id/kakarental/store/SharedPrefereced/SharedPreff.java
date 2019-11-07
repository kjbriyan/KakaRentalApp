package id.kakarental.store.SharedPrefereced;

public class SharedPreff {
    private static String ID_KONSUMEN="id_konsumen";
    private static String USERNAME="username";
    private static String PASSWORD="password";
    private static String NAMA_LENGKAP="nama_lengkap";
    private static String EMAIL="email";
    private static String JENIS_KELAMIN="jenis_kelamin";
    private static String TANGGAL_LAHIR="tanggal_lahir";
    private static String TEMPAT_LAHIR="tempat_lahir";
    private static String ID_KOTA="id_kota";
    private static String ALAMAT_LENGKAP="alamat_lengkap";
    private static String NO_HP="no_hp";
    private static String NO_WA="no_wa";
    private static String IG="instagram";
    private static String IP="192.168.1.1";
    private static String STATUS="status";
    private static String foto_ktp="foto_ktp";

    public static String getFoto_ktp() {
        return foto_ktp;
    }

    public static String getSTATUS() {
        return STATUS;
    }

    public static void setSTATUS(String STATUS) {
        SharedPreff.STATUS = STATUS;
    }

    public static void setIP(String IP) {
        SharedPreff.IP = IP;
    }

    public static String getIP() {
        return IP;
    }

    public static String getIdKota() {
        return ID_KOTA;
    }

    public static void setIdKota(String idKota) {
        ID_KOTA = idKota;
    }

    public static String getIG() {
        return IG;
    }

    public static void setIG(String IG) {
        SharedPreff.IG = IG;
    }

    public static String getNoWa() {
        return NO_WA;
    }

    public static void setNoWa(String noWa) {
        NO_WA = noWa;
    }



    public static String getIdKonsumen() {
        return ID_KONSUMEN;
    }

    public static void setIdKonsumen(String idKonsumen) {
        ID_KONSUMEN = idKonsumen;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        SharedPreff.USERNAME = USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        SharedPreff.PASSWORD = PASSWORD;
    }

    public static String getNamaLengkap() {
        return NAMA_LENGKAP;
    }

    public static void setNamaLengkap(String namaLengkap) {
        NAMA_LENGKAP = namaLengkap;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        SharedPreff.EMAIL = EMAIL;
    }

    public static String getJenisKelamin() {
        return JENIS_KELAMIN;
    }

    public static void setJenisKelamin(String jenisKelamin) {
        JENIS_KELAMIN = jenisKelamin;
    }

    public static String getTanggalLahir() {
        return TANGGAL_LAHIR;
    }

    public static void setTanggalLahir(String tanggalLahir) {
        TANGGAL_LAHIR = tanggalLahir;
    }

    public static String getTempatLahir() {
        return TEMPAT_LAHIR;
    }

    public static void setTempatLahir(String tempatLahir) {
        TEMPAT_LAHIR = tempatLahir;
    }

    public static String getAlamatLengkap() {
        return ALAMAT_LENGKAP;
    }

    public static void setAlamatLengkap(String alamatLengkap) {
        ALAMAT_LENGKAP = alamatLengkap;
    }

    public static String getNoHp() {
        return NO_HP;
    }

    public static void setNoHp(String noHp) {
        NO_HP = noHp;
    }
}
