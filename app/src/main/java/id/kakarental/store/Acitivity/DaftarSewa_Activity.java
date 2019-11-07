package id.kakarental.store.Acitivity;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import id.kakarental.store.Adapter.ListSewa_Adapter;
import id.kakarental.store.Model.list_sewa.DataItemSewa;
import id.kakarental.store.Model.list_sewa.ResponseListSewa;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarSewa_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private Context context;
    private ImageView img_back;
    private ConstraintLayout container;
    private ShimmerFrameLayout shimmerFrameLayout;
    private TextView notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_sewa_);
        context = this;

        initUi();

    }

    private void initUi() {

        recyclerView = findViewById(R.id.rv_listSewa);
        manager = new LinearLayoutManager(this);
        img_back = findViewById(R.id.iv_backstatus);
        container = findViewById(R.id.container);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        notice = findViewById(R.id.tv_noticeKosong);

        initEvent();
    }

    private void initEvent() {
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initGetSewa();


    }

    private void initGetSewa() {

        Call<ResponseListSewa> getSewa = InitRetrofit.getInstance().getSewa(Prefs.getString(SharedPreff.getIdKonsumen(), null));
        getSewa.enqueue(new Callback<ResponseListSewa>() {
            @Override
            public void onResponse(Call<ResponseListSewa> call, Response<ResponseListSewa> response) {
                ResponseListSewa res = response.body();

                if (!response.isSuccessful()) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                    return;
                } else if (res.isStatus()) {
                    List<DataItemSewa> item = res.getData();
                    ListSewa_Adapter adapter = new ListSewa_Adapter(item, context);
                    recyclerView.setAdapter(adapter);

                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    notice.setVisibility(View.GONE);

                } else {

                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<ResponseListSewa> call, Throwable t) {
                Snackbar.make(container, "Koneksi Bermasalah", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Refresh", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                initGetSewa();
                            }
                        }).show();
            }
        });
    }

    public void recreate() {
        recreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initGetSewa();
    }
}
