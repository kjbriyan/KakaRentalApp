package id.kakarental.store.Model.list_sewa;

import com.google.gson.annotations.SerializedName;


public class DataItemSewa {

    @SerializedName("tgl_akhir")
    private String tglAkhir;

    @SerializedName("id_produk")
    private String idProduk;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("tgl_sewa")
    private String tglSewa;

    @SerializedName("harga_sewa_baru")
    private String hargaSewaBaru;

    @SerializedName("jenis_sewa_baru")
    private String jenisSewaBaru;

    @SerializedName("harga_sewa")
    private String hargaSewa;

    @SerializedName("id_penjualan_detail")
    private String idPenjualanDetail;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("proses")
    private String proses;

    @SerializedName("id_penjualan")
    private String idPenjualan;

    @SerializedName("nama_produk")
    private String namaProduk;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("proses_perpanjang")
    private String prosesPerpanjang;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("jenis_sewa")
    private String jenisSewa;

    @SerializedName("tgl_perpanjang")
    private String tglPerpanjang;

    @SerializedName("harga_jual")
    private String hargaJual;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("durasi")
    private String durasi;

    @SerializedName("durasi_baru")
    private String durasiBaru;

    @SerializedName("kode_transaksi")
    private String kodeTransaksi;

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public String getTglAkhir() {
        return tglAkhir;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getTglSewa() {
        return tglSewa;
    }

    public String getHargaSewaBaru() {
        return hargaSewaBaru;
    }

    public String getJenisSewaBaru() {
        return jenisSewaBaru;
    }

    public String getHargaSewa() {
        return hargaSewa;
    }

    public String getIdPenjualanDetail() {
        return idPenjualanDetail;
    }

    public String getGambar() {
        return gambar;
    }

    public String getProses() {
        return proses;
    }

    public String getIdPenjualan() {
        return idPenjualan;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getProsesPerpanjang() {
        return prosesPerpanjang;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getJenisSewa() {
        return jenisSewa;
    }

    public String getTglPerpanjang() {
        return tglPerpanjang;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public String getTipe() {
        return tipe;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getDurasiBaru() {
        return durasiBaru;
    }
}