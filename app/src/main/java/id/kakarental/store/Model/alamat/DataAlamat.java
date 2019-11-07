package id.kakarental.store.Model.alamat;

import com.google.gson.annotations.SerializedName;

public class DataAlamat {

    @SerializedName("nama_alamat")
    private String namaAlamat;

    @SerializedName("id_alamat")
    private String idAlamat;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("kota_id")
    private String kotaId;

    @SerializedName("alamat_lengkap")
    private String alamatLengkap;

    @SerializedName("atas_nama")
    private String atasNama;

    @SerializedName("nama_kota")
    private String namaKota;

    @SerializedName("nama_provinsi")
    private String namaProvinsi;

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public String getNamaAlamat() {
        return namaAlamat;
    }

    public String getIdAlamat() {
        return idAlamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getUserId() {
        return userId;
    }

    public String getKotaId() {
        return kotaId;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public String getAtasNama() {
        return atasNama;
    }
}