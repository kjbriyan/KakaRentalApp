package id.kakarental.store.Model.User;


import com.google.gson.annotations.SerializedName;


public class ResponseUser {

	@SerializedName("dataItem")
	private DataItem dataItem;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setDataItem(DataItem dataItem){
		this.dataItem = dataItem;
	}

	public DataItem getDataItem(){
		return dataItem;
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