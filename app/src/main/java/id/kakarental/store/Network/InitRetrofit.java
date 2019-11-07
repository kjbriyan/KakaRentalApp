package id.kakarental.store.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {

    private static final String URL = "https://www.kakarental.com/";
    private static final String BASE_URL_IMAGE = URL + "asset/foto_produk/";
    private static final String BASE_URL_KTP = URL + "asset/foto_ktp/";

//    private static final String URL = "http://192.168.1.70";
//    private static final String BASE_URL = URL + "/kakarentalllll/";
//    private static final String BASE_URL_IMAGE =BASE_URL+ "asset/foto_produk/";
//
//    private static final String BASE_URL_KTP = BASE_URL +"asset/foto_ktp/";

//    public static Retrofit initRetrofit() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit;


    public static Retrofit initRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static ApiInterface getInstance() {
        return initRetrofit().create(ApiInterface.class);
    }

    public static String getIMG_URL() {
        String IMAGE = "";
        IMAGE = BASE_URL_IMAGE;
        return IMAGE;
    }

    public static String getKtp() {
        String KTP = "";
        KTP = BASE_URL_KTP;
        return KTP;
    }
}
