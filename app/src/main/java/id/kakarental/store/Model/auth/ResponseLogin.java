package id.kakarental.store.Model.auth;


import com.google.gson.annotations.SerializedName;


public class ResponseLogin {

    @SerializedName("data")
    private DataLogin data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public DataLogin getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}