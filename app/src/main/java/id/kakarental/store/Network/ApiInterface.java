package id.kakarental.store.Network;

import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.CartModel.ResponseDelete;
import id.kakarental.store.Model.ResponseAlamt;
import id.kakarental.store.Model.ResponseCheckOut;
import id.kakarental.store.Model.alamat.ResponseAlamat;
import id.kakarental.store.Model.alamat.ResponseTambahAlamat;
import id.kakarental.store.Model.auth.ResponseLogin;
import id.kakarental.store.Model.banner.ResponseBanner;
import id.kakarental.store.Model.dasboard.ResponseDasboard;
import id.kakarental.store.Model.dasboard.ResponseDetailProduk;
import id.kakarental.store.Model.kategori.ResponseKategori;
import id.kakarental.store.Model.list_sewa.ResponseListSewa;
import id.kakarental.store.Model.perpanjang.ResponseKonfirm;
import id.kakarental.store.Model.perpanjang.ResponsePerpanjang;
import id.kakarental.store.Model.raja_ongkir.ResponseRajaOngkir;
import id.kakarental.store.Model.registrasi.Kota.ResponseKota;
import id.kakarental.store.Model.registrasi.ResponseProvinsi;
import id.kakarental.store.Model.registrasi.ResponseRegister;
import id.kakarental.store.Model.rekening.ResponseRekening;
import id.kakarental.store.Model.status_transaksi.ResponseKonfirmasi;
import id.kakarental.store.Model.status_transaksi.ResponseStatusDetailListTransaksi;
import id.kakarental.store.Model.status_transaksi.ResponseStatusTransaksi;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    //AUTHETIKASI

    @FormUrlEncoded
    @POST("api/auth")
    Call<ResponseLogin> getUser(@Field("username") String username,
                                @Field("password") String password,
                                @Field("token") String token);

    @Multipart
    @POST("api/uploadktp")
    Call<ResponseRegister> uploadpoto(@Part MultipartBody.Part gambar);


    @FormUrlEncoded
    @POST("api/register")
    Call<ResponseRegister> postRegistrasi(@Field("a") String username,
                                          @Field("b") String pass,
                                          @Field("c") String namalengkap,
                                          @Field("d") String email,
                                          @Field("g") String nohp,
//                                          @Field("h") String kotaId,
//                                          @Field("e") String alamatLengkap,
//                                          @Field("f") String status,
                                          @Field("m") String id_kk
    );



    @FormUrlEncoded
    @PUT("api/register/{id}")
    Call<ResponseRegister> update(@Path("id") String id,
                                  @Field("c") String fullname,
                                  @Field("d") String email,
                                  @Field("e") String notlpn,
                                  @Field("f") String nowa,
                                  @Field("h") String tgl,
                                  @Field("i") String instagram,
                                  @Field("j") String tempat
    );
    @FormUrlEncoded
    @PUT("api/uploadktp/{id}")
    Call<ResponseRegister> update2(@Path("id") String id,
                                  @Field("c") String fullname,
                                  @Field("d") String email,
                                  @Field("e") String notlpn,
                                  @Field("f") String nowa,
                                  @Field("h") String tgl,
                                  @Field("i") String instagram,
                                  @Field("j") String tempat
    );

    @GET("api/auth/{id_konsumen}")
    Call<ResponseRegister> getdatauser(@Path("id_konsumen") String id);


    @Multipart
    @POST("api/lainlain")
    Call<ResponseRegister> updatektp(@Part MultipartBody.Part gambar);

    @FormUrlEncoded
    @PUT("api/lainlain/{id}")
    Call<ResponseRegister> updatePass(@Path("id") String id,
                                      @Field("b") String pass

    );

    //Provinsi
    @GET("api/Wilayah")
    Call<ResponseProvinsi> getProv();

    @FormUrlEncoded
    @POST("api/Wilayah")
    Call<ResponseKota> postKota(@Field("id_provinsi") String id_provinsi);


    //BANNER
    @GET("api/banner")
    Call<ResponseBanner> banner();

    //DASBOARD

    @GET("api/dasboard")
    Call<ResponseDasboard> getDasboard();

    @FormUrlEncoded
    @POST("api/dasboard")
    Call<ResponseDetailProduk> detailProduk(@Field("id_produk") String idProduk,
                                            @Field("id_penjualanDetail") String idPenjualanDetail);

    //KATEGORI

    @GET("api/kategori/{input}")
    Call<ResponseKategori> getKategori(@Path("input") String input);
    //CARTS

    @GET("api/carts/{id}")
    Call<ResponseCart> getCart(@Path("id") String id);

    @FormUrlEncoded
    @POST("api/carts")
    Call<ResponseCart> insertCart(@Field("id") String id,
                                  @Field("a") String id_produk,
                                  @Field("b") String harga_jual,
                                  @Field("c") String harga_sewa,
                                  @Field("d") String durasi,
                                  @Field("e") String tgl_sewa,
                                  @Field("f") String tgl_akhir,
                                  @Field("g") String jenis_sewa,
                                  @Field("h") String tipe
    );

    @DELETE("api/carts/{id}")
    Call<ResponseCart> deleteCart(@Path("id") String id);

    @DELETE("api/delete_item/{id}")
    Call<ResponseDelete> deleteItemCart(@Path("id") String id);

    //TRANSAKSI

    @GET("api/alamat/{id}")
    Call<ResponseAlamt> getAlamat(@Path("id") String id);

    @FormUrlEncoded
    @POST("api/transaksi")
    Call<ResponseCheckOut> ceckOut(@Field("a") String idPembeli,
                                   @Field("b") String TotalPembayaran,
                                   @Field("d") String kurir,
                                   @Field("e") String service,
                                   @Field("f") String ongkir,
                                   @Field("g") String alamat_id,
                                   @Field("h") String admin,
                                   @Field("i") String kodeUnik,
                                   @Field("LIST_CART") String ListCart);

    @FormUrlEncoded
    @POST("starter/cost")
    Call<ResponseRajaOngkir> getOngkir(@Field("key") String key,
                                       @Field("origin") String asal,
                                       @Field("destination") String tujuan,
                                       @Field("weight") String berat,
                                       @Field("courier") String kurir);

    //STATUS TRANSAKSI

    @GET("api/status_transaksi/{id}")
    Call<ResponseStatusTransaksi> getTransaksi(@Path("id") String id);

    @FormUrlEncoded
    @POST("api/status_transaksi")
    Call<ResponseStatusDetailListTransaksi> getDetailTransaksi(@Field("kode_transaksi") String kodeTransaksi,
                                                               @Field("id_penjualan") String Idpenjualan);

    @FormUrlEncoded
    @PUT("api/transaksi")
    Call<ResponseKonfirmasi> konfirm(@Field("kode_transaksi") String kodeTransaksi);

    //Alamat
    @FormUrlEncoded
    @POST("api/alamat")
    Call<ResponseAlamat> getDetaiAlamat(@Field("a") String id_alamat);

    @FormUrlEncoded
    @POST("api/tambahAlamat")
    Call<ResponseTambahAlamat> tambahAlamat(@Field("a") String AtasNama,
                                            @Field("b") String kotaId,
                                            @Field("c") String NoHp,
                                            @Field("d") String Alamat,
                                            @Field("f") String status,
                                            @Field("e") String userId

    );

    @FormUrlEncoded
    @PUT("api/tambahAlamat/{id}")
    Call<ResponseTambahAlamat> updateAlamat(@Path("id") String id,
                                            @Field("a") String AtasNama,
                                            @Field("b") String kotaId,
                                            @Field("c") String NoHp,
                                            @Field("d") String Alamat,
                                            @Field("f") String status,
                                            @Field("e") String userId
    );


    //SEWA
    @GET("api/list_sewa/{id}")
    Call<ResponseListSewa> getSewa(@Path("id") String id_user);

    @FormUrlEncoded
    @PUT("api/perpanjang_sewa")
    Call<ResponsePerpanjang> perpanjangSewa(@Field("id_penjualan") String idPenjualan,
                                            @Field("total") String total,
                                            @Field("harga") String harga,
                                            @Field("jenis") String jenis,
                                            @Field("tanggal") String tanggal,
                                            @Field("durasi") String durasi);

    @FormUrlEncoded
    @POST("api/perpanjang_sewa")
    Call<ResponseKonfirm> konfirmPerpanjang(@Field("id_penjualan") String Id_penjualan);

    //REKENING
    @GET("api/rekening")
    Call<ResponseRekening> getRek();

}
