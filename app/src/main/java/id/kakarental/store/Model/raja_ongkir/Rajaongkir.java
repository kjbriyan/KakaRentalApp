package id.kakarental.store.Model.raja_ongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Rajaongkir {

    @SerializedName("query")
    private Query query;

    @SerializedName("destination_details")
    private DestinationDetails destinationDetails;

    @SerializedName("origin_details")
    private OriginDetails originDetails;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("status")
    private Status status;

    public Query getQuery() {
        return query;
    }

    public DestinationDetails getDestinationDetails() {
        return destinationDetails;
    }

    public OriginDetails getOriginDetails() {
        return originDetails;
    }

    public List<ResultsItem> getResults() {
        return results;
    }

    public Status getStatus() {
        return status;
    }
}