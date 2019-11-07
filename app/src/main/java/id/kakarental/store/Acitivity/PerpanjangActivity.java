package id.kakarental.store.Acitivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.CarouselViewPagerTransformer;
import com.synnapps.carouselview.ImageListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.dasboard.Produk;
import id.kakarental.store.Model.dasboard.ResponseDetailProduk;
import id.kakarental.store.Model.perpanjang.ResponsePerpanjang;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerpanjangActivity extends AppCompatActivity {
    private static final String TAG = "PerpanjangActivity";
    private Context context;
    private ImageView back;
    private TextView title, desc, sewa1hari, sewa1minggu, sewa1bln, badgeCart, tv_tanggal_kembali, desc_jangka, total_sewa;
    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.placeholder_fail, R.drawable.placeholder_fail};
    private String img, img2, img3, img4;
    String[] sampleNetworkImageURLs;
    private String id_penjualan, kode_trans, total, tanggal, durasi, jenis_sewa;
    private String hari, harga_sewa;
    private RadioButton rb_1hari, rb_2hari, rb_3hari;
    private Button btn_cart;
    private EditText tanggalSewa, jangka;
    private Produk data;
    private SimpleDateFormat sdf;
    private Calendar c;
    private CardView container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perpanjang);

        initEvent();


    }

    private void initEvent() {

        initUi();
        initButton();
        initData();
        setRadiobtn();
        jangkaChangedListener();
    }

    private void initUi() {

        context = this;

        back = findViewById(R.id.iv_backsewa);
        carouselView = findViewById(R.id.img_HolderDetail);
        carouselView.setPageTransformInterval(1000);
        carouselView.setPageTransformer(CarouselViewPagerTransformer.DEPTH);
        carouselView.setImageListener(imageListeners);
        title = findViewById(R.id.tv_titleDetailSewa);
        desc = findViewById(R.id.desc_detail);
        tanggalSewa = findViewById(R.id.dateDeatilActivity);
        sewa1hari = findViewById(R.id.tv_hargaSewahari);
        sewa1minggu = findViewById(R.id.tv_HargaSewaMinggu);
        sewa1bln = findViewById(R.id.tv_HargaSewaBulan);

        desc_jangka = findViewById(R.id.tv_descJangka);
        total_sewa = findViewById(R.id.tv_totalSewa);

        container = findViewById(R.id.cardView9);

        tv_tanggal_kembali = findViewById(R.id.tv_tanggalKembali);
        rb_1hari = findViewById(R.id.rb_1hari);
        rb_2hari = findViewById(R.id.rb_1minggu);
        rb_3hari = findViewById(R.id.rb_1bulan);
        jangka = findViewById(R.id.et_jangkaWaktu);
        btn_cart = findViewById(R.id.btn_add_cart);


    }

    private void initButton() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonPerpanjang();
    }

    private void initData() {
        String id_produk = getIntent().getStringExtra("id_produk");
        String id_penjualanDetail = getIntent().getStringExtra("id_detail");
        Log.d(TAG, "initEvent: " + id_produk);
        getDetailProduk(id_produk, id_penjualanDetail);

    }


    private void getDetailProduk(String id_produk, String id_penjualanDetail) {

        Call<ResponseDetailProduk> detailProduk = InitRetrofit.getInstance().detailProduk(id_produk, id_penjualanDetail);
        detailProduk.enqueue(new Callback<ResponseDetailProduk>() {
            @Override
            public void onResponse(Call<ResponseDetailProduk> call, Response<ResponseDetailProduk> response) {
                ResponseDetailProduk res = response.body();

                if (response.isSuccessful()) {

                    data = res.getProduk();

                    initDataView();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailProduk> call, Throwable t) {

            }
        });
    }

    private void initDataView() {

        statusPerpanjang();

        id_penjualan = getIntent().getStringExtra("id_penjualan");


        img = InitRetrofit.getIMG_URL() + data.getGambar();
        img2 = InitRetrofit.getIMG_URL() + data.getGambar2();
        img3 = InitRetrofit.getIMG_URL() + data.getGambar3();
        img4 = InitRetrofit.getIMG_URL() + data.getGambar4();

        sampleNetworkImageURLs = new String[]{img, img2, img3, img4};
        carouselView.setPageCount(sampleNetworkImageURLs.length);

        title.setText(data.getNamaProduk());
        desc.setText(Html.fromHtml(data.getKeterangan()));
        tanggalSewa.setText(data.getTglAkhir());
        sewa1hari.setText(FormatRp.getFomat(data.getSewaHarian()));
        sewa1minggu.setText(FormatRp.getFomat(data.getSewaMingguan()));
        sewa1bln.setText(FormatRp.getFomat(data.getSewaBulanan()));
    }

    ImageListener imageListeners = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sampleNetworkImageURLs[position]).placeholder(sampleImages[0])
                    .error(sampleImages[1]).into(imageView);
        }
    };

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
                    Toast.makeText(context, "Harus Plih Tanggal Dulu Boss", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Harus Plih Tanggal Dulu Boss", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Harus Plih Tanggal Dulu Boss", Toast.LENGTH_SHORT).show();
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
        Date date = new Date();
        try {
            date = sdf.parse(data.getTglAkhir());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, hari);
        Date result = new Date(c.getTimeInMillis());
        String hasil = sdf.format(result);
        tv_tanggal_kembali.setText(hasil);
        Log.d(TAG, "onClick: hasi;" + hasil);
    }

    private void buttonPerpanjang() {

//            btn_cart.setBackgroundResource(R.color.red);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tgl_kembali = tv_tanggal_kembali.getText().toString();

                if (!tgl_kembali.equals("Tanggal Kembali")) {

                    final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    perpanjang();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialogInterface.dismiss();
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(PerpanjangActivity.this);
                    builder.setMessage("Apakah anda yakin akan melakukan perpanjangan sewa ?").setPositiveButton("Ya", dialogClickListener)
                            .setNegativeButton("Tidak", dialogClickListener).show();

                } else {
                    Toast.makeText(context, "Tanggal Sewa Belum Terisi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void statusPerpanjang() {
        int proses = Integer.valueOf(getIntent().getStringExtra("proses"));
        if (proses > 2) {
            container.setVisibility(View.VISIBLE);
        } else {
            container.setVisibility(View.GONE);
        }

    }

    private void perpanjang() {
        int total = (Integer.valueOf(harga_sewa) * (Integer.valueOf(jangka.getText().toString())));
        Log.d(TAG, "perpanjang: " + id_penjualan + " , " + total + " , " + harga_sewa + " , "
                + jenis_sewa + " , " + tv_tanggal_kembali.getText().toString() + " , " + jangka.getText().toString());

        Call<ResponsePerpanjang> perpanjang = InitRetrofit.getInstance().perpanjangSewa(id_penjualan, String.valueOf(total), harga_sewa, jenis_sewa, tv_tanggal_kembali.getText().toString(), jangka.getText().toString());
        perpanjang.enqueue(new Callback<ResponsePerpanjang>() {
            @Override
            public void onResponse(Call<ResponsePerpanjang> call, Response<ResponsePerpanjang> response) {
                pesansukses();
                finish();
            }

            @Override
            public void onFailure(Call<ResponsePerpanjang> call, Throwable t) {

            }
        });
    }

    private void pesansukses() {
        Snackbar.with(this, null)
                .type(Type.SUCCESS)
                .message("Perpanjangan Berhasil Dikirim!")
                .duration(Duration.SHORT)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();
    }


}
