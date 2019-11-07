package id.kakarental.store.Model.registrasi.Kota;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseKota{

	@SerializedName("data")
	private List<DataItemKota> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemKota> data){
		this.data = data;
	}

	public List<DataItemKota> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}