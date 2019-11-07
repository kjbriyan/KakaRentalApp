package id.kakarental.store.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import id.kakarental.store.Acitivity.DetailJualActivity;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.dasboard.DataDasboardJual;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;


public class Dasboard_Jual_Adapter extends RecyclerView.Adapter<Dasboard_Jual_Adapter.ViewHolder> {

    private static final String TAG = "Dasboard_Adapter";
    private static final String PRODUK_EXTRA_INTENT = "produk";

    private Context context;
    private List<DataDasboardJual> item;


    public Dasboard_Jual_Adapter(Context context, List<DataDasboardJual> item) {
        this.context = context;
        this.item = item;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_produk_jual, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.bindData(item.get(i));
        //   viewHolder.toDetail(item.get(i),context);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView gambar_prod;
        private TextView tv_harga, nama_Produk, status, tv_diskon;
        // public DataMobil item;
        View view;

        public ViewHolder(@NonNull View v, final Context context) {
            super(v);

            gambar_prod = v.findViewById(R.id.img_ProdThubmnail);
            nama_Produk = v.findViewById(R.id.tv_namaProduk);
            status = v.findViewById(R.id.tv_status);
            tv_harga = v.findViewById(R.id.tv_hargaJual);
            tv_harga.setPaintFlags(tv_harga.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv_diskon = v.findViewById(R.id.tv_hargaDiskon);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    String produk = gson.toJson(item.get(getAdapterPosition()));

                    Intent i = new Intent(view.getContext(), DetailJualActivity.class);
                    i.putExtra("tipe", "jual");
                    i.putExtra(PRODUK_EXTRA_INTENT, produk);
                    view.getContext().startActivity(i);
                }
            });

        }

        public void bindData(DataDasboardJual i) {


            Picasso.get().load(InitRetrofit.getIMG_URL() + i.getGambar())
                    .placeholder(R.drawable.placeholder_fail)
                    .error(R.drawable.placeholder_fail)
                    .into(gambar_prod);
            nama_Produk.setText(i.getNamaProduk());
            // tv_harga.setText("Rp." + String.format("%,.2f", Double.parseDouble(i.getHargaBeli())));
            if (!i.getDiskon().equals("0")) {
                tv_harga.setText(FormatRp.getFomat(i.getHargaKonsumen()));
            } else {
                tv_harga.setText(FormatRp.getFomat("0"));
            }

            String diskon = String.valueOf(Integer.parseInt(i.getHargaKonsumen()) - Integer.parseInt(i.getDiskon()));
            tv_diskon.setText(FormatRp.getFomat(diskon));

            status.setBackgroundResource(R.color.green);



            if (i.getStok()>0) {
                status.setText("TERSEDIA");
                status.setBackgroundResource(R.color.green);
            } else {
                status.setText("STOK HABIS");
                status.setBackgroundResource(R.color.red);
            }


        }

    }

    public static String getDetail() {
        return PRODUK_EXTRA_INTENT;
    }
}
