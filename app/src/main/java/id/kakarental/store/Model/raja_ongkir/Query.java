package id.kakarental.store.Model.raja_ongkir;

import com.google.gson.annotations.SerializedName;


public class Query {

    @SerializedName("courier")
    private String courier;

    @SerializedName("origin")
    private String origin;

    @SerializedName("destination")
    private String destination;

    @SerializedName("weight")
    private int weight;

    @SerializedName("key")
    private String key;

    public String getCourier() {
        return courier;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public String getKey() {
        return key;
    }
}