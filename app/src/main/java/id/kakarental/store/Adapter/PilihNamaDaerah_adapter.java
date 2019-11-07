package id.kakarental.store.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;



import id.kakarental.store.Model.registrasi.DataItem;
import id.kakarental.store.R;

public class PilihNamaDaerah_adapter  extends RecyclerView.Adapter<PilihNamaDaerah_adapter.ViewHolder> {

    Context mContext;
    List<DataItem> item;
    OnclickListener mListener;

    public interface OnclickListener {
        void onClick(DataItem alamat);
    }

    public void setOnclick(OnclickListener listener) {
        this.mListener = listener;
    }


    public PilihNamaDaerah_adapter(Context mContext, List<DataItem> item) {
        this.mContext = mContext;
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate( R.layout.design_alamat, viewGroup, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindItem(item.get(i));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView alamat;
        LinearLayout container;

        public ViewHolder(@NonNull View v) {
            super(v);

            alamat = v.findViewById(R.id.pilihanalamat);

            container = v.findViewById(R.id.container);
        }

        public void bindItem(final DataItem item) {

            alamat.setText(item.getNamaProvinsi());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(item);
                }
            });
        }
    }
}