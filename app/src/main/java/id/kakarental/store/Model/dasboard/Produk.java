package id.kakarental.store.Model.dasboard;

import com.google.gson.annotations.SerializedName;


public class Produk {

    @SerializedName("id_produk")
    private String idProduk;

    @SerializedName("gambar4")
    private String gambar4;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("gambar3")
    private String gambar3;

    @SerializedName("gambar2")
    private String gambar2;

    @SerializedName("stok")
    private Object stok;

    @SerializedName("harga_reseller")
    private String hargaReseller;

    @SerializedName("harga_beli")
    private String hargaBeli;

    @SerializedName("berat")
    private String berat;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("diskon")
    private String diskon;

    @SerializedName("tgl_akhir")
    private String tglAkhir;

    @SerializedName("merk")
    private String merk;

    @SerializedName("sewa_bulanan")
    private String sewaBulanan;

    @SerializedName("sewa_mingguan")
    private String sewaMingguan;

    @SerializedName("sewa_harian")
    private String sewaHarian;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("produk_seo")
    private String produkSeo;

    @SerializedName("nama_produk")
    private String namaProduk;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("denda")
    private String denda;

    @SerializedName("waktu_input")
    private String waktuInput;

    @SerializedName("id_kategori_produk")
    private String idKategoriProduk;

    @SerializedName("harga_konsumen")
    private String hargaKonsumen;

    @SerializedName("username")
    private String username;

    @SerializedName("status")
    private String status;

    public String getIdProduk() {
        return idProduk;
    }

    public String getGambar4() {
        return gambar4;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getGambar3() {
        return gambar3;
    }

    public String getGambar2() {
        return gambar2;
    }

    public Object getStok() {
        return stok;
    }

    public String getHargaReseller() {
        return hargaReseller;
    }

    public String getHargaBeli() {
        return hargaBeli;
    }

    public String getBerat() {
        return berat;
    }

    public String getTipe() {
        return tipe;
    }

    public String getDiskon() {
        return diskon;
    }

    public String getTglAkhir() {
        return tglAkhir;
    }

    public String getMerk() {
        return merk;
    }

    public String getSewaBulanan() {
        return sewaBulanan;
    }

    public String getSewaMingguan() {
        return sewaMingguan;
    }

    public String getSewaHarian() {
        return sewaHarian;
    }

    public String getGambar() {
        return gambar;
    }

    public String getProdukSeo() {
        return produkSeo;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getDenda() {
        return denda;
    }

    public String getWaktuInput() {
        return waktuInput;
    }

    public String getIdKategoriProduk() {
        return idKategoriProduk;
    }

    public String getHargaKonsumen() {
        return hargaKonsumen;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }
}