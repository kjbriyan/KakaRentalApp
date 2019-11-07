package id.kakarental.store.Model.rekening;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseRekening {

    @SerializedName("data")
    private List<DataRekening> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<DataRekening> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}