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
import android.widget.Toast;

import java.util.List;

import id.kakarental.store.Acitivity.UbahAlamatActivity;
import id.kakarental.store.Model.DataItemAlamat;
import id.kakarental.store.R;

public class PilihAlamat_Adapter extends RecyclerView.Adapter<PilihAlamat_Adapter.ViewHolder> {

    Context mContext;
    List<DataItemAlamat> item;
    OnclickListener mListener;

    public interface OnclickListener {
        void onClick(DataItemAlamat alamat);
    }

    public void setOnclick(OnclickListener listener) {
        this.mListener = listener;
    }


    public PilihAlamat_Adapter(Context mContext, List<DataItemAlamat> item) {
        this.mContext = mContext;
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.design_list_alamat, viewGroup, false);

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

        TextView title, penerima,tlp,alamat,kab,prov,pos,edit_alamat;
        LinearLayout container;

        public ViewHolder(@NonNull View v) {
            super(v);
            title = v.findViewById(R.id.tv_title);
            penerima = v.findViewById(R.id.tv_penerima);
            tlp = v.findViewById(R.id.tv_noTlp);
            alamat = v.findViewById(R.id.tv_alamatlengkap);
            kab = v.findViewById(R.id.tv_Kab);
            prov = v.findViewById(R.id.tv_Prov);
            pos = v.findViewById(R.id.tv_kodePos);
            edit_alamat = v.findViewById(R.id.btn_editAlamat);
            container = v.findViewById(R.id.container);
        }

        public void bindItem(final DataItemAlamat item) {
            title.setText(item.getNamaAlamat());
            penerima.setText(item.getAtasNama());
            tlp.setText(item.getNoHp());
            alamat.setText(item.getAlamatLengkap());
            kab.setText(item.getNamaKota());
            prov.setText(item.getNamaProvinsi());
            pos.setVisibility(View.GONE);
            edit_alamat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), UbahAlamatActivity.class);
                    view.getContext().startActivity(i);
                }
            });
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(item);
                }
            });
        }
    }
}
