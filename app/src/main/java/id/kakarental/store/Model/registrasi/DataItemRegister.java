package id.kakarental.store.Model.registrasi;

import com.google.gson.annotations.SerializedName;


public class DataItemRegister {

	@SerializedName("nama_anak")
	private String namaAnak;

	@SerializedName("bb")
	private String bb;

	@SerializedName("umur")
	private String umur;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("jk_anak")
	private String jkAnak;

	@SerializedName("facebook")
	private String facebook;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("no_wa")
	private String noWa;

	@SerializedName("instagram")
	private String instagram;

	@SerializedName("alamat_lengkap")
	private String alamatLengkap;

	@SerializedName("password")
	private String password;

	@SerializedName("tempat_lahir")
	private String tempatLahir;

	@SerializedName("foto_ktp")
	private String foto;

	@SerializedName("id_konsumen")
	private String idKonsumen;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("kota_id")
	private String kotaId;

	@SerializedName("tanggal_daftar")
	private String tanggalDaftar;

	@SerializedName("email")
	private String email;

	@SerializedName("tanggal_lahir")
	private String tanggalLahir;

	@SerializedName("suami_istri")
	private String suamiIstri;

	@SerializedName("username")
	private String username;

	public DataItemRegister(String namaAnak, String bb, String umur, String noHp, String jkAnak, String facebook, String namaLengkap, String noWa, String instagram, String alamatLengkap, String password, String tempatLahir, String foto, String idKonsumen, String jenisKelamin, String kotaId, String tanggalDaftar, String email, String tanggalLahir, String suamiIstri, String username) {
		this.namaAnak = namaAnak;
		this.bb = bb;
		this.umur = umur;
		this.noHp = noHp;
		this.jkAnak = jkAnak;
		this.facebook = facebook;
		this.namaLengkap = namaLengkap;
		this.noWa = noWa;
		this.instagram = instagram;
		this.alamatLengkap = alamatLengkap;
		this.password = password;
		this.tempatLahir = tempatLahir;
		this.foto = foto;
		this.idKonsumen = idKonsumen;
		this.jenisKelamin = jenisKelamin;
		this.kotaId = kotaId;
		this.tanggalDaftar = tanggalDaftar;
		this.email = email;
		this.tanggalLahir = tanggalLahir;
		this.suamiIstri = suamiIstri;
		this.username = username;
	}

	public String getNamaAnak() {
		return namaAnak;
	}

	public String getBb() {
		return bb;
	}

	public Object getUmur() {
		return umur;
	}

	public String getNoHp() {
		return noHp;
	}

	public String getJkAnak() {
		return jkAnak;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getNamaLengkap() {
		return namaLengkap;
	}

	public String getNoWa() {
		return noWa;
	}

	public String getInstagram() {
		return instagram;
	}

	public String getAlamatLengkap() {
		return alamatLengkap;
	}

	public String getPassword() {
		return password;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public String getFoto() {
		return foto;
	}

	public String getIdKonsumen() {
		return idKonsumen;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public String getKotaId() {
		return kotaId;
	}

	public String getTanggalDaftar() {
		return tanggalDaftar;
	}

	public String getEmail() {
		return email;
	}

	public String getTanggalLahir() {
		return tanggalLahir;
	}

	public Object getSuamiIstri() {
		return suamiIstri;
	}

	public String getUsername() {
		return username;
	}
}