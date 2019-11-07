package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import id.kakarental.store.Adapter.StatusTransaksi_Adapter;
import id.kakarental.store.Model.status_transaksi.DataStatusTransaksi;
import id.kakarental.store.Model.status_transaksi.ResponseStatusTransaksi;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusPesananActivity extends AppCompatActivity {
    private static final String TAG = "StatusPesananActivity";

    private RecyclerView recyclerView;
    private StatusTransaksi_Adapter adapter;
    private Context mContext;
    private SwipeRefreshLayout refreshLayout;
    private Parcelable recyclerViewState;
    ImageView back;
    ProgressDialog dialog;
    TextView notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pesanan);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading . . . ");
        dialog.setCancelable(false);

        mContext = this;

        initUi();
        refreshLayout();
        initgetData();

    }

    private void initUi() {
        back = findViewById( R.id.iv_backstatus );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        notice = findViewById(R.id.tv_noticeKosong);
        refreshLayout = findViewById(R.id.swipeRefresh_Status);
        recyclerView = findViewById(R.id.rv_statusPesanan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Save state
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        // Restore state

    }

    private void refreshLayout() {

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initgetData();
                recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            }
        });
    }

    private void initgetData() {
        dialog.show();
        String id = Prefs.getString(SharedPreff.getIdKonsumen(), null);

        Call<ResponseStatusTransaksi> transaksiCall = InitRetrofit.getInstance().getTransaksi(id);

        transaksiCall.enqueue(new Callback<ResponseStatusTransaksi>() {
            @Override
            public void onResponse(Call<ResponseStatusTransaksi> call, Response<ResponseStatusTransaksi> response) {
                refreshLayout.setRefreshing(false);
                ResponseStatusTransaksi res = response.body();
                dialog.hide();

                if (!response.isSuccessful()) {
                    Toast.makeText(StatusPesananActivity.this, "Error" + response.message(), Toast.LENGTH_SHORT).show();
                    return;

                }
                Boolean status = res.isStatus();

                if (status) {
                    List<DataStatusTransaksi> transaksi = res.getData();
                    adapter = new StatusTransaksi_Adapter(transaksi, mContext);
                    recyclerView.setAdapter(adapter);
                    notice.setVisibility(View.GONE);
                } else {
                  //  Toast.makeText(mContext, res.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseStatusTransaksi> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                dialog.hide();
                Toast.makeText(mContext, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLayout();
        initgetData();
        Log.d(TAG, "onResume: ");
    }
}
