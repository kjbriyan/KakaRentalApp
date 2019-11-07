package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.CarouselViewPagerTransformer;
import com.synnapps.carouselview.ImageListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.kakarental.store.Adapter.Dasboard_Sewa_Adapter;
import id.kakarental.store.Helper.DatePickerView;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.dasboard.DataIDasboardSewa;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSewaActivity extends AppCompatActivity {
    private static final String TAG = "DetailSewaActivity";
    public static final String EXTRA_SewaDetail = "DetailSewaActivity.sewa";


    private TextView title, desc, sewa1hari, sewa1minggu, sewa1bln, badgeCart, tv_tanggal_kembali, desc_jangka, total_sewa;
    private ImageView imageView, back;
    ViewFlipper vf;
    private Button btn_cart;
    private LinearLayout container;
    private DataIDasboardSewa data;
    private List<DataItemCart> getData;
    private int size = 0;
    private DatePickerView tanggalSewa;
    private RadioButton rb_1hari, rb_2hari, rb_3hari;
    private Calendar c;
    private SimpleDateFormat sdf;
    private String hari, harga_sewa, jenis_sewa;
    private EditText jangka;
    Context mContext;
    private String img, img2, img3, img4;
    ProgressDialog dialog;
    ImageView cart;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.placeholder_fail, R.drawable.placeholder_fail};
    String[] sampleNetworkImageURLs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk_sewa);
        
        mContext = this;
        
        dialog = new ProgressDialog(this);
        dialog.setMessage("Mohon Tunggu ...");
        dialog.setCancelable(false);

        badgeCart = findViewById(R.id.tv_iconCartBadge_Detail);
        badgeCart.setVisibility(View.GONE);



        // getCart();
        initUi();
        // checkOrder();
    }

    private void initUi() {
        cart = findViewById(R.id.img_cartToolbarDetail);
        back = findViewById(R.id.iv_backsewa);
        container = findViewById(R.id.container);
        tanggalSewa = findViewById(R.id.dateDeatilActivity);
        title = findViewById(R.id.tv_titleDetailSewa);
        carouselView = findViewById(R.id.img_HolderDetail);
        desc = findViewById(R.id.desc_detail);
        sewa1hari = findViewById(R.id.tv_hargaSewahari);
        sewa1minggu = findViewById(R.id.tv_HargaSewaMinggu);
        sewa1bln = findViewById(R.id.tv_HargaSewaBulan);
        desc_jangka = findViewById(R.id.tv_descJangka);
        total_sewa = findViewById(R.id.tv_totalSewa);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        tv_tanggal_kembali = findViewById(R.id.tv_tanggalKembali);
        rb_1hari = findViewById(R.id.rb_1hari);
        rb_2hari = findViewById(R.id.rb_1minggu);
        rb_3hari = findViewById(R.id.rb_1bulan);
        btn_cart = findViewById(R.id.btn_add_cart);

        jangka = findViewById(R.id.et_jangkaWaktu);

        container.setVisibility(View.GONE);
        jangka.setEnabled(false);

        Gson gson = new Gson();
        data = gson.fromJson(getIntent().getStringExtra(Dasboard_Sewa_Adapter.getDetail()), DataIDasboardSewa.class);

        img = InitRetrofit.getIMG_URL() + data.getGambar();
        img2 = InitRetrofit.getIMG_URL() + data.getGambar2();
        img3 = InitRetrofit.getIMG_URL() + data.getGambar3();
        img4 = InitRetrofit.getIMG_URL() + data.getGambar4();

        sampleNetworkImageURLs = new String[]{img, img2, img3, img4};

        carouselView.setPageCount(sampleNetworkImageURLs.length);
        carouselView.setPageTransformInterval(1000);
        carouselView.setPageTransformer(CarouselViewPagerTransformer.DEPTH);
        carouselView.setImageListener(imageListener);

        title.setText(data.getNamaProduk());
        desc.setText(Html.fromHtml(data.getKeterangan()));

        sewa1hari.setText(FormatRp.getFomat(data.getSewaHarian()));
        sewa1minggu.setText(FormatRp.getFomat(data.getSewaMingguan()));
        sewa1bln.setText(FormatRp.getFomat(data.getSewaBulanan()));

        setRadiobtn();
        jangkaChangedListener();
        buttonProses();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailSewaActivity.this, KeranjangActivity.class);

                startActivity(i);
            }
        });

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sampleNetworkImageURLs[position]).placeholder(sampleImages[0])
                    .error(sampleImages[1]).into(imageView);
        }
    };


    private void getCart() {
        dialog.show();
        String id_pelanggan = Prefs.getString(SharedPreff.getIdKonsumen(), null);
        Call<ResponseCart> cart = InitRetrofit.getInstance().getCart(id_pelanggan);

        cart.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                dialog.hide();
                container.setVisibility(View.VISIBLE);
                ResponseCart res = response.body();

                if (!response.isSuccessful()) {
                    return;
                } else if (res.isStatus()) {
                    List<DataItemCart> item = res.getData();
                    size = item.size();
                    getData = item;
                    checkOrder();
                    setBadgeCart();
                } else {
                    size = 0;
                    setBadgeCart();
                    checkOrder();

                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                dialog.hide();
                Snackbar.make(container, "TIdak ada koneksi", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Refresh", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getCart();
                            }
                        }).show();
            }
        });
    }

    private void setBadgeCart() {
        if (size > 0) {
            badgeCart.setVisibility(View.VISIBLE);
            badgeCart.setText(Integer.toString(size));
        } else {
            badgeCart.setVisibility(View.GONE);
        }
    }

    private void setRadiobtn() {

        rb_1hari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(tanggalSewa.getText())) {
                    rb_2hari.setChecked(false);
                    rb_3hari.setChecked(false);
                    jenis_sewa = "Harian";
                    desc_jangka.setText("HARI");
                    jangka.setEnabled(true);
                    jangka.setText("1");
                    jangka.setKeyListener(DigitsKeyListener.getInstance("123456"));
                    jangka.setFilters(new InputFilter[]{
                            new InputFilter.LengthFilter(1)
                    });

                } else {
                    Toast.makeText(DetailSewaActivity.this, "Harus Plih Tanggal Dulu Boss", Toast.LENGTH_SHORT).show();
                    rb_1hari.setChecked(false);
                    rb_2hari.setChecked(false);
                    rb_3hari.setChecked(false);
                    jangka.setEnabled(false);
                }

            }
        });

        rb_2hari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(tanggalSewa.getText())) {
                    rb_1hari.setChecked(false);
                    rb_3hari.setChecked(false);
                    jenis_sewa = "Mingguan";
                    desc_jangka.setText("MINGGU");
                    jangka.setEnabled(true);
                    jangka.setText("1");
                    jangka.setKeyListener(DigitsKeyListener.getInstance("123"));
                    jangka.setFilters(new InputFilter[]{
                            new InputFilter.LengthFilter(1)
                    });

                } else {
                    Toast.makeText(DetailSewaActivity.this, "Harus Plih Tanggal Dulu Boss", Toast.LENGTH_SHORT).show();
                    rb_1hari.setChecked(false);
                    rb_2hari.setChecked(false);
                    rb_3hari.setChecked(false);
                    jangka.setEnabled(false);
                }

            }
        });

        rb_3hari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(tanggalSewa.getText())) {
                    rb_1hari.setChecked(false);
                    rb_2hari.setChecked(false);
                    jenis_sewa = "Bulanan";
                    desc_jangka.setText("BULAN");
                    jangka.setKeyListener(DigitsKeyListener.getInstance("123456789"));
                    jangka.setText("1");
                    jangka.setEnabled(true);
                    jangka.setFilters(new InputFilter[]{
                            new InputFilter.LengthFilter(2)
                    });
                } else {
                    Toast.makeText(DetailSewaActivity.this, "Harus Plih Tanggal Dulu Boss", Toast.LENGTH_SHORT).show();
                    rb_1hari.setChecked(false);
                    rb_2hari.setChecked(false);
                    rb_3hari.setChecked(false);
                    jangka.setEnabled(false);
                }

            }
        });
    }

    private void jangkaChangedListener() {

        jangka.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                int jangkaW;
                if (!(s.length() > 0)) {
                    jangkaW = 1;

                } else {
                    jangkaW = Integer.parseInt(s.toString());
                }

                if (rb_1hari.isChecked()) {
                    int sewahari = Integer.parseInt(data.getSewaHarian());
                    String total = String.valueOf(sewahari * jangkaW);
                    total_sewa.setText(FormatRp.getFomat(total));
                    iniSewa(jangkaW * 1);
                    hari = jangkaW * 1 + " hari";
                    harga_sewa = data.getSewaHarian();
                } else if (rb_2hari.isChecked()) {
                    int sewaMinggu = Integer.parseInt(data.getSewaMingguan());
                    String total = String.valueOf(sewaMinggu * jangkaW);
                    total_sewa.setText(FormatRp.getFomat(total));
                    iniSewa(jangkaW * 7);
                    hari = jangkaW * 7 + " hari";
                    harga_sewa = data.getSewaMingguan();
                } else {
                    int sewaBulan = Integer.parseInt(data.getSewaBulanan());
                    String total = String.valueOf(sewaBulan * jangkaW);
                    total_sewa.setText(FormatRp.getFomat(total));
                    iniSewa(jangkaW * 30);
                    hari = jangkaW * 30 + " hari";
                    harga_sewa = data.getSewaBulanan();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void iniSewa(int hari) {

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        c.setTime(tanggalSewa.getDate());
        c.add(Calendar.DATE, hari);
        Date result = new Date(c.getTimeInMillis());
        String hasil = sdf.format(result);
        tv_tanggal_kembali.setText(hasil);
        Log.d(TAG, "onClick: hasi;" + hasil);
    }

    private void buttonProses() {

        if (data.getStatus().equals("1")) {

//            btn_cart.setBackgroundResource(R.color.red);
            btn_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tgl_kembali = tv_tanggal_kembali.getText().toString();

                    if (!tgl_kembali.equals("Tanggal Kembali")) {
                        dialog.show();
                        insertCart();
                        return;
                    }

                    Toast.makeText(DetailSewaActivity.this, "Tanggal Sewa Belum Terisi", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
//            btn_cart.setBackgroundResource(R.color.red);
            btn_cart.setText("TIDAK TERSEDIA");
           
        }

    }

    private void MessageSuccess() {
        Snackbar.make(container, "Berhasil Ditambahkan ke Keranjang", Snackbar.LENGTH_SHORT).show();
    }


    private void checkOrder() {

        if (size > 0) {
            for (int i = 0; i < getData.size(); i++) {
                if (getData.get(i).getIdProduk().equals(data.getIdProduk())) {
                    btn_cart.setText("Sudah Dipesan");
                    btn_cart.setClickable(false);
                    btn_cart.setBackgroundResource(R.color.orange);
                    break;
                } else if (!data.getStatus().equals("1")){
                    btn_cart.setText("TAMBAHKAN KE KERANJANG");
                    btn_cart.setClickable(true);
                    btn_cart.setBackgroundResource(R.color.red);
                }else {
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

    private void insertCart() {

        String id = Prefs.getString(SharedPreff.getIdKonsumen(), null);
        String id_produk = data.getIdProduk();
        String tgl_sewa = tanggalSewa.getText().toString();
        String tgl_kembali = tv_tanggal_kembali.getText().toString();
        String nama_tipe = data.getTipe();

        Call<ResponseCart> insert = InitRetrofit.getInstance().insertCart(
                id,
                id_produk,
                "0",
                harga_sewa,
                jangka.getText().toString(),
                tgl_sewa,
                tgl_kembali,
                jenis_sewa,
                nama_tipe

        );

        insert.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                dialog.hide();
                ResponseCart res = response.body();
                if (!response.isSuccessful()) {
                    return;
                } else if (res.isStatus()) {

                    getCart();
                    MessageSuccess();
                    Toast.makeText(DetailSewaActivity.this, "Berhasil insert", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailSewaActivity.this, "error" + res.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                dialog.hide();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCart();
    }
}


