package id.kakarental.store.Model.dasboard;

import com.google.gson.annotations.SerializedName;


public class ResponseDetailProduk {

    @SerializedName("produk")
    private Produk produk;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public Produk getProduk() {
        return produk;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}