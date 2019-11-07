package id.kakarental.store.Model.dasboard;


import com.google.gson.annotations.SerializedName;


public class DataIDasboardSewa {

	@SerializedName("id_produk")
	private String idProduk;

	@SerializedName("sewa_bulanan")
	private String sewaBulanan;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("sewa_mingguan")
	private String sewaMingguan;

	@SerializedName("sewa_harian")
	private String sewaHarian;

	@SerializedName("harga_reseller")
	private String hargaReseller;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("id_tipe")
	private String idTipe;

	@SerializedName("produk_seo")
	private String produkSeo;

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("harga_beli")
	private String hargaBeli;

	@SerializedName("berat")
	private String berat;

	@SerializedName("nama_tipe")
	private String namaTipe;

	@SerializedName("waktu_input")
	private String waktuInput;

	@SerializedName("tipe")
	private String tipe;

	@SerializedName("id_kategori_produk")
	private String idKategoriProduk;

	@SerializedName("diskon")
	private String diskon;

	@SerializedName("harga_konsumen")
	private String hargaKonsumen;

	@SerializedName("username")
	private String username;

	@SerializedName("status")
	private String status;

	@SerializedName("gambar2")
	private String gambar2;

	@SerializedName("gambar3")
	private String gambar3;

	@SerializedName("gambar4")
	private String gambar4;

	public DataIDasboardSewa() {
	}

	public DataIDasboardSewa(String idProduk, String sewaBulanan, String keterangan, String sewaMingguan, String sewaHarian, String hargaReseller, String gambar, String idTipe, String produkSeo, String namaProduk, String satuan, String hargaBeli, String berat, String namaTipe, String waktuInput, String tipe, String idKategoriProduk, String diskon, String hargaKonsumen, String username, String status, String gambar2, String gambar3, String gambar4) {
		this.idProduk = idProduk;
		this.sewaBulanan = sewaBulanan;
		this.keterangan = keterangan;
		this.sewaMingguan = sewaMingguan;
		this.sewaHarian = sewaHarian;
		this.hargaReseller = hargaReseller;
		this.gambar = gambar;
		this.idTipe = idTipe;
		this.produkSeo = produkSeo;
		this.namaProduk = namaProduk;
		this.satuan = satuan;
		this.hargaBeli = hargaBeli;
		this.berat = berat;
		this.namaTipe = namaTipe;
		this.waktuInput = waktuInput;
		this.tipe = tipe;
		this.idKategoriProduk = idKategoriProduk;
		this.diskon = diskon;
		this.hargaKonsumen = hargaKonsumen;
		this.username = username;
		this.status = status;
		this.gambar2 = gambar2;
		this.gambar3 = gambar3;
		this.gambar4 = gambar4;
	}

	public String getGambar2() {
		return gambar2;
	}

	public String getGambar3() {
		return gambar3;
	}

	public String getGambar4() {
		return gambar4;
	}

	public String getIdProduk(){
		return idProduk;
	}


	public String getSewaBulanan(){
		return sewaBulanan;
	}

	public String getKeterangan(){
		return keterangan;
	}


	public String getSewaMingguan(){
		return sewaMingguan;
	}

	public String getSewaHarian(){
		return sewaHarian;
	}

	public String getHargaReseller(){
		return hargaReseller;
	}

	public String getGambar(){
		return gambar;
	}

	public String getIdTipe(){
		return idTipe;
	}

	public String getProdukSeo(){
		return produkSeo;
	}

	public String getNamaProduk(){
		return namaProduk;
	}

	public String getSatuan(){
		return satuan;
	}

	public String getHargaBeli(){
		return hargaBeli;
	}

	public String getBerat(){
		return berat;
	}

	public String getNamaTipe(){
		return namaTipe;
	}

	public String getWaktuInput(){
		return waktuInput;
	}

	public String getTipe(){
		return tipe;
	}

	public String getIdKategoriProduk(){
		return idKategoriProduk;
	}

	public String getDiskon(){
		return diskon;
	}

	public String getHargaKonsumen(){
		return hargaKonsumen;
	}

	public String getUsername(){
		return username;
	}

	public String getStatus(){
		return status;
	}

	public void setIdProduk(String idProduk) {
		this.idProduk = idProduk;
	}



	public void setSewaBulanan(String sewaBulanan) {
		this.sewaBulanan = sewaBulanan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}



	public void setSewaMingguan(String sewaMingguan) {
		this.sewaMingguan = sewaMingguan;
	}

	public void setSewaHarian(String sewaHarian) {
		this.sewaHarian = sewaHarian;
	}

	public void setHargaReseller(String hargaReseller) {
		this.hargaReseller = hargaReseller;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	public void setIdTipe(String idTipe) {
		this.idTipe = idTipe;
	}

	public void setProdukSeo(String produkSeo) {
		this.produkSeo = produkSeo;
	}

	public void setNamaProduk(String namaProduk) {
		this.namaProduk = namaProduk;
	}

	public void setSatuan(String satuan) {
		this.satuan = satuan;
	}

	public void setHargaBeli(String hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public void setBerat(String berat) {
		this.berat = berat;
	}

	public void setNamaTipe(String namaTipe) {
		this.namaTipe = namaTipe;
	}

	public void setWaktuInput(String waktuInput) {
		this.waktuInput = waktuInput;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
	}

	public void setIdKategoriProduk(String idKategoriProduk) {
		this.idKategoriProduk = idKategoriProduk;
	}

	public void setDiskon(String diskon) {
		this.diskon = diskon;
	}

	public void setHargaKonsumen(String hargaKonsumen) {
		this.hargaKonsumen = hargaKonsumen;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}