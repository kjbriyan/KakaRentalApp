package id.kakarental.store.Model.status_transaksi;

import com.google.gson.annotations.SerializedName;


public class ResponseKonfirmasi {

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