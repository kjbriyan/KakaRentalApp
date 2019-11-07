package id.kakarental.store.Model.CartModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseCart {

    @SerializedName("data")
    private List<DataItemCart> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<DataItemCart> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}