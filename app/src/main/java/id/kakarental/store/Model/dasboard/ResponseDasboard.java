package id.kakarental.store.Model.dasboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseDasboard {

    @SerializedName("jual")
    private List<DataDasboardJual> jual;

    @SerializedName("sewa")
    private List<DataIDasboardSewa> sewa;

    @SerializedName("message")
    private String message;

    @SerializedName("iklan")
    private List<DataIklan> iklan;

    @SerializedName("status")
    private boolean status;

    public List<DataDasboardJual> getJual() {
        return jual;
    }

    public List<DataIDasboardSewa> getSewa() {
        return sewa;
    }

    public String getMessage() {
        return message;
    }

    public List<DataIklan> getIklan() {
        return iklan;
    }

    public boolean isStatus() {
        return status;
    }
}