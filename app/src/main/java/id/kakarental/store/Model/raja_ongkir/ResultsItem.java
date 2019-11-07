package id.kakarental.store.Model.raja_ongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResultsItem {

    @SerializedName("costs")
    private List<CostsItem> costs;

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    public List<CostsItem> getCosts() {
        return costs;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}