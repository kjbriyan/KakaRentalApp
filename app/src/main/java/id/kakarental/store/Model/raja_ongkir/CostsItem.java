package id.kakarental.store.Model.raja_ongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;




public class CostsItem {

    @SerializedName("cost")
    private List<CostItem> cost;

    @SerializedName("service")
    private String service;

    @SerializedName("description")
    private String description;

    public List<CostItem> getCost() {
        return cost;
    }

    public String getService() {
        return service;
    }

    public String getDescription() {
        return description;
    }

    public void setCost(List<CostItem> cost) {
        this.cost = cost;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}