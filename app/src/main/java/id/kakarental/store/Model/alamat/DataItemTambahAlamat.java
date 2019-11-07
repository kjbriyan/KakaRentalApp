package id.kakarental.store.Model.alamat;

import com.google.gson.annotations.SerializedName;


public class DataItemTambahAlamat {

	@SerializedName("nama_alamat")
	private String namaAlamat;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("kota_id")
	private String kotaId;

	@SerializedName("alamat_lengkap")
	private String alamatLengkap;

	@SerializedName("atas_nama")
	private String atasNama;

	public String getNamaAlamat(){
		return namaAlamat;
	}

	public String getNoHp(){
		return noHp;
	}

	public String getUserId(){
		return userId;
	}

	public String getKotaId(){
		return kotaId;
	}

	public String getAlamatLengkap(){
		return alamatLengkap;
	}

	public String getAtasNama(){
		return atasNama;
	}
}