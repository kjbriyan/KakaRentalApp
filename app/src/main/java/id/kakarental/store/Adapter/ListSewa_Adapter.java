package id.kakarental.store.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import id.kakarental.store.Acitivity.DaftarSewa_Activity;
import id.kakarental.store.Acitivity.PerpanjangActivity;
import id.kakarental.store.Helper.FormatRp;
import id.kakarental.store.Model.list_sewa.DataItemSewa;
import id.kakarental.store.Model.perpanjang.ResponseKonfirm;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSewa_Adapter extends RecyclerView.Adapter<ListSewa_Adapter.CartHolder> {
    private static final String TAG = "ListSewa_Adapter";

    List<DataItemSewa> data;
    Context mContext;

    public ListSewa_Adapter(List<DataItemSewa> data, Context context) {
        this.data = data;
        this.mContext = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_daftar_sewa_adapter, viewGroup, false);

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
        private TextView tv_namaProduk, tv_harga, tv_durasiSewa,
                tv_status, tggl, durasi, statusPerpanjang, TglPerpanjang,
                konfirm_pembayaran;
        private ImageView img_cartHolder;
        private View mView;
        private Button btn_perpanjang, btn_invoice, btn_konfirm;
        private CardView container;

        public CartHolder(@NonNull View v) {
            super(v);

            mView = v;

            tv_namaProduk = v.findViewById(R.id.tv_namaProdCart);
            tv_harga = v.findViewById(R.id.tv_hargaProdCart);
            tv_durasiSewa = v.findViewById(R.id.tv_durasiSewaCart);
            tv_status = v.findViewById(R.id.tv_statusCart);
            tggl = v.findViewById(R.id.tv_tggl);
            durasi = v.findViewById(R.id.tv_durasi);
            img_cartHolder = v.findViewById(R.id.img_cartThumb);
            container = v.findViewById(R.id.container2);
            statusPerpanjang = v.findViewById(R.id.tv_statusPerpanjang);
            TglPerpanjang = v.findViewById(R.id.tv_tanggalPerpanjang);
            btn_perpanjang = v.findViewById(R.id.btn_perpanjang);
            btn_invoice = v.findViewById(R.id.invoice);
            btn_konfirm = v.findViewById(R.id.konfirm);
            konfirm_pembayaran = v.findViewById(R.id.tv_konfirmPel);
        }

        public void bindItem(DataItemSewa data) {

            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTime tgl = format.parseDateTime(data.getTglAkhir());
            DateTime tgl_perpanjang = format.parseDateTime("1976-01-30");

            if (data.getTglPerpanjang() != null) {
                tgl_perpanjang = format.parseDateTime(data.getTglPerpanjang());
            }

            DateTime now = DateTime.now();
            DateTime exp = tgl.plusDays(1);
            LocalDate tgl_akhir = new LocalDate(tgl);
            LocalDate sekarang = new LocalDate(now);
            LocalDate tgl_akhir_perpanjang = new LocalDate(tgl_perpanjang);


            int days = Days.daysBetween(sekarang, tgl_akhir).getDays();
            int days_per = Days.daysBetween(sekarang, tgl_akhir_perpanjang).getDays();

            if (data.getProsesPerpanjang().equals("3")) {

                if (sekarang.isAfter(tgl_akhir) == true) {
                    durasi.setText("Masa Sewa Telah habis");
                    durasi.setTextColor(R.color.red);
                } else {
                    durasi.setText("Masa sewa barang ini tinggal " + days_per + " hari");
                }

            } else {
                if (sekarang.isAfter(tgl_akhir) == true) {
                    durasi.setText("Masa Sewa Telah habis");
                    durasi.setTextColor(R.color.red);
                } else {
                    durasi.setText("Masa sewa barang ini tinggal " + days + " hari");
                }
            }
            tggl.setText("Tanggal mulai sewa " + data.getTglSewa() + " sampai " + data.getTglAkhir());

            tv_namaProduk.setText(data.getNamaProduk());
            Picasso.get().load(InitRetrofit.getIMG_URL() + data.getGambar()).into(img_cartHolder);
            //  berat.setText(data.getBerat()+ " Gram");
            tv_harga.setText(FormatRp.getFomat(data.getHargaJual()));

            String proses = data.getProses();
            int perpanjang = Integer.valueOf(data.getProsesPerpanjang());
            if (proses.equals("8")) {
                tv_status.setText("Sewa Sudah Selesai");
            } else if (Integer.valueOf(proses) < 3) {
                tv_status.setText("Menunggu Pembayaran");
                btn_perpanjang.setText("Lihat Detail");
                durasi.setVisibility(View.GONE);
            } else {
                tv_status.setText("Dalam Masa Sewa");
            }
            tv_status.setBackgroundResource(R.color.orange);
            tv_harga.setText(FormatRp.getFomat(data.getHargaSewa()));
            tv_durasiSewa.setText("Durasi Sewa " + data.getDurasi() + " " + data.getJenisSewa());
            tv_durasiSewa.setVisibility(View.VISIBLE);
            Log.d(TAG, "bindItem: " + data.getKeterangan());


            if (data.getKeterangan() != null && !data.getKeterangan().isEmpty()) {
                btn_perpanjang.setVisibility(View.GONE);

                if (data.getProsesPerpanjang().equals("1")) {
                    statusPerpanjang.setText("Menunggu Pembayaran Perpanjang");

                    btn_konfirm.setBackgroundResource(R.drawable.bg_button_orange);
                    konfirm_pembayaran.setVisibility(View.GONE);
                } else if (data.getProsesPerpanjang().equals("2")) {
                    statusPerpanjang.setText("Menunggu Konfirmasi Pembayaran");
                    btn_konfirm.setClickable(false);
                    btn_konfirm.setBackgroundResource(R.drawable.bg_button_red);
                    konfirm_pembayaran.setText("Anda telah melakukan konfirmasi pembayaran pada pesanan ini, " +
                            "silahkan tunggu sampai admin telah memverivikasi pembayaran anda. \n Maks 3x24 hari.");
                } else if (data.getProsesPerpanjang().equals("3")) {
                    statusPerpanjang.setText("Permbayaran Perpanjangan Telah Diterima");

                    btn_konfirm.setBackgroundResource(R.drawable.bg_button_red);
                    btn_konfirm.setClickable(false);
                    konfirm_pembayaran.setText("Pembayaran anda telah di terima");
                } else {
                    statusPerpanjang.setText("Perpanjangan Dibatalkan");
                    btn_konfirm.setVisibility(View.GONE);
                    btn_invoice.setVisibility(View.GONE);
                    konfirm_pembayaran.setVisibility(View.GONE);
                }

                TglPerpanjang.setText(data.getTglPerpanjang());


            } else {

                container.setVisibility(View.GONE);
            }

            initButtonProses(data);

        }

        private void initButtonProses(final DataItemSewa data) {

            final String id_detail = data.getIdPenjualanDetail();
            final String id_penjualan = data.getIdPenjualan();
            final String id_produk = data.getIdProduk();
            final String cek_proses = data.getProses();

            btn_perpanjang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, PerpanjangActivity.class);
                    i.putExtra("id_detail", id_detail);
                    i.putExtra("id_penjualan", id_penjualan);
                    i.putExtra("id_produk", id_produk);
                    i.putExtra("proses", cek_proses);
                    mContext.startActivity(i);

                }
            });


            // final String Url = "https://lagiviral.web.id/produk/print_invoice/" + kodeTransaksi;
            final String Url = "http://"+ Prefs.getString(SharedPreff.getIP(), null)+ "/kakarenv1/produk/print_invoice/" + data.getKodeTransaksi();

            btn_invoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(Url));
                    mContext.startActivity(i);

                }
            });
            btn_konfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    konfirmTransaksi(data.getIdPenjualan());
                                    statusPerpanjang.setText("Menunggu Konfirmasi Pembayaran");
                                    btn_konfirm.setClickable(false);
                                    btn_konfirm.setBackgroundResource(R.drawable.bg_button_red);
                                    konfirm_pembayaran.setVisibility(View.VISIBLE);
                                    konfirm_pembayaran.setText("Anda telah melakukan konfirmasi pembayaran pada pesanan ini, " +
                                            "silahkan tunggu sampai admin telah memverivikasi pembayaran anda. \n Maks 3x24 hari.");

                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialogInterface.dismiss();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Apakah anda yakin telah melakukan pembayaran").setPositiveButton("Ya", dialogClickListener)
                            .setNegativeButton("Belum", dialogClickListener);

                    if (Integer.valueOf(data.getProsesPerpanjang())<2){
                        builder.show();
                    }else {

                    }
                }
            });
        }

        private void konfirmTransaksi(String idPenjualan) {
            Call<ResponseKonfirm> konfirm = InitRetrofit.getInstance().konfirmPerpanjang(idPenjualan);
            konfirm.enqueue(new Callback<ResponseKonfirm>() {
                @Override
                public void onResponse(Call<ResponseKonfirm> call, Response<ResponseKonfirm> response) {


                }

                @Override
                public void onFailure(Call<ResponseKonfirm> call, Throwable t) {

                }
            });
        }
    }


}
