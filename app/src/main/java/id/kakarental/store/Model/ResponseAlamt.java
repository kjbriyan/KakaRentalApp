package id.kakarental.store.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseAlamt{

	@SerializedName("data")
	private List<DataItemAlamat> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DataItemAlamat> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}