package id.kakarental.store.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.kakarental.store.Helper.FormatRp;

import id.kakarental.store.Model.status_transaksi.ListItemDetailStatusTransaksi;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;

public class ListDetailStatusTransaskAdapter extends RecyclerView.Adapter<ListDetailStatusTransaskAdapter.CartHolder> {

    List<ListItemDetailStatusTransaksi> data ;
    Context mContext;

    public ListDetailStatusTransaskAdapter(List<ListItemDetailStatusTransaksi> data, Context context) {
        this.data = data;
        this.mContext = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_bayar_layout, viewGroup, false);

        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder cartHolder, int i) {
        cartHolder.bindItem(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        private TextView tv_namaProduk, tv_harga, tv_durasiSewa,tv_status,berat;
        private ImageView img_cartHolder;

        public CartHolder(@NonNull View v) {
            super(v);

            tv_namaProduk = v.findViewById(R.id.tv_namaProdCart);
            tv_harga = v.findViewById(R.id.tv_hargaProdCart);
            tv_durasiSewa = v.findViewById(R.id.tv_durasiSewaCart);
            tv_status = v.findViewById(R.id.tv_statusCart);
            berat = v.findViewById(R.id.tv_beratDetail);
            img_cartHolder = v.findViewById(R.id.img_cartThumb);
        }

        public void bindItem(ListItemDetailStatusTransaksi data) {

                tv_namaProduk.setText(data.getNamaProduk());
                Picasso.get().load(InitRetrofit.getIMG_URL() +data.getGambar()).into(img_cartHolder);
                berat.setText(data.getBerat()+ " Gram");
                tv_harga.setText(FormatRp.getFomat(data.getHargaJual()));

            if (data.getTipe().equals("2")){
                tv_status.setBackgroundResource(R.color.green);
                tv_status.setText("Beli");
                tv_harga.setText(FormatRp.getFomat(data.getHargaJual()));
                tv_durasiSewa.setVisibility(View.GONE);
            }else{
                tv_status.setText("Sewa");
                tv_status.setBackgroundResource(R.color.orange);
                int durasi = ((Integer.valueOf(data.getHargaSewa()))* (Integer.valueOf(data.getDurasi())));
                tv_harga.setText(FormatRp.getFomat(String.valueOf(durasi)));
                tv_durasiSewa.setText("Durasi Sewa "+data.getDurasi()+" "+ data.getJenisSewa());
                tv_durasiSewa.setVisibility(View.VISIBLE);
            }
        }
    }
}
