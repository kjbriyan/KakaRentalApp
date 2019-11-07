package id.kakarental.store.Acitivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.rekening.DataRekening;
import id.kakarental.store.Model.rekening.ResponseRekening;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccesPesanActivity extends AppCompatActivity {
    private static final String TAG = "SuccesPesanActivity";

    TextView invoice, kodeUnik ,total, btn_cetak, keterangan, selesai,rek1,rek2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes_pesan);

        initUi();
        getRek();
    }

    private void initUi() {

        invoice = findViewById(R.id.txt_invoice);
        kodeUnik = findViewById(R.id.tv_kodeUnik);
        total = findViewById(R.id.txt_Total);
        btn_cetak = findViewById(R.id.tv_cetakInvoice);
        keterangan = findViewById(R.id.tv_keterangan);
        rek1 = findViewById(R.id.tv_rekTujuan1);
        rek2 = findViewById(R.id.tv_rekTujuan2);
        selesai = findViewById(R.id.tv_selesai);

        initEvent();
    }

    private void initEvent() {

        final String inpoice = getIntent().getStringExtra("invoice");
        String kode_unik = getIntent().getStringExtra("kode_unik");
        String total_harga = getIntent().getStringExtra("total");
        String email = Prefs.getString(SharedPreff.getEMAIL(), null);
      //  final String Url = "https://lagiviral.web.id/produk/print_invoice/" + inpoice;
        final String Url = "http://"+ Prefs.getString(SharedPreff.getIP(), null)+ "/kakarenv1/produk/print_invoice/" + inpoice;

        invoice.setText("No Invoice Anda :" + inpoice);
        kodeUnik.setText("Kode Unik Transfer"+ FormatRp.getFomat(kode_unik));

        int totalKode = Integer.valueOf(total_harga)+ Integer.valueOf(kode_unik);

        total.setText("Total Belanja Anda " + FormatRp.getFomat(String.valueOf(totalKode)));
        btn_cetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Url));
                startActivity(i);
            }
        });

        keterangan.setText("Kami juga telah mengirimkan detail pesanan anda ke " + email +
                " Silahkan mentransferkan uang dengan total"+ FormatRp.getFomat(String.valueOf(totalKode)) +" ke salah satu pilihan " +
                "bank di bawah ini : ");

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getRek(){

        Call<ResponseRekening> rek = InitRetrofit.getInstance().getRek();

        rek.enqueue(new Callback<ResponseRekening>() {
            @Override
            public void onResponse(Call<ResponseRekening> call, Response<ResponseRekening> response) {
                if (response.isSuccessful()){

                    List<DataRekening> rek = response.body().getData();

                    for (int i = 0; i< rek.size(); i++){
                        rek1.setText(rek.get(0).getNamaBank()+" "+rek.get(0).getNoRekening()+" A/N "+ rek.get(0).getPemilikRekening());
                        rek2.setText(rek.get(1).getNamaBank()+" "+rek.get(1).getNoRekening()+" A/N "+ rek.get(1).getPemilikRekening());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRekening> call, Throwable t) {

            }
        });

    }
}
