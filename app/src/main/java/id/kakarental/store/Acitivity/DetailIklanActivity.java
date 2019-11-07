package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.CarouselViewPagerTransformer;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import id.kakarental.store.Adapter.Dasboard_iklan_Adapter;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.dasboard.DataDasboardJual;
import id.kakarental.store.Model.dasboard.DataIklan;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;

public class DetailIklanActivity extends AppCompatActivity {
    private static final String TAG = "DetailIklanActivity";

    private TextView title, tv_harga, nama, lokasi, hp, wa, detail;
    private ImageView imageView, back, cart;
    private Button btn_cart, btn_whatsapp;
    private ConstraintLayout container;

    private DataIklan data2;
    CarouselView carouselView;
    List<DataItemCart> carts;
    private String img, img2, img3, img4;
    ProgressDialog dialog;
    String tipe;

    int[] sampleImages = {R.drawable.placeholder_fail,R.drawable.placeholder_fail};
    String[] sampleNetworkImageURLs ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk_iklan);

        Gson gson = new Gson();
        tipe = getIntent().getStringExtra("tipe");
        data2 = gson.fromJson(getIntent().getStringExtra(Dasboard_iklan_Adapter.getDetail()), DataIklan.class);

        img = InitRetrofit.getIMG_URL() + data2.getGambar();
        img2 = InitRetrofit.getIMG_URL() + data2.getGambar2();
        img3 = InitRetrofit.getIMG_URL() + data2.getGambar3();
        img4 = InitRetrofit.getIMG_URL() + data2.getGambar4();


        sampleNetworkImageURLs = new String[]{img,img2,img3,img4};

        dialog = new ProgressDialog(this);
        dialog.setMessage("Mohon Tunggu ...");
        dialog.setCancelable(false);


        initUi();

    }

    private void initUi() {


        tv_harga = findViewById(R.id.tv_hargaJual);
        container = findViewById(R.id.containerDetailSewa);
        title = findViewById(R.id.tv_tittleJualdetail);
        carouselView = findViewById(R.id.img_HolderDetail);
        btn_cart = findViewById(R.id.btn_add_cart);
        detail = findViewById(R.id.tv_descDetail);
        back = findViewById(R.id.iv_backsewa);
        nama = findViewById(R.id.tv_nama);
        lokasi = findViewById(R.id.lokasi);
        hp = findViewById(R.id.no_hp);
        wa = findViewById(R.id.whatsapp);
        btn_whatsapp = findViewById(R.id.btn_whatsapp);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        detail.setText(Html.fromHtml(data2.getKeterangan()).toString());
        title.setText(data2.getNamaProduk());
        tv_harga.setText(FormatRp.getFomat(data2.getHarga()));
        nama.setText(data2.getAtasNama());
        lokasi.setText(data2.getAlamat());
        hp.setText(data2.getNoHp());
        wa.setText(data2.getNoWa());

        btn_cart.setText("Hubungi Penjual " + "(" + data2.getNoWa() + ")");
        btn_cart.setBackgroundResource(R.color.orange);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data2.getNoHp())));
            }
        });

        btn_whatsapp.setText("Whatsapp Penjual " + "(" + data2.getNoWa() + ")");
        btn_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = data2.getNoWa();
                number = "62" + number.substring(1);
                String url = "https://api.whatsapp.com/send?phone=" + number + "&text=Saya%20Berminat%20Dengan%20Produk%" +
                        "20Anda";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });



        carouselView.setPageCount(sampleNetworkImageURLs.length);
        carouselView.setPageTransformInterval(1000);
        carouselView.setPageTransformer(CarouselViewPagerTransformer.DEPTH);
        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sampleNetworkImageURLs[position]).placeholder(sampleImages[0])
                    .error(sampleImages[1]).into(imageView);
        }
    };

}


