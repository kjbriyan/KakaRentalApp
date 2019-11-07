package id.kakarental.store.Model.registrasi;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseRegister{

	@SerializedName("data")
	private List<DataItemRegister> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemRegister> data){
		this.data = data;
	}

	public List<DataItemRegister> getData(){
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