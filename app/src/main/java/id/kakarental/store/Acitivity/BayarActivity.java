package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pixplicity.easyprefs.library.Prefs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Adapter.BayarlayoutAdapter;
import id.kakarental.store.Adapter.RajaOngkir_adapter;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.DataItemAlamat;
import id.kakarental.store.Model.ResponseAlamt;
import id.kakarental.store.Model.ResponseCheckOut;
import id.kakarental.store.Model.raja_ongkir.CostItem;
import id.kakarental.store.Model.raja_ongkir.CostsItem;
import id.kakarental.store.Model.raja_ongkir.ResponseRajaOngkir;
import id.kakarental.store.Model.raja_ongkir.ResultsItem;
import id.kakarental.store.Network.ApiInterface;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BayarActivity extends AppCompatActivity {
    private static final String TAG = "BayarActivity";
    public static final String EXTRA_BAYAR = "BayarActivity.bayar";
    public static final int REQ_ALAMAT = 100;

    ConstraintLayout container;

    private TextView nama, noHp, btn_alamt, kota, prov, alamt,
            totalBayar, titleOngkir, biayaOngkir, beratOngkir,
            totalOngkir, notice, biayaAdmin;
    Button proses;
    private RecyclerView rv_bayar;
    private List<DataItemCart> dataCarts;
    private Context context;
    private ImageView img_back;
    private String kurirs = "";
    private RadioButton jne, pos, tiki, antarpemili, ambilsendiri;
    String harga = "";
    RecyclerView recyclerView;
    RajaOngkir_adapter adapter;
    String titles, tarif, berat, TotalAkhriCheckOut, kurir, invoices, alamat_id, biaya_Admin, kodeunik;
    ProgressDialog progressDialog;
    NestedScrollView containerScroll;
    String tujuan = null;


    List<DataItemAlamat> alamat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        context = this;
        container = findViewById(R.id.constraintLayout);

        progress();

        Type type = new TypeToken<List<DataItemCart>>() {
        }.getType();
        String json = getIntent().getStringExtra(KeranjangActivity.EXTRA_DATA);
        Gson gson = new Gson();
        dataCarts = gson.fromJson(json, type);
        for (int i = 0; i < dataCarts.size(); i++) {
            invoices = dataCarts.get(i).getSession();
        }

        getAlamat();
        initUi();
    }

    private void progress() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . . .");
        progressDialog.show();
        progressDialog.setCancelable(false);


    }


    private void initUi() {

        img_back = findViewById(R.id.img_back);
        rv_bayar = findViewById(R.id.rv_Bayarlayout);
        nama = findViewById(R.id.tv_namaBayar);
        noHp = findViewById(R.id.tv_noTlpBayar);
        containerScroll = findViewById(R.id.container);
        kota = findViewById(R.id.tv_kotaBayar);
        alamt = findViewById(R.id.tv_alamtBayar);
        prov = findViewById(R.id.tv_prov);
        notice = findViewById(R.id.tv_antarPem);
        notice.setVisibility(View.GONE);

        totalBayar = findViewById(R.id.tv_totalBayarlayout);
        proses = findViewById(R.id.btn_porsesbayar);
        titleOngkir = findViewById(R.id.tv_titelBayarOngkir);
        biayaOngkir = findViewById(R.id.tv_tarifOngkir);
        beratOngkir = findViewById(R.id.tv_beratOngkir);
        totalOngkir = findViewById(R.id.tv_totalOngkir);
        btn_alamt = findViewById(R.id.btn_pilihAlamt);
        jne = findViewById(R.id.rb_jne);
        pos = findViewById(R.id.rb_pos);
        tiki = findViewById(R.id.rb_tiki);
        antarpemili = findViewById(R.id.rb_pemilik);
        ambilsendiri = findViewById(R.id.rb_sendiri);
        biayaAdmin = findViewById(R.id.tvBiayaAdmin);

        recyclerView = findViewById(R.id.rv_TarifOngkirBayar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);
        containerScroll.setVisibility(View.GONE);

        initEvent();
        initRbClik();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_alamt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BayarActivity.this, PilihAlamatActivity.class);

                Gson gson = new Gson();
                String data = gson.toJson(alamat);
                i.putExtra("data_alamat", data);
                startActivityForResult(i, REQ_ALAMAT);
            }
        });

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kurirs.equals("")) {
                    Toast.makeText(context, "Pilih Kurir", Toast.LENGTH_SHORT).show();

                } else if (titleOngkir.getText().toString().equals("Jenis Layanan")) {
                    Toast.makeText(context, "Pilih Layanan", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    checkOut();
                }
            }
        });
    }

    private void initEvent() {

        if (dataCarts.size() > 0) {
            initRV();
            checkTotal();
            checkBerat();
        }
    }

    private void getAlamat() {

        String alamats = Prefs.getString(SharedPreff.getIdKonsumen(), null);
        Call<ResponseAlamt> getAlamat = InitRetrofit.getInstance().getAlamat(Prefs.getString(SharedPreff.getIdKonsumen(), null));
        Log.d(TAG, "getAlamat: " + alamats);
        getAlamat.enqueue(new Callback<ResponseAlamt>() {

            @Override
            public void onResponse(Call<ResponseAlamt> call, Response<ResponseAlamt> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }


                ResponseAlamt res = response.body();
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: success");
                    containerScroll.setVisibility(View.VISIBLE);
                    if (response.body().isStatus()) {
                        alamat = res.getData();
                        for (int i = 0; i < alamat.size(); i++) {
                            tujuan = alamat.get(0).getKotaId();
                            nama.setText(alamat.get(0).getAtasNama());
                            noHp.setText(alamat.get(0).getNoHp());
                            kota.setText(alamat.get(0).getNamaKota());
                            alamt.setText(alamat.get(0).getAlamatLengkap());
                            alamat_id = alamat.get(0).getIdAlamat();
                            prov.setText(alamat.get(0).getNamaProvinsi());
                        }

                        kurir = "null";
                        getOngkir(kurir, tujuan);
                    }
                } else {
                    Toast.makeText(context, "GAGAL" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseAlamt> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                Snackbar.make(container, "Koneksi Bermasalah", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Refresh", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getAlamat();
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ALAMAT && resultCode == RESULT_OK) {

            Type type = new TypeToken<DataItemAlamat>() {
            }.getType();
            Gson gson = new Gson();
            String alamat = data.getStringExtra("data");
            DataItemAlamat getAlamat = gson.fromJson(alamat, type);

            // Log.d(TAG, "onActivityResult: " + REQ_ALAMAT + " fwegf " + RESULT_OK);

            tujuan = getAlamat.getKotaId();
            nama.setText(getAlamat.getAtasNama());
            noHp.setText(getAlamat.getNoHp());
            kota.setText(getAlamat.getNamaKota());
            alamt.setText(getAlamat.getAlamatLengkap());
            alamat_id = getAlamat.getIdAlamat();
            prov.setText(getAlamat.getNamaProvinsi());

            //Toast.makeText(context, kurir + " " + tujuan, Toast.LENGTH_SHORT).show();

            getOngkir(kurirs, tujuan);
            if (kurirs == "null") {
                recyclerView.setVisibility(View.GONE);
            }
        }

    }

    private void initRV() {
        rv_bayar.setHasFixedSize(true);
        rv_bayar.setLayoutManager(new LinearLayoutManager(this));

        BayarlayoutAdapter adapter = new BayarlayoutAdapter(dataCarts, BayarActivity.this);
        rv_bayar.setAdapter(adapter);
    }

    private void initRbClik() {

        jne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRbDisable(1);
                kurirs = "jne";
                getOngkir(kurirs, tujuan);
                recyclerView.setVisibility(View.VISIBLE);
                notice.setVisibility(View.GONE);
            }
        });

        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRbDisable(2);
                kurirs = "pos";
                getOngkir(kurirs, tujuan);
                recyclerView.setVisibility(View.VISIBLE);
                notice.setVisibility(View.GONE);
            }
        });

        tiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRbDisable(3);
                kurirs = "tiki";
                getOngkir(kurirs, tujuan);
                recyclerView.setVisibility(View.VISIBLE);
                notice.setVisibility(View.GONE);
            }
        });

        antarpemili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRbDisable(4);
                kurirs = "null";
                getOngkir(kurirs, "null");
                recyclerView.setVisibility(View.GONE);
                notice.setVisibility(View.VISIBLE);
            }
        });

        ambilsendiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRbDisable(5);
                kurirs = "null";
                getOngkir(kurirs, "null");
                recyclerView.setVisibility(View.GONE);
                notice.setVisibility(View.GONE);
            }
        });
    }

    private void initRbDisable(int i) {
        if (i == 1) {
            kurir = "jne";
            jne.setChecked(true);
            pos.setChecked(false);
            tiki.setChecked(false);
            antarpemili.setChecked(false);
            ambilsendiri.setChecked(false);

        } else if (i == 2) {
            kurir = "pos";
            jne.setChecked(false);
            pos.setChecked(true);
            tiki.setChecked(false);
            antarpemili.setChecked(false);
            ambilsendiri.setChecked(false);


        } else if (i == 3) {
            kurir = "tiki";
            jne.setChecked(false);
            pos.setChecked(false);
            tiki.setChecked(true);
            antarpemili.setChecked(false);
            ambilsendiri.setChecked(false);


        } else if (i == 4) {
            kurir = "antar pemilik";
            jne.setChecked(false);
            pos.setChecked(false);
            tiki.setChecked(false);
            antarpemili.setChecked(true);
            ambilsendiri.setChecked(false);
            titleOngkir.setText("Barang dikirim oleh pemilik");
            biayaOngkir.setText("Rp.0");
            totalOngkir.setText("Rp.0");
            tarif = "0";
            titles = null;
            checkTotal();

        } else {
            kurir = "ambil sendiri";
            jne.setChecked(false);
            pos.setChecked(false);
            tiki.setChecked(false);
            antarpemili.setChecked(false);
            ambilsendiri.setChecked(true);
            titleOngkir.setText("Tidak Menggunakan Jasa Pengiriman");
            biayaOngkir.setText("Rp.0");
            totalOngkir.setText("Rp.0");
            tarif = "0";
            titles = null;
            checkTotal();

        }
    }

    private void checkBerat() {
        int berats = 0;
        if (dataCarts.size() > 0) {
            for (int i = 0; i < dataCarts.size(); i++) {
                berats += (Integer.parseInt(dataCarts.get(i).getBerat()));
            }
            berat = String.valueOf(berats);
            Log.d(TAG, "checkBerat: " + berat);
            beratOngkir.setText(berat + " Gram");
        }
    }


    private void checkTotal() {
        int total = 0;
        if (dataCarts.size() > 0) {

            for (int i = 0; i < dataCarts.size(); i++) {
                if (dataCarts.get(i).getTipe().equals("1")) {
                    total += (Integer.parseInt(dataCarts.get(i).getTotal()));
                } else {
                    total += (Integer.parseInt(dataCarts.get(i).getHargaJual()));
                }
            }


            int admin = (total * 5) / 100;
            String sub = String.valueOf(admin).substring(String.valueOf(admin).length() - 3);
            if (sub.equals("000")) {

            }else if (Integer.valueOf(sub) < 1000){
                admin = (admin - Integer.valueOf(sub) + 1000);
            }

            biaya_Admin = String.valueOf(admin);
            biayaAdmin.setText(FormatRp.getFomat(biaya_Admin));

            kodeunik = invoices.substring(invoices.length() - 3);

            Log.d(TAG, "Sub String: " + admin);
            Log.d(TAG, "kode Unuk: " + kodeunik);

            harga = String.valueOf(total );
            int harga2 = total+admin;
            totalBayar.setText(FormatRp.getFomat(String.valueOf(harga2)));
            TotalAkhriCheckOut = harga + admin;
            Log.d(TAG, "checkTotal: " + TotalAkhriCheckOut);

        }
    }

    private void checkOut() {

        String id_pembeli = Prefs.getString(SharedPreff.getIdKonsumen(), null);

        Type type = new TypeToken<List<DataItemCart>>() {
        }.getType();
        List<DataItemCart> data = dataCarts;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(data, type);

        Log.d(TAG, "checkOut: " + json);


        Call<ResponseCheckOut> checkOut = InitRetrofit.getInstance().ceckOut(
                id_pembeli, TotalAkhriCheckOut, kurir, titles, tarif, alamat_id, biaya_Admin, kodeunik, json);
        checkOut.enqueue(new Callback<ResponseCheckOut>() {
            @Override
            public void onResponse(Call<ResponseCheckOut> call, Response<ResponseCheckOut> response) {

                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: success");
                    if (response.body().isStatus()) {
                        Log.d(TAG, "onResponse: ");
                        // Toast.makeText(context, "tung tara tung", Toast.LENGTH_SHORT).show();
                        deleteCart();
                        finish();
                        Intent i = new Intent(BayarActivity.this, SuccesPesanActivity.class);

                        i.putExtra("invoice", invoices);
                        i.putExtra("kode_unik", kodeunik);
                        i.putExtra("total", TotalAkhriCheckOut);
                        startActivity(i);


                    }
                } else {
                    Toast.makeText(context, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckOut> call, Throwable t) {
                Toast.makeText(context, "GAGAL UPLOAD " + t.getMessage(), Toast.LENGTH_SHORT).show();

                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
            }
        });
    }


    private void getOngkir(String kurir, String tujuan) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.rajaongkir.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        final String key, asal;
        key = "81368a975e6d4bcdaa16ec158ea419cf";
        asal = "42";
        // tujuan = Prefs.getString(SharedPreff.getIdKota(), null);

        Call<ResponseRajaOngkir> ongkir = service.getOngkir(key, asal, tujuan, berat, kurir);

        ongkir.enqueue(new Callback<ResponseRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseRajaOngkir> call, Response<ResponseRajaOngkir> response) {

                ResponseRajaOngkir res = response.body();

                if (!response.isSuccessful()) {
                    //Toast.makeText(context, "GAGAL GET", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d(TAG, "onResponse: " + res.getRajaongkir().getResults());

                List<ResultsItem> item = res.getRajaongkir().getResults();
                List<CostsItem> cost = new ArrayList<>();
                List<CostItem> cos = new ArrayList<>();

                for (int i = 0; i < item.size(); i++) {
                    //Toast.makeText(context, item.get(i).getName(), Toast.LENGTH_SHORT).show();
                    cost = item.get(i).getCosts();
                }
                for (int j = 0; j < cost.size(); j++) {
                    cos = cost.get(j).getCost();
                }

                adapter = new RajaOngkir_adapter(context, cost, cos);
                adapterclik();

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRajaOngkir> call, Throwable t) {
                Toast.makeText(context, "Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void adapterclik() {
        adapter.setOnclickini(new RajaOngkir_adapter.oClickini() {
            @Override
            public void onclickini(String title, String value, String estimasi) {
                titleOngkir.setText(title);
                biayaOngkir.setText(FormatRp.getFomat(value));
                titles = title;
                tarif = value;

                totalOngkir.setText(FormatRp.getFomat(value));

                if (totalOngkir.getText().toString() != "Rp.0") {
                    int totalOngkir = Integer.valueOf(value);
                    int totalCheckout = Integer.valueOf(harga);

                    int totalbayar = totalOngkir+totalCheckout;

                    int admin = (totalbayar * 5) / 100;
                    String sub = String.valueOf(admin).substring(String.valueOf(admin).length() - 3);
                    if (sub.equals("000")) {

                    }else if (Integer.valueOf(sub) < 1000){
                        admin = (admin - Integer.valueOf(sub) + 1000);
                    }

                    biaya_Admin = String.valueOf(admin);
                    biayaAdmin.setText(FormatRp.getFomat(biaya_Admin));


                    totalBayar.setText(FormatRp.getFomat(String.valueOf(totalCheckout + totalOngkir +admin)));
                    TotalAkhriCheckOut = String.valueOf(totalCheckout + totalOngkir+admin);
                    Log.d(TAG, "onclickini 1: " + TotalAkhriCheckOut);
                    Log.d(TAG, "onclickini 2: "+totalbayar);
                }

            }
        });
    }

    private void deleteCart() {
        Call<ResponseCart> delete = InitRetrofit.getInstance().deleteCart(Prefs.getString(SharedPreff.getIdKonsumen(), null));

        delete.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {

            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //  progressDialog.cancel();
    }
}


