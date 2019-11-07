package id.kakarental.store.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.kakarental.store.Acitivity.DetailStatusActivity;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.status_transaksi.DataStatusTransaksi;
import id.kakarental.store.R;

public class StatusTransaksi_Adapter extends RecyclerView.Adapter<StatusTransaksi_Adapter.ViewHolder> {

    private List<DataStatusTransaksi> data;
    private Context context;

    public StatusTransaksi_Adapter(List<DataStatusTransaksi> data, Context context) {

        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_status_pesanan, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.bindData(data.get(i));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView invoice, total, jam, status;
        private View mView;

        public ViewHolder(@NonNull View v) {
            super(v);

            mView = v;

            invoice = v.findViewById(R.id.tv_invoiceStatus);
            total = v.findViewById(R.id.tv_TotalStatus);
            jam = v.findViewById(R.id.tv_jamStransaksi);
            status = v.findViewById(R.id.tv_statusTransaksi);
        }

        private void bindData(DataStatusTransaksi data) {

            String statuses = data.getProses();
            String kurir = data.getKurir();
            String resi = data.getResi();
            final String konfirmsiPembayaran = data.getKonfirmasiPembayaran();
            final String kodeTrans = data.getKodeTransaksi();
            final String id_penjualan = data.getIdPenjualan();
            final String id_alamat = data.getAlamatId();
            String no_resi = "";

            if (resi == null || resi.isEmpty()) {
                no_resi = "";
            } else {
                no_resi = resi;
            }


            invoice.setText(data.getKodeTransaksi());
            jam.setText(data.getWaktuTransaksi());
            if (data.getTotalPembayaran() != null) {
                total.setText(FormatRp.getFomat(data.getTotalPembayaran()));
            } else {
                total.setText("Rp.0");
            }

            if (statuses.equals("0")) {
                status.setText("Dibatalkan");
                status.setBackgroundResource(R.drawable.bg_tv_pending);
            } else if (statuses.equals("1")) {
                status.setText("Menunggu Pembayaran");
                status.setBackgroundResource(R.drawable.bg_tv_proses);
            } else if (statuses.equals("2")) {
                status.setText("Pembayaran Sedang Diverifikasi");
                status.setBackgroundResource(R.drawable.bg_tv_konfirmasi);
            } else if (statuses.equals("3")) {
                status.setText("Pembayaran Diterima");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else if (kurir.equals("ambil sendiri") && statuses.equals("4")) {
                status.setText("Barang Belum di Ambil");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else if (kurir.equals("ambil sendiri") && statuses.equals("5")) {
                status.setText("Barang Sudah di Ambil");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else if (kurir.equals("ambil sendiri") && statuses.equals("8")) {
                status.setText("Barang Sudah di Ambil");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else if (kurir.equals("antar pemilik") && statuses.equals("4")) {
                status.setText("Menunggu Pengantaran");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else if (kurir.equals("antar pemilik") && statuses.equals("5")) {
                status.setText("Barang Sudah di Antar");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else if (kurir.equals("antar pemilik") && statuses.equals("8")) {
                status.setText("Barang Sudah di Antar");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else if (statuses.equals("4")) {
                status.setText("Barang Sedang di Packing");
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            } else {
                status.setText("Resi : " + resi);
                status.setBackgroundResource(R.drawable.bg_tv_packing);
            }

            final String finalNo_resi = no_resi;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailStatusActivity.class);
                    i.putExtra("kode_trans", kodeTrans);
                    i.putExtra("id_penjualan", id_penjualan);
                    i.putExtra("alamat_id", id_alamat);
                    i.putExtra("resi", finalNo_resi);
                    i.putExtra("konfirm", konfirmsiPembayaran);
                    context.startActivity(i);
                }
            });

//            if (statuses.equals("0")) {
//                status.setText("Menunggu Pembayaran");
//                status.setBackgroundResource(R.color.red);
//            } else if (statuses.equals("1")) {
//                status.setText("Sedang Diproses");
//                status.setBackgroundResource(R.color.orange);
//            } else if (statuses.equals("2")){
//                status.setText("Pembayaran Telah Dikonfirmasi");
//                status.setBackgroundResource(R.color.birulangit);
//            }else {
//                status.setText("Barang Sedang di Packing");
//                status.setBackgroundResource(R.color.green);
//            }

        }
    }
}
