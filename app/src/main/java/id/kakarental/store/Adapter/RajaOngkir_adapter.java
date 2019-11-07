package id.kakarental.store.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.raja_ongkir.CostItem;
import id.kakarental.store.Model.raja_ongkir.CostsItem;
import id.kakarental.store.R;

public class RajaOngkir_adapter extends RecyclerView.Adapter<RajaOngkir_adapter.viewHolder> {
    Context context;
    List<CostsItem> deskrip;
    List<CostItem> cost;
    private oClickini clickIni;
    View mView;

    public void setOnclickini(oClickini clickIni) {
        this.clickIni = clickIni;
    }

    public RajaOngkir_adapter(Context context, List<CostsItem> deskrip, List<CostItem> cost) {
        this.context = context;
        this.deskrip = deskrip;
        this.cost = cost;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_ongkir_bayar, viewGroup, false);

        return new viewHolder(view, deskrip);
    }


    public interface oClickini {
        void onclickini(String title, String value, String estimasi);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, final int i) {
        final String title;
        String value = null;
        String estimasi = null;

        if (deskrip.get(i).getDescription().equals("Paket Kilat Khusus")||
        deskrip.get(i).getDescription().equals("Express Next Day Barang")){
            viewHolder.hari.setVisibility(View.GONE);
        }

        viewHolder.desc.setText(deskrip.get(i).getDescription());

        title = deskrip.get(i).getDescription();

        List<CostItem> item = deskrip.get(i).getCost();
        for (int j = 0; j < item.size(); j++) {
            viewHolder.tarif.setText(FormatRp.getFomat(String.valueOf(item.get(j).getValue())));
            viewHolder.estiamsi.setText(item.get(j).getEtd());
            value= String.valueOf(item.get(j).getValue());
            estimasi = item.get(j).getEtd();
        }


        final String values = value;
        final String estimasis = estimasi;
        viewHolder.pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickIni.onclickini(title,values, estimasis);

            }
        });

    }

    @Override
    public int getItemCount() {
        return deskrip.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView desc, tarif, estiamsi,hari;
        Button pilih;

        public viewHolder(@NonNull View v, final List<CostsItem> item) {
            super(v);

            v.setClickable(true);
            hari = v.findViewById(R.id.tv_hariOngkir);
            desc = v.findViewById(R.id.tv_titleDescOngkir);
            tarif = v.findViewById(R.id.tv_tarifOngkir);
            estiamsi = v.findViewById(R.id.tv_estimasiOngkir);
            pilih = v.findViewById(R.id.button);
        }
    }

}
