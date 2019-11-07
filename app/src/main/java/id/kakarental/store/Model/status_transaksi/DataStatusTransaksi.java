package id.kakarental.store.Model.status_transaksi;

import com.google.gson.annotations.SerializedName;


public class DataStatusTransaksi {

    @SerializedName("id_penjualan")
    private String idPenjualan;

    @SerializedName("kode_transaksi")
    private String kodeTransaksi;

    @SerializedName("konfirmasi_pembayaran")
    private String konfirmasiPembayaran;

    @SerializedName("ongkir")
    private String ongkir;

    @SerializedName("resi")
    private String resi;

    @SerializedName("service")
    private Object service;

    @SerializedName("waktu_transaksi")
    private String waktuTransaksi;

    @SerializedName("total_pembayaran")
    private String totalPembayaran;

    @SerializedName("kurir")
    private String kurir;

    @SerializedName("tipe")
    private Object tipe;

    @SerializedName("proses")
    private String proses;

    @SerializedName("diskon")
    private Object diskon;

    @SerializedName("id_pembeli")
    private String idPembeli;

    @SerializedName("alamat_id")
    private String alamatId;

    public String getKonfirmasiPembayaran() {
        return konfirmasiPembayaran;
    }

    public String getResi() {
        return resi;
    }

    public String getAlamatId() {
        return alamatId;
    }

    public String getIdPenjualan() {
        return idPenjualan;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public String getOngkir() {
        return ongkir;
    }

    public Object getService() {
        return service;
    }

    public String getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public String getTotalPembayaran() {
        return totalPembayaran;
    }

    public String getKurir() {
        return kurir;
    }

    public Object getTipe() {
        return tipe;
    }

    public String getProses() {
        return proses;
    }

    public Object getDiskon() {
        return diskon;
    }

    public String getIdPembeli() {
        return idPembeli;
    }
}