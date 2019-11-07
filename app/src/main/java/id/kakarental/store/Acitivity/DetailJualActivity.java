package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.CarouselViewPagerTransformer;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import id.kakarental.store.Adapter.Dasboard_Sewa_Adapter;
import id.kakarental.store.Adapter.Dasboard_iklan_Adapter;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.dasboard.DataDasboardJual;
import id.kakarental.store.Model.dasboard.DataIklan;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailJualActivity extends AppCompatActivity {
    private static final String TAG = "DetailJualActivity";

    private TextView title, tv_harga, badgeCart, detail;
    private ImageView imageView, back, cart;
    private Button btn_cart;
    private ConstraintLayout container;
    private DataDasboardJual data;
    private DataIklan data2;
    private int size = 0;
    List<DataItemCart> carts;
    private String img, img2, img3, img4;
    ProgressDialog dialog;
    String tipe;
    CarouselView carouselView;

    int[] sampleImages = {R.drawable.placeholder_fail, R.drawable.placeholder_fail};
    String[] sampleNetworkImageURLs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk_jual);

        Gson gson = new Gson();
        tipe = getIntent().getStringExtra("tipe");
        if (tipe.equals("iklan")) {
            data2 = gson.fromJson(getIntent().getStringExtra(Dasboard_iklan_Adapter.getDetail()), DataIklan.class);
        } else {
            data = gson.fromJson(getIntent().getStringExtra(Dasboard_Sewa_Adapter.getDetail()), DataDasboardJual.class);
        }

        dialog = new ProgressDialog(this);
        dialog.setMessage("Mohon Tunggu ...");
        dialog.setCancelable(false);


//        getCart();
        initUi();
        checkStok();
        // checkOrder();
    }

    private void initUi() {

        cart = findViewById(R.id.img_cartToolbarDetail);
        tv_harga = findViewById(R.id.tv_hargaJual);
        container = findViewById(R.id.containerDetailSewa);
        badgeCart = findViewById(R.id.tv_iconCartBadge_Detail);
        title = findViewById(R.id.tv_tittleJualdetail);
        carouselView = findViewById(R.id.img_HolderDetail);
        btn_cart = findViewById(R.id.btn_add_cart);
        detail = findViewById(R.id.tv_descDetail);
        back = findViewById(R.id.iv_backsewa);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        badgeCart.setVisibility(View.GONE);

        img = InitRetrofit.getIMG_URL() + data.getGambar();
        img2 = InitRetrofit.getIMG_URL() + data.getGambar2();
        img3 = InitRetrofit.getIMG_URL() + data.getGambar3();
        img4 = InitRetrofit.getIMG_URL() + data.getGambar4();

        sampleNetworkImageURLs = new String[]{img,img2,img3,img4};

        carouselView.setPageCount(sampleNetworkImageURLs.length);
        carouselView.setPageTransformInterval(1000);
        carouselView.setPageTransformer(CarouselViewPagerTransformer.DEPTH);
        carouselView.setImageListener(imageListener);

        detail.setText(Html.fromHtml(data.getKeterangan()).toString());
        title.setText(data.getNamaProduk());
        tv_harga.setText(FormatRp.getFomat(data.getHargaKonsumen()));

        buttonProses();

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sampleNetworkImageURLs[position]).placeholder(sampleImages[0])
                    .error(sampleImages[1]).into(imageView);
        }
    };

    private void buttonProses() {


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailJualActivity.this, KeranjangActivity.class);

                startActivity(i);
            }
        });

        btn_cart.setBackgroundResource(R.color.green);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                Log.d(TAG, "onClick: button");
                saveCart();
                getCart();
            }
        });
    }

    private void getCart() {
        dialog.show();
        Call<ResponseCart> cart = InitRetrofit.getInstance().getCart(Prefs.getString(SharedPreff.getIdKonsumen(), null));

        cart.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {

                ResponseCart res = response.body();
                dialog.hide();
                if (!response.isSuccessful()) {

                    return;
                } else if (res.isStatus()) {
                    List<DataItemCart> items = res.getData();
                    carts = items;
                    size = items.size();
                    checkOrder();
                    setbadge();

                } else {
                    size = 0;
                    setbadge();
                    checkOrder();
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {

                dialog.hide();
            }
        });
    }

    private void setbadge() {
        if (size > 0) {
            badgeCart.setVisibility(View.VISIBLE);
            badgeCart.setText(Integer.toString(size));
        } else {
            badgeCart.setVisibility(View.GONE);
        }
    }

    private void checkStok(){


    }

    private void checkOrder() {

        if (data.getStok()<1){
            btn_cart.setText("STOK HABIS");
            btn_cart.setClickable(false);
            btn_cart.setBackgroundResource(R.color.red);
        }else {
            if (size > 0) {
                for (int i = 0; i < carts.size(); i++) {
                    if (carts.get(i).getIdProduk().equals(data.getIdProduk())) {
                        btn_cart.setText("SUDAH DIPESAN");
                        btn_cart.setClickable(false);
                        btn_cart.setBackgroundResource(R.color.orange);
                        break;
                    } else {
                        btn_cart.setText("TAMBAHKAN KE KERANJANG");
                        btn_cart.setClickable(true);
                        btn_cart.setBackgroundResource(R.color.green);
                    }
                }
            } else {
                btn_cart.setText("TAMBAHKAN KE KERANJANG");
                btn_cart.setClickable(true);
                btn_cart.setBackgroundResource(R.color.green);
            }
        }
    }

    private void saveCart() {

        String id = Prefs.getString(SharedPreff.getIdKonsumen(), null);
        String idProduk = data.getIdProduk();
        String tipe = data.getTipe();
        String harga = "0";
        if (!data.getDiskon().equals("0")) {
            Double nilai = Double.parseDouble(data.getHargaKonsumen()) - Double.parseDouble(data.getDiskon());
            harga = String.valueOf(nilai);
        } else {
            harga = data.getHargaKonsumen();
        }

        Call<ResponseCart> save = InitRetrofit.getInstance().insertCart(id, idProduk, harga, "0", "0", null,
                null, null, tipe);

        save.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                dialog.hide();

                Log.d(TAG, "onResponse: retrofit");

                if (response.isSuccessful()) {

                    Log.d(TAG, "onResponse: success");

                    MessageSuccess();
                    getCart();
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                dialog.hide();
                Toast.makeText(DetailJualActivity.this, "error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void MessageSuccess() {
        Snackbar.make(container, "Berhasil Ditambahkan ke Keranjang", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCart();
    }
}


