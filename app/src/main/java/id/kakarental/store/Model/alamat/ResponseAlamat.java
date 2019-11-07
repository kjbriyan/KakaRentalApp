package id.kakarental.store.Model.alamat;

import com.google.gson.annotations.SerializedName;


public class ResponseAlamat {

    @SerializedName("data")
    private DataAlamat data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public DataAlamat getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}