package id.kakarental.store.Model.banner;

import com.google.gson.annotations.SerializedName;


public class BannerItem {

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("id_slide")
    private String idSlide;

    @SerializedName("waktu")
    private String waktu;

    @SerializedName("gambar")
    private String gambar;

    public String getKeterangan() {
        return keterangan;
    }

    public String getIdSlide() {
        return idSlide;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getGambar() {
        return gambar;
    }
}