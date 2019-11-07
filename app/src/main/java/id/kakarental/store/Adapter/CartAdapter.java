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

import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    List<DataItemCart> data = new ArrayList<>();
    Context mContext;
    View view;
    OnclickListener mListener;

    public void setOnClickListener(OnclickListener listener) {
        mListener = listener;
    }

    public interface OnclickListener {
        void onClickDelete(int position, String idCart);
    }

    public CartAdapter(List<DataItemCart> data, Context context) {
        this.data = data;
        this.mContext = context;
        notifyDataSetChanged();
    }

    public void setData(List<DataItemCart> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_cart_adapter, viewGroup, false);

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
        private TextView tv_namaProduk, tv_harga, tv_durasiSewa, tv_status;
        private ImageView img_cartHolder, btn_deleteCart;
        private View view;

        public CartHolder(@NonNull View v) {
            super(v);

            tv_namaProduk = v.findViewById(R.id.tv_namaProdCart);
            tv_harga = v.findViewById(R.id.tv_hargaProdCart);
            tv_durasiSewa = v.findViewById(R.id.tv_durasiSewaCart);
            tv_status = v.findViewById(R.id.tv_statusCart);
            btn_deleteCart = v.findViewById(R.id.btn_deleteCart);
            img_cartHolder = v.findViewById(R.id.img_cartThumb);

            this.view = v;
        }

        public void bindItem(final DataItemCart cart) {


            tv_namaProduk.setText(cart.getNamaProduk());
            Picasso.get().load(InitRetrofit.getIMG_URL() + cart.getGambar()).into(img_cartHolder);

            if (cart.getTipe().equals("2")) {
                tv_status.setBackgroundResource(R.color.green);
                tv_status.setText("Beli");
                tv_harga.setText(FormatRp.getFomat(cart.getHargaJual()));
                tv_durasiSewa.setVisibility(View.GONE);
            } else {
                tv_status.setText("Sewa");
                tv_status.setBackgroundResource(R.color.orange);
                int durasi = ((Integer.valueOf(cart.getHargaSewa()))* (Integer.valueOf(cart.getDurasi())));
                tv_harga.setText(FormatRp.getFomat(String.valueOf(durasi)));
                tv_durasiSewa.setText("Durasi Sewa " + cart.getDurasi()+" "+ cart.getJenisSewa());
                tv_durasiSewa.setVisibility(View.VISIBLE);
            }

            btn_deleteCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        String idCart = cart.getIdPenjualanDetail();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onClickDelete(position, idCart);
                        }
                    }
                }
            });


        }
    }
}
