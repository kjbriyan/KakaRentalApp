package id.kakarental.store.Model.raja_ongkir;

import com.google.gson.annotations.SerializedName;


public class Status {

    @SerializedName("code")
    private int code;

    @SerializedName("description")
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}