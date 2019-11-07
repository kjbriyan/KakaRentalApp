package id.kakarental.store.Model.banner;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseBanner {

    @SerializedName("banner")
    private List<BannerItem> banner;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<BannerItem> getBanner() {
        return banner;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}