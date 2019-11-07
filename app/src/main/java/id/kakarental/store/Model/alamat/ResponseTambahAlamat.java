package id.kakarental.store.Model.alamat;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseTambahAlamat{

	@SerializedName("data")
	private List<DataItemTambahAlamat> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemTambahAlamat> data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<DataItemTambahAlamat> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}