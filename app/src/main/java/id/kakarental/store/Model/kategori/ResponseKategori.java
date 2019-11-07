package id.kakarental.store.Model.kategori;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseKategori {

    @SerializedName("sewa")
    private List<SewaItemKategori> sewa;

    @SerializedName("message")
    private String message;

    @SerializedName("beli")
    private List<BeliItemKategori> beli;

    @SerializedName("status")
    private boolean status;

    public List<SewaItemKategori> getSewa() {
        return sewa;
    }

    public String getMessage() {
        return message;
    }

    public List<BeliItemKategori> getBeli() {
        return beli;
    }

    public boolean isStatus() {
        return status;
    }
}