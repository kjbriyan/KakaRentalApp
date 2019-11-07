package id.kakarental.store.Model.status_transaksi;

import com.google.gson.annotations.SerializedName;


public class ListItemDetailStatusTransaksi {

    @SerializedName("nama_produk")
    private String namaProduk;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("berat")
    private String berat;

    @SerializedName("harga_sewa")
    private String hargaSewa;

    @SerializedName("harga_jual")
    private String hargaJual;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("durasi")
    private String durasi;

    @SerializedName("jenis_sewa")
    private String jenisSewa;

    public String getJenisSewa() {
        return jenisSewa;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getBerat() {
        return berat;
    }

    public String getHargaSewa() {
        return hargaSewa;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public String getGambar() {
        return gambar;
    }

    public String getTipe() {
        return tipe;
    }
}