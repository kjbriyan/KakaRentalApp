package id.kakarental.store.Model.registrasi.Kota;

import com.google.gson.annotations.SerializedName;


public class DataItemKota {

	@SerializedName("kota_id")
	private String kotaId;

	@SerializedName("nama_kota")
	private String namaKota;

	public void setKotaId(String kotaId){
		this.kotaId = kotaId;
	}

	public String getKotaId(){
		return kotaId;
	}

	public void setNamaKota(String namaKota){
		this.namaKota = namaKota;
	}

	public String getNamaKota(){
		return namaKota;
	}
}