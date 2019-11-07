package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import id.kakarental.store.Adapter.ListDetailStatusTransaskAdapter;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.alamat.DataAlamat;
import id.kakarental.store.Model.alamat.ResponseAlamat;
import id.kakarental.store.Model.rekening.DataRekening;
import id.kakarental.store.Model.rekening.ResponseRekening;
import id.kakarental.store.Model.status_transaksi.DataDetailStatusTransaksi;
import id.kakarental.store.Model.status_transaksi.ListItemDetailStatusTransaksi;
import id.kakarental.store.Model.status_transaksi.ResponseKonfirmasi;
import id.kakarental.store.Model.status_transaksi.ResponseStatusDetailListTransaksi;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailStatusActivity extends AppCompatActivity {
    private static final String TAG = "DetailStatusActivity";

    private TextView kodeTrans, nama, noTlp, provinsi, kota, alamat;
    private TextView totalberat, totalongkir, subtotal,
            totaltagihan, kurir, service,
            status, rek1, rek2, btn_cetak,
            konfirm_pel, btn_konfirm;
    private Context mContext;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    ImageView back;
    NestedScrollView container;
    ProgressDialog dialog;
    String konfirmsiPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_status);

        mContext = this;
        dialog = new ProgressDialog(mContext);
        dialog.setCancelable(false);
        dialog.setMessage("Loading . . .");

        konfirmsiPembayaran = getIntent().getStringExtra("konfirm");

        initUi();
        // setValue();
        initevent();

    }

    private void initUi() {
        back = findViewById(R.id.iv_backstatus);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        container = findViewById(R.id.container_detail);
        kodeTrans = findViewById(R.id.tv_kodeTransaksi);
        konfirm_pel = findViewById(R.id.tv_konfirmPel);
        btn_konfirm = findViewById(R.id.tv_keterangan);
        nama = findViewById(R.id.tv_nama);
        noTlp = findViewById(R.id.tv_noHp);
        alamat = findViewById(R.id.tv_alamat);
        kota = findViewById(R.id.tv_kota);
        provinsi = findViewById(R.id.tv_provinsi);
        btn_cetak = findViewById(R.id.tv_cetakInvoice);

        rek1 = findViewById(R.id.tv_rekTujuan1);
        rek2 = findViewById(R.id.tv_rekTujuan2);

        recyclerView = findViewById(R.id.rv_listStatus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        totalberat = findViewById(R.id.tv_totalBerat);
        totalongkir = findViewById(R.id.tv_totalOngkir);
        subtotal = findViewById(R.id.tv_subTotal);
        totaltagihan = findViewById(R.id.tv_totalTagihan);
        kurir = findViewById(R.id.tv_kurir);
        service = findViewById(R.id.tv_service);
        status = findViewById(R.id.tv_status);

        progressBar = findViewById(R.id.progressBar);
        Sprite circle = new Circle();
        progressBar.setIndeterminateDrawable(circle);


    }

    private void initevent() {

        container.setVisibility(View.GONE);

        final String kodeTransaksi = getIntent().getStringExtra("kode_trans");
        String id_penjualan = getIntent().getStringExtra("id_penjualan");
        String id_alamat = getIntent().getStringExtra("alamat_id");

        kodeTrans.setText(kodeTransaksi);
        getDetailStatus(kodeTransaksi, id_penjualan);
        setAlamat(id_alamat);
        getRek();
       // final String Url = "https://lagiviral.web.id/produk/print_invoice/" + kodeTransaksi;
        final String Url = "http://"+ Prefs.getString(SharedPreff.getIP(), null)+ "/kakarenv1/produk/print_invoice/" + kodeTransaksi;
        btn_cetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Url));
                startActivity(i);
            }
        });

        btn_konfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                konfirmTrans(kodeTransaksi);
                Log.d(TAG, "onClick: " + kodeTransaksi);
            }
        });



    }

    private void setAlamat(String id_alamat) {
        Call<ResponseAlamat> getAlamt = InitRetrofit.getInstance().getDetaiAlamat(id_alamat);
        getAlamt.enqueue(new Callback<ResponseAlamat>() {
            @Override
            public void onResponse(Call<ResponseAlamat> call, Response<ResponseAlamat> response) {
                if (response.isSuccessful()) {

                    DataAlamat data = response.body().getData();
                    nama.setText(data.getAtasNama());
                    noTlp.setText(data.getNoHp());
                    provinsi.setText(data.getNamaProvinsi());
                    kota.setText(data.getNamaKota());
                    alamat.setText(data.getAlamatLengkap());

                }
            }

            @Override
            public void onFailure(Call<ResponseAlamat> call, Throwable t) {

            }
        });
    }


    private void getDetailStatus(String kodeTransaksi, String id_penjualan) {

        Call<ResponseStatusDetailListTransaksi> getDetailStatus = InitRetrofit.getInstance().getDetailTransaksi(kodeTransaksi, id_penjualan);

        getDetailStatus.enqueue(new Callback<ResponseStatusDetailListTransaksi>() {
            @Override
            public void onResponse(Call<ResponseStatusDetailListTransaksi> call, Response<ResponseStatusDetailListTransaksi> response) {

                ResponseStatusDetailListTransaksi res = response.body();

                if (!response.isSuccessful()) {
                    Toast.makeText(DetailStatusActivity.this, "ERROR" + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                DataDetailStatusTransaksi data = res.getData();
                List<ListItemDetailStatusTransaksi> listStatus = res.getList();
                ListDetailStatusTransaskAdapter adapter = new ListDetailStatusTransaskAdapter(listStatus, mContext);
                recyclerView.setAdapter(adapter);
                initdata(data);
            }

            @Override
            public void onFailure(Call<ResponseStatusDetailListTransaksi> call, Throwable t) {
                Toast.makeText(DetailStatusActivity.this, "ERROR" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initdata(DataDetailStatusTransaksi data) {
        int jumlah = 0;
        String statuses = data.getProses();

        totalberat.setText(data.getTotalBerat() + " Gram");
        totalongkir.setText(FormatRp.getFomat(data.getOngkir()));
        if (data.getTotalSewa() == "0") {
            jumlah = (Integer.valueOf(data.getTotalSewa()) + 0);
        } else {
            jumlah = (Integer.valueOf(data.getTotalSewa()) + (Integer.valueOf(data.getTotalBeli())));
        }

        subtotal.setText(FormatRp.getFomat(String.valueOf(jumlah)));
        totaltagihan.setText(FormatRp.getFomat(getTotalTagihan(data.getOngkir(), String.valueOf(jumlah))));
        kurir.setText(data.getKurir());
        service.setText(data.getService());
        String resi = getIntent().getStringExtra("resi");

        Log.d(TAG, "initdata: " + kurir);

        if (statuses.equals("0")) {
            status.setText("Dibatalkan");
            status.setBackgroundResource(R.drawable.bg_tv_pending);
        } else if (statuses.equals("1")) {
            status.setText("Menunggu Pembayaran");
            status.setBackgroundResource(R.drawable.bg_tv_proses);
        } else if (statuses.equals("2")) {
            status.setText("Pembayaran Sedang Diverifikasi");
            status.setBackgroundResource(R.drawable.bg_tv_konfirmasi);
        } else if (statuses.equals("3")) {
            status.setText("Pembayaran Diterima");
            status.setBackgroundResource(R.drawable.bg_tv_packing);
        } else if (data.getKurir().equals("ambil sendiri") && statuses.equals("4")) {
            status.setText("Barang Belum di Ambil");
            status.setBackgroundResource(R.drawable.bg_tv_packing);
        } else if (data.getKurir().equals("ambil sendiri") && statuses.equals("5")) {
            status.setText("Barang Sudah di Ambil");
            status.setBackgroundResource(R.drawable.bg_tv_packing);
        } else if (data.getKurir().equals("antar pemilik") && statuses.equals("4")) {
            status.setText("Menunggu Pengantaran");
            status.setBackgroundResource(R.drawable.bg_tv_packing);
        } else if (data.getKurir().equals("antar pemilik") && statuses.equals("5")) {
            status.setText("Barang Sudah di Antar");
            status.setBackgroundResource(R.drawable.bg_tv_packing);
        } else if (statuses.equals("4")) {
            status.setText("Barang Sedang di Packing");
            status.setBackgroundResource(R.drawable.bg_tv_packing);
        } else if (statuses.equals("5")) {
            status.setText("Resi : " + resi);
            status.setBackgroundResource(R.drawable.bg_tv_packing);
        }

        int statusss = Integer.valueOf(statuses);

        if (statusss==2) {
            btn_konfirm.setClickable(false);
            Log.d(TAG, "onResponse: " + konfirmsiPembayaran);
            btn_konfirm.setBackgroundColor(Color.RED);
            konfirm_pel.setVisibility(View.VISIBLE);
            konfirm_pel.setText("Anda telah melakukan konfirmasi pembayaran pada pesanan ini, " +
                    "silahkan tunggu sampai admin telah memverivikasi pembayaran anda. \n Maks 3x24 hari.");
        } else if (statusss>2){
            btn_konfirm.setClickable(false);
            Log.d(TAG, "onResponse: " + konfirmsiPembayaran);
            btn_konfirm.setBackgroundColor(Color.RED);
            konfirm_pel.setVisibility(View.VISIBLE);
            konfirm_pel.setText("Pembayaran anda telah di terima");
        }else {
            konfirm_pel.setVisibility(View.GONE);
        }

    }

    private void getRek() {

        Call<ResponseRekening> rek = InitRetrofit.getInstance().getRek();

        rek.enqueue(new Callback<ResponseRekening>() {
            @Override
            public void onResponse(Call<ResponseRekening> call, Response<ResponseRekening> response) {
                if (response.isSuccessful()) {

                    List<DataRekening> rek = response.body().getData();

                    for (int i = 0; i < rek.size(); i++) {
                        rek1.setText(rek.get(0).getNamaBank() + " " + rek.get(0).getNoRekening() + " A/N " + rek.get(0).getPemilikRekening());
                        rek2.setText(rek.get(1).getNamaBank() + " " + rek.get(1).getNoRekening() + " A/N " + rek.get(1).getPemilikRekening());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRekening> call, Throwable t) {

            }
        });
    }

    private void konfirmTrans(String kodeTrans) {
        dialog.show();
        Call<ResponseKonfirmasi> konfirm = InitRetrofit.getInstance().konfirm(kodeTrans);
        konfirm.enqueue(new Callback<ResponseKonfirmasi>() {
            @Override
            public void onResponse(Call<ResponseKonfirmasi> call, Response<ResponseKonfirmasi> response) {

                if (response.isSuccessful()) {
                    dialog.hide();
                    konfirmsiPembayaran = "1";
                    status.setText("Menunggu Konfirmasi Pembayaran");
                    status.setBackgroundResource(R.drawable.bg_tv_konfirmasi);

                    if (konfirmsiPembayaran.equals("1")) {
                        btn_konfirm.setClickable(false);
                        Log.d(TAG, "onResponse: " + konfirmsiPembayaran);
                        btn_konfirm.setBackgroundColor(Color.RED);
                        konfirm_pel.setVisibility(View.VISIBLE);
                        konfirm_pel.setText("Anda telah melakukan konfirmasi pembayaran pada pesanan ini, " +
                                "silahkan tunggu sampai admin telah memverivikasi pembayaran anda. \n Maks 3x24 jam.");
                    } else {
                        konfirm_pel.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseKonfirmasi> call, Throwable t) {
                dialog.hide();
            }
        });
    }

    private String getTotalTagihan(String ongkir, String subtotal) {

        Double total = (Double.parseDouble(ongkir) + Double.parseDouble(subtotal));

        String totalTagihan = String.valueOf(total);

        return totalTagihan;

    }


}
