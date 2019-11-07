package id.kakarental.store.Acitivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Adapter.PilihNamaDaerah_adapter;
import id.kakarental.store.Model.registrasi.DataItem;
import id.kakarental.store.Model.registrasi.ResponseProvinsi;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihProvinsiActivity extends AppCompatActivity {

    PilihNamaDaerah_adapter adapter;
    RecyclerView rvprovs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pilih_provinsi );
        rvprovs = findViewById( R.id.rv_pilihprovinsi );

        getProvs();
    }

    private void getProvs(){
        Call<ResponseProvinsi> call = InitRetrofit.getInstance().getProv();
        call.enqueue( new Callback<ResponseProvinsi>() {
            @Override
            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
                final ResponseProvinsi res = response.body();
                if (res.isStatus()){
                    final List<DataItem> provinsi = res.getData();
                    Log.d( "PilihProvinsiActivty", "onResponse: "+provinsi.get( 0 ).getNamaProvinsi() );

                    adapter = new PilihNamaDaerah_adapter( PilihProvinsiActivity.this ,provinsi );
                    rvprovs.setAdapter( adapter );

                }else {
                    Toast.makeText( PilihProvinsiActivity.this, "datane ra enek", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
                Toast.makeText( PilihProvinsiActivity.this, "jaringan gagal", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
