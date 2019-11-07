package id.kakarental.store.Model.registrasi;


import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("provinsi_id")
	private String provinsiId;

	@SerializedName("nama_provinsi")
	private String namaProvinsi;

	public DataItem(String provinsiId, String namaProvinsi) {
		this.provinsiId = provinsiId;
		this.namaProvinsi = namaProvinsi;
	}

	public void setProvinsiId(String provinsiId){
		this.provinsiId = provinsiId;
	}

	public String getProvinsiId(){
		return provinsiId;
	}

	public void setNamaProvinsi(String namaProvinsi){
		this.namaProvinsi = namaProvinsi;
	}

	public String getNamaProvinsi(){
		return namaProvinsi;
	}
}