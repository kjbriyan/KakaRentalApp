package id.kakarental.store.Acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import id.kakarental.store.Adapter.PilihAlamat_Adapter;
import id.kakarental.store.Model.DataItemAlamat;
import id.kakarental.store.R;

public class PilihAlamatActivity extends AppCompatActivity {
    private static final String TAG = "PilihAlamatActivity";
    RecyclerView recyclerView;
    List<DataItemAlamat> alamats;
    Context context;
    PilihAlamat_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_alamat);
        context = this;
        initUi();
    }

    private void initUi() {
        recyclerView = findViewById(R.id.rv_alamat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        getListAlamt();
    }

    private void getListAlamt() {
        Type type = new TypeToken<List<DataItemAlamat>>() {
        }.getType();
        String json = getIntent().getStringExtra("data_alamat");
        Gson gson = new Gson();
        alamats = gson.fromJson(json, type);
        Log.d(TAG, "getListAlamt: " + json);
        adapter = new PilihAlamat_Adapter(context, alamats);
        recyclerView.setAdapter(adapter);
        onClickAdapter();
    }

    private void onClickAdapter() {
        adapter.setOnclick(new PilihAlamat_Adapter.OnclickListener() {
            @Override
            public void onClick(DataItemAlamat alamat) {
                DataItemAlamat alamats = alamat;
                Gson gson = new Gson();
                String data = gson.toJson(alamats);
                Intent i = new Intent();
                i.putExtra("data", data);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
