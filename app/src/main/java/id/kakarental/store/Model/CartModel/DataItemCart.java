package id.kakarental.store.Model.CartModel;

import com.google.gson.annotations.SerializedName;


public class DataItemCart {

    @SerializedName("nama_produk")
    private String namaProduk;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("berat")
    private String berat;

    @SerializedName("tgl_akhir")
    private String tglAkhir;

    @SerializedName("id_produk")
    private String idProduk;

    @SerializedName("tgl_sewa")
    private String tglSewa;

    @SerializedName("session")
    private String session;

    @SerializedName("harga_sewa")
    private String hargaSewa;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("id_penjualan_detail")
    private String idPenjualanDetail;

    @SerializedName("jenis_sewa")
    private String jenisSewa;

    @SerializedName("harga_jual")
    private String hargaJual;

    @SerializedName("durasi")
    private String durasi;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("waktu_order")
    private String waktuOrder;

    @SerializedName("total")
    private String total;

    public String getTotal() {
        return total;
    }

    public String getTglAkhir() {
        return tglAkhir;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public String getTglSewa() {
        return tglSewa;
    }

    public String getSession() {
        return session;
    }

    public String getHargaSewa() {
        return hargaSewa;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getGambar() {
        return gambar;
    }

    public String getBerat() {
        return berat;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdPenjualanDetail() {
        return idPenjualanDetail;
    }

    public String getJenisSewa() {
        return jenisSewa;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getTipe() {
        return tipe;
    }

    public String getWaktuOrder() {
        return waktuOrder;
    }
}