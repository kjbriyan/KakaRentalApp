package id.kakarental.store.Adapter;

import android.content.Context;
import android.content.Intent;
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

import id.kakarental.store.Acitivity.DetailIklanActivity;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.dasboard.DataIklan;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;

public class Dasboard_iklan_Adapter extends RecyclerView.Adapter<Dasboard_iklan_Adapter.ViewHolder> {

    private static final String TAG = "Dasboard_Adapter";
    private static final String PRODUK_EXTRA_INTENT = "iklan";

    private Context context;
    private List<DataIklan> item;


    public Dasboard_iklan_Adapter(Context context, List<DataIklan> item) {
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
        private TextView tv_harga, nama_Produk, status, diskon;
        // public DataMobil item;
        View view;

        public ViewHolder(@NonNull View v, final Context context) {
            super(v);

            gambar_prod = v.findViewById(R.id.img_ProdThubmnail);
            nama_Produk = v.findViewById(R.id.tv_namaProduk);
            status = v.findViewById(R.id.tv_status);
            tv_harga = v.findViewById(R.id.tv_hargaJual);
            diskon = v.findViewById(R.id.tv_hargaDiskon);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    String produk = gson.toJson(item.get(getAdapterPosition()));

                    Intent i = new Intent(view.getContext(), DetailIklanActivity.class);
                    i.putExtra("tipe", "iklan");
                    i.putExtra(PRODUK_EXTRA_INTENT, produk);
                    view.getContext().startActivity(i);
                }
            });

        }

        public void bindData(DataIklan i) {


            Picasso.get().load(InitRetrofit.getIMG_URL() + i.getGambar())
                    .placeholder(R.drawable.placeholder_fail)
                    .error(R.drawable.placeholder_fail)
                    .into(gambar_prod);
            nama_Produk.setText(i.getNamaProduk());
            // tv_harga.setText("Rp." + String.format("%,.2f", Double.parseDouble(i.getHargaBeli())));
            tv_harga.setText(FormatRp.getFomat(i.getHarga()));
            status.setText("BARANG IKLAN");
            diskon.setVisibility(View.GONE);
            status.setBackgroundResource(R.color.green);

//            if (i.getStatus().equals("1")) {
//                status.setText("TERSEDIA");
//                status.setBackgroundResource(R.color.green);
//            } else {
//                status.setText("DISEWA");
//                status.setBackgroundResource(R.color.red);
//            }


        }

    }

    public static String getDetail() {
        return PRODUK_EXTRA_INTENT;
    }
}
