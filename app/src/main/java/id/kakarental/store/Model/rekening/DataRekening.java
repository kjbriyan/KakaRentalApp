package id.kakarental.store.Model.rekening;

import com.google.gson.annotations.SerializedName;


public class DataRekening {

    @SerializedName("id_rekening")
    private String idRekening;

    @SerializedName("no_rekening")
    private String noRekening;

    @SerializedName("pemilik_rekening")
    private String pemilikRekening;

    @SerializedName("nama_bank")
    private String namaBank;

    public String getIdRekening() {
        return idRekening;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public String getPemilikRekening() {
        return pemilikRekening;
    }

    public String getNamaBank() {
        return namaBank;
    }
}