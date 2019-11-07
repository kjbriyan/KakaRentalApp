package id.kakarental.store.Model.status_transaksi;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseStatusTransaksi {

    @SerializedName("data")
    private List<DataStatusTransaksi> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<DataStatusTransaksi> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}