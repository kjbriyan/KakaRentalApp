package id.kakarental.store.Model.status_transaksi;


import com.google.gson.annotations.SerializedName;


public class DataDetailStatusTransaksi {

    @SerializedName("kode_transaksi")
    private String kodeTransaksi;

    @SerializedName("total_beli")
    private String totalBeli;

    @SerializedName("total_sewa")
    private String totalSewa;

    @SerializedName("ongkir")
    private String ongkir;

    @SerializedName("service")
    private String service;

    @SerializedName("total_berat")
    private String totalBerat;

    @SerializedName("kurir")
    private String kurir;

    @SerializedName("proses")
    private String proses;

    @SerializedName("atas_nama")
    private String atasNama;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("alamat_lengkap")
    private String alamatLengkap;

    @SerializedName("nama_kota")
    private String namaKota;

    @SerializedName("nama_provinsi")
    private String namaProvinsi;


    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public String getTotalBeli() {
        return totalBeli;
    }

    public String getTotalSewa() {
        return totalSewa;
    }

    public String getOngkir() {
        return ongkir;
    }

    public String getService() {
        return service;
    }

    public String getTotalBerat() {
        return totalBerat;
    }

    public String getKurir() {
        return kurir;
    }

    public String getProses() {
        return proses;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }
}