package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import id.kakarental.store.Adapter.CartAdapter;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.CartModel.ResponseDelete;
import id.kakarental.store.Model.viewmodel.CartViewModel;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "Fragment_Cart.extra";

    private static final String TAG = "KeranjangActivity";
    private RecyclerView rv_cart;
    private ConstraintLayout container;
    private TextView tv_total, notice;
    private Button btn_check_out;
    private List<DataItemCart> dataCarts;
    private ImageView back;
    private CartAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog dialog;
    int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Mohon Tunggu");
        dialog.setCancelable(false);

        rv_cart = findViewById(R.id.rv_cart);


        getCart();
        initUi();
        hideCartList();
    }

    private void initUi() {

        back = findViewById(R.id.img_back);
        tv_total = findViewById(R.id.tv_totalCart);
        btn_check_out = findViewById(R.id.btn_checkOutChart);
        container = findViewById(R.id.container_cart);
        notice = findViewById(R.id.tv_noticeKosong);
        notice.setVisibility(View.GONE);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh_Cart);



        buttonClick();
        initRefresh();

    }

    private void buttonClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });

        btn_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.containerSecondary, new Fragment_Dasboard())
//                        .addToBackStack(null)
//                        .commit();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String data = gson.toJson(dataCarts);
                Log.d(TAG, "onClick: " + data);
                Intent i = new Intent(KeranjangActivity.this, BayarActivity.class);
                i.putExtra(EXTRA_DATA, data);
                startActivity(i);

            }
        });
    }

    private void initRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCart();
            }
        });

    }

    private void getCart() {
        dialog.show();
        String id_pelanggan = Prefs.getString(SharedPreff.getIdKonsumen(), null);
        Call<ResponseCart> cart = InitRetrofit.getInstance().getCart(id_pelanggan);

        cart.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                dialog.hide();
                ResponseCart res = response.body();
                if (!response.isSuccessful()) {
                    swipeRefreshLayout.setRefreshing(false);
                    // Toast.makeText(getContext(), "Error " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                } else if (res.isStatus()) {
                    btn_check_out.setEnabled(true);
                    ShowCartList();
                    dataCarts = res.getData();
                  size = dataCarts.size();
                    initRecycle(dataCarts);
                    getCartListData();
                    swipeRefreshLayout.setRefreshing(false);
                    initUi();
                    //  Toast.makeText(getContext(), "refreshed " + response.message(), Toast.LENGTH_SHORT).show();

                } else {
                    dialog.hide();
                    notice.setVisibility(View.VISIBLE);
                    hideCartList();
                    size = 0;
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                btn_check_out.setEnabled(false);
                dialog.hide();
                hideCartList();
                Snackbar.make(container, "Tidak ada koneksi", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Refresh", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getCart();
                            }
                        }).show();
            }
        });
    }

    private void initRecycle(List<DataItemCart> dataCarts) {

        rv_cart.setLayoutManager(new LinearLayoutManager(this));
        rv_cart.setHasFixedSize(true);
        adapter = new CartAdapter(dataCarts, this);
        rv_cart.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnClickListener(new CartAdapter.OnclickListener() {
            @Override
            public void onClickDelete(int position, String idCart) {
                deleteItemCart(position, idCart);
            }
        });
    }

    private void ShowCartList() {
        rv_cart.setVisibility(View.VISIBLE);
        container.setVisibility(View.VISIBLE);
        notice.setVisibility(View.GONE);
    }

    public void hideCartList() {
        rv_cart.setVisibility(View.GONE);
        container.setVisibility(View.GONE);
        notice.setVisibility(View.VISIBLE);
    }

    private void getCartListData() {
        int total = 0;
        if (dataCarts.size() > 0) {

            for (int i = 0; i < dataCarts.size(); i++) {
                if (dataCarts.get(i).getTipe().equals("1")) {
                    total += (Integer.parseInt(dataCarts.get(i).getTotal()));
                } else {
                    total += (Integer.parseInt(dataCarts.get(i).getHargaJual()));
                }
            }
            String harga = String.valueOf(total);
            String totalharga = "Rp." + String.format("%,.2f", Double.parseDouble(harga)).toString();
            tv_total.setText(totalharga);
        } else {
            rv_cart.setVisibility(View.GONE);
            container.setVisibility(View.GONE);
            notice.setVisibility(View.VISIBLE);
        }
    }

    public void deleteItemCart(int position, String id) {
        dataCarts.remove(position);
        adapter.notifyItemRemoved(position);

        deleteCart(id);
    }

    private void deleteCart(String id){

        dialog.show();

        Call<ResponseDelete> delete = InitRetrofit.getInstance().deleteItemCart(id);

        delete.enqueue(new Callback<ResponseDelete>() {
            @Override
            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                getCart();
                dialog.hide();
            }

            @Override
            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                dialog.hide();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCart();
    }
}
