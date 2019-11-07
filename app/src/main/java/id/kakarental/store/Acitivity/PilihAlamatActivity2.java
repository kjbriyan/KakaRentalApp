package id.kakarental.store.Acitivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pixplicity.easyprefs.library.Prefs;

import java.lang.reflect.Type;
import java.util.List;

import id.kakarental.store.Adapter.PilihAlamat_Adapter;
import id.kakarental.store.Adapter.TambahAlamat_Adapter;
import id.kakarental.store.Model.DataItemAlamat;
import id.kakarental.store.Model.ResponseAlamt;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import id.kakarental.store.Utils.Move;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PilihAlamatActivity2 extends AppCompatActivity {
    private static final String TAG = "PilihAlamatActivity2";
    RecyclerView recyclerView;
    List<DataItemAlamat> alamats;
    Context context;
    TambahAlamat_Adapter adapter;
    Button tbalamt;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pilih_alamat2 );
        context = this;
        initUi();
    }

    private void initUi() {
        recyclerView = findViewById(R.id.rv_alamat2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        getListAlamt();

        back = findViewById( R.id.img_backAlamat2 );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        tbalamt = findViewById( R.id.btn_tbAlamat2 );
        tbalamt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.move( PilihAlamatActivity2.this, TambahAlamatActivity.class );
            }
        } );
    }

    private void getListAlamt() {
        Type type = new TypeToken<List<DataItemAlamat>>() {
        }.getType();
        String json = getIntent().getStringExtra("data_alamat");
        Gson gson = new Gson();
        alamats = gson.fromJson(json, type);
        Log.d(TAG, "getListAlamt: " + json);
//        adapter = new PilihAlamat_Adapter(context, alamats);
//        recyclerView.setAdapter(adapter);
        getAlamat();
        //onClickAdapter();
    }

//    private void onClickAdapter() {
//        adapter.setOnclick(new PilihAlamat_Adapter.OnclickListener() {
//            @Override
//            public void onClick(DataItemAlamat alamat) {
//                DataItemAlamat alamats = alamat;
//                Gson gson = new Gson();
//                String data = gson.toJson(alamats);
//                Intent i = new Intent();
//                i.putExtra("data", data);
//                setResult(RESULT_OK, i);
//                finish();
//            }
//        });
//    }

    private void getAlamat() {

        Call<ResponseAlamt> call = InitRetrofit.getInstance().getAlamat( Prefs.getString( SharedPreff.getIdKonsumen(),null ) );
        call.enqueue( new Callback<ResponseAlamt>() {
            @Override
            public void onResponse(Call<ResponseAlamt> call, Response<ResponseAlamt> response) {
                ResponseAlamt res = response.body();
                if (res.isStatus()){
                    List <DataItemAlamat> almt = res.getData();
                    adapter = new TambahAlamat_Adapter( context, almt );
                    recyclerView.setAdapter( adapter );
                }else {
                    Toast.makeText( PilihAlamatActivity2.this, "Alamat belum ada mohon masukan alamat", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAlamt> call, Throwable t) {
                Toast.makeText( PilihAlamatActivity2.this, "gagal koneksi", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAlamat();
    }
}

