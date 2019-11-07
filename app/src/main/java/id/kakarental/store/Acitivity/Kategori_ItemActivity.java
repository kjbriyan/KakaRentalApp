package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import id.kakarental.store.Adapter.Kategori_Jual_Adapter_ALL;
import id.kakarental.store.Adapter.Kategori_Sewa_Adapter_ALL;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.kategori.BeliItemKategori;
import id.kakarental.store.Model.kategori.ResponseKategori;
import id.kakarental.store.Model.kategori.SewaItemKategori;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kategori_ItemActivity extends AppCompatActivity {

    private static final String TAG = "All_itemActivity";

    private RecyclerView rvjual, rv_sewa;
    RecyclerView.LayoutManager layoutManager, layoutManager2;
    ProgressDialog dialog;
    Context mContext;
    TextView badgeCart;
    ImageView back;
    TextView tv_jual, tv_sewa;
    ImageView cart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_item);

        mContext = this;

        initUi();
        initEvent();
    }


    private void initUi() {
        cart = findViewById(R.id.img_cartToolbarDetail);
        badgeCart = findViewById(R.id.tv_iconCartBadge_Detail);
        back = findViewById(R.id.iv_backsewa);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading ...");

        String data = getIntent().getStringExtra("data");
        Toast.makeText(mContext, data, Toast.LENGTH_SHORT).show();

        tv_jual = findViewById(R.id.tv_notfoundJual);
        tv_sewa = findViewById(R.id.tv_notfoundSewa);

        rvjual = findViewById(R.id.rv_kategori_Beli);
        rv_sewa = findViewById(R.id.rv_kategori_Sewa);

    }

    private void initEvent() {


        badgeCart.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rvjual.setLayoutManager(layoutManager);
        rvjual.setHasFixedSize(true);

        layoutManager2 = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rv_sewa.setLayoutManager(layoutManager2);
        rv_sewa.setHasFixedSize(true);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Kategori_ItemActivity.this, KeranjangActivity.class);

                startActivity(i);
            }
        });

        // initKategori();
        getCart();
        initKategori();

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
                    int size = items.size();
                    setbadge(size);

                } else {
                    setbadge(0);
                }

            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                getCart();
                dialog.hide();
            }
        });
    }

    private void setbadge(int size) {
        if (size > 0) {
            badgeCart.setVisibility(View.VISIBLE);
            badgeCart.setText(Integer.toString(size));
        } else {
            badgeCart.setVisibility(View.GONE);
        }
    }


    private void initKategori() {
        String input = getIntent().getStringExtra("data");
        dialog.show();

        Call<ResponseKategori> jual = InitRetrofit.getInstance().getKategori(input);

        jual.enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                // swipeRefreshLayout.setRefreshing(false);
                dialog.hide();
                ResponseKategori res = response.body();
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Error : " + response.message() + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else if (res.isStatus()) {

                    if (!res.getBeli().isEmpty()) {
                        List<BeliItemKategori> jual = res.getBeli();
                        Kategori_Jual_Adapter_ALL adapter = new Kategori_Jual_Adapter_ALL(mContext, jual);
                        rvjual.setAdapter(adapter);

                        tv_jual.setVisibility(View.GONE);
                    } else {
                        tv_jual.setVisibility(View.VISIBLE);

                    }

                    if (!res.getSewa().isEmpty()) {
                        List<SewaItemKategori> sewa = res.getSewa();
                        Kategori_Sewa_Adapter_ALL adapter2 = new Kategori_Sewa_Adapter_ALL(mContext, sewa);
                        rv_sewa.setAdapter(adapter2);

                        tv_sewa.setVisibility(View.GONE);
                    } else {
                        tv_sewa.setVisibility(View.VISIBLE);
                    }

//                    if (data.equals("jual")) {
//                        List<BeliItemKategori> jual = res.getBeli();
//                        Kategori_Jual_Adapter_ALL adapter = new Kategori_Jual_Adapter_ALL(mContext, jual);
//                        rv.setAdapter(adapter);
//                    } else if (data.equals("sewa")) {
//                        List<SewaItemKategori> sewa = res.getSewa();
////                        Dasboard_Sewa_Adapter_ALL adapter = new Dasboard_Sewa_Adapter_ALL(mContext, sewa);
////                        rv.setAdapter(adapter);
//                    } else {
//
//                    }

//                    progressBar.setVisibility(View.GONE);
//                    nestedScrollView.setVisibility(View.VISIBLE);
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                initKategori();
                dialog.hide();
                // swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}
