package id.kakarental.store.Model.list_sewa;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseListSewa {

    @SerializedName("data")
    private List<DataItemSewa> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<DataItemSewa> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}