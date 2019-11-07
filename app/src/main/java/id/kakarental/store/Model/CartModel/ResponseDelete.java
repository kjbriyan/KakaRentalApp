package id.kakarental.store.Model.CartModel;

import com.google.gson.annotations.SerializedName;


public class ResponseDelete {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}