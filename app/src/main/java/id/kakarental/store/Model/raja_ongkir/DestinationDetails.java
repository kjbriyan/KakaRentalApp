package id.kakarental.store.Model.raja_ongkir;

import com.google.gson.annotations.SerializedName;


public class DestinationDetails {

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("province")
    private String province;

    @SerializedName("province_id")
    private String provinceId;

    @SerializedName("type")
    private String type;

    @SerializedName("postal_code")
    private String postalCode;

    @SerializedName("city_id")
    private String cityId;

    public String getCityName() {
        return cityName;
    }

    public String getProvince() {
        return province;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public String getType() {
        return type;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCityId() {
        return cityId;
    }
}