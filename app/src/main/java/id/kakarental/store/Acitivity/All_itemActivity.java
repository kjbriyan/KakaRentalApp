package id.kakarental.store.Acitivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Adapter.Dasboard_Jual_Adapter;
import id.kakarental.store.Adapter.Dasboard_Sewa_Adapter;
import id.kakarental.store.Adapter.Dasboard_iklan_Adapter;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.dasboard.DataDasboardJual;
import id.kakarental.store.Model.dasboard.DataIDasboardSewa;
import id.kakarental.store.Model.dasboard.DataIklan;
import id.kakarental.store.Model.dasboard.ResponseDasboard;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class All_itemActivity extends AppCompatActivity {

    private static final String TAG = "All_itemActivity";

    private RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;

    Context mContext;
    TextView badgeCart, tittle;
    ImageView back;
    ProgressBar progressBar;
    ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_iteml);

        mContext = this;

        initUi();
        initEvent();
    }


    private void initUi() {

        badgeCart = findViewById(R.id.tv_iconCartBadge_Detail);
        back = findViewById(R.id.iv_backsewa);
        tittle = findViewById(R.id.tv_tittle);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
       // progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite sprite = new ThreeBounce();
//        progressBar.setIndeterminateDrawable(sprite);

        rv = findViewById(R.id.rv_allProduct);

    }

    private void initEvent() {
        String judul = getIntent().getStringExtra("data");
        tittle.setText(judul);
        badgeCart.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));
        initDasboard();
        getCart();
    }

    private void getCart() {

        Call<ResponseCart> cart = InitRetrofit.getInstance().getCart(Prefs.getString(SharedPreff.getIdKonsumen(), null));

        cart.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {

                ResponseCart res = response.body();

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


    private void initDasboard() {


        Call<ResponseDasboard> jual = InitRetrofit.getInstance().getDasboard();

        jual.enqueue(new Callback<ResponseDasboard>() {
            @Override
            public void onResponse(Call<ResponseDasboard> call, Response<ResponseDasboard> response) {
                // swipeRefreshLayout.setRefreshing(false);

                ResponseDasboard res = response.body();
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Error : " + response.message() + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else if (res.isStatus()) {

                    String data = getIntent().getStringExtra("data");

                    if (data.equals("Jual")) {
                        List<DataDasboardJual> jual = res.getJual();

                        Dasboard_Jual_Adapter adapter = new Dasboard_Jual_Adapter(mContext, jual);
                        rv.setAdapter(adapter);
                    } else if (data.equals("Sewa")) {
                        List<DataIDasboardSewa> sewa = res.getSewa();
                        Dasboard_Sewa_Adapter adapter = new Dasboard_Sewa_Adapter(mContext, sewa);
                        rv.setAdapter(adapter);
                    } else {
                        List<DataIklan> iklan = res.getIklan();
                        Dasboard_iklan_Adapter adapter = new Dasboard_iklan_Adapter(mContext, iklan);
                        rv.setAdapter(adapter);
                    }

                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
//                    progressBar.setVisibility(View.GONE);
//                    nestedScrollView.setVisibility(View.VISIBLE);
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseDasboard> call, Throwable t) {

                initDasboard();

                // swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCart();
    }
}
