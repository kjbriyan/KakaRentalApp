package id.kakarental.store.Model.status_transaksi;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseStatusDetailListTransaksi {

    @SerializedName("data")
    private DataDetailStatusTransaksi data;

    @SerializedName("message")
    private String message;

    @SerializedName("list")
    private List<ListItemDetailStatusTransaksi> list;

    @SerializedName("status")
    private boolean status;

    public DataDetailStatusTransaksi getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public List<ListItemDetailStatusTransaksi> getList() {
        return list;
    }

    public boolean isStatus() {
        return status;
    }
}