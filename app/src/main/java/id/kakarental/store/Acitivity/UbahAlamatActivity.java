package id.kakarental.store.Acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Model.alamat.ResponseTambahAlamat;
import id.kakarental.store.Model.registrasi.DataItem;
import id.kakarental.store.Model.registrasi.Kota.DataItemKota;
import id.kakarental.store.Model.registrasi.Kota.ResponseKota;
import id.kakarental.store.Model.registrasi.ResponseProvinsi;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import id.kakarental.store.Utils.Move;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahAlamatActivity extends AppCompatActivity {

    private Spinner provs, kota, kategori;
//    Spinner kategori;
    EditText alamat, nohp, atasnama;
    TextView  al;
    String kota_id, iduser, almt, hp, atsnama;
    Button simpan;
    TextView id;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_alamat);
        provs = findViewById(R.id.sp_uaprovinsi);
        kota = findViewById(R.id.sp_uakota);
        alamat = findViewById(R.id.et_ubahaLamat);
        nohp = findViewById(R.id.et_ubahtelephone);
        atasnama = findViewById(R.id.et_ubahatasnama);
        simpan = findViewById(R.id.btn_uaaalamat);
        al = findViewById(R.id.alamat);
        back = findViewById( R.id.img_back );
        kategori = findViewById( R.id.sp_uakategori );

        view();
    }

    private void view() {
        spiner();
        iduser = Prefs.getString(SharedPreff.getIdKonsumen(), "");

        String aa = getIntent().getExtras().getString( "alamat" );
        String bb = getIntent().getExtras().getString( "no" );
        String cc = getIntent().getExtras().getString( "penerima" );
        String d = getIntent().getExtras().getString( "provs" );
        String e = getIntent().getExtras().getString( "kota" );

//        provs.setText( d );
//        kota.setText( e );
        nohp.setText( bb );
        atasnama.setText( cc );
        alamat.setText( aa );

//        provs.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Move.move( UbahAlamatActivity.this,PilihProvinsiActivity.class );
//            }
//        } );
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String a = alamat.getText().toString();
                final String b = nohp.getText().toString();
                final String c = atasnama.getText().toString();
                String kat = kategori.getSelectedItem().toString();
                String kot = kota.getSelectedItem().toString();
                if (atasnama.length()==0 ){
                    atasnama.setError( "ALamat kosong" );
                }else if (nohp.length()==0){
                    nohp.setError( "Nomor hp tidak boleh kosong" );
                }else if (alamat.length()==0){
                    alamat.setError( "atas nama tidak boleh kosong" );
                }
                else{
                    ubahalamat( c,kota_id,b,a,kat,iduser );

                }
            }
        });
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }

//    private void spiner() {
//
//        Call<ResponseProvinsi> call = InitRetrofit.getInstance().getProv();
//        call.enqueue(new Callback<ResponseProvinsi>() {
//            @Override
//            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
//                final ResponseProvinsi res = response.body();
//                if (res.getData() != null) {
//                    final List<DataItem> items = res.getData();
//                    final List<String> stringList = new ArrayList<String>();
//
//                    for (int i = 0; i < items.size(); i++) {
//                        stringList.add(items.get(i).getNamaProvinsi());
//
//                    }
//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UbahAlamatActivity.this, android.R.layout.simple_spinner_item,
//                            stringList);
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    provs.setAdapter(adapter);
//
//
//                    provs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
//                            String idd = items.get(position).getProvinsiId();
//                            final Call<ResponseKota> kotaCall = InitRetrofit.getInstance().postKota(idd);
//                            kotaCall.enqueue(new Callback<ResponseKota>() {
//                                @Override
//                                public void onResponse(Call<ResponseKota> calll, Response<ResponseKota> responsee) {
//                                    final ResponseKota responseKota = responsee.body();
//                                    if (responseKota.getData() != null) {
//                                        final List<DataItemKota> itemKotas = responseKota.getData();
//                                        final List<String> stringList1 = new ArrayList<String>();
//                                        for (int j = 0; j < itemKotas.size(); j++) {
//                                            stringList1.add(itemKotas.get(j).getNamaKota());
//                                        }
//                                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(UbahAlamatActivity.this,
//                                                android.R.layout.simple_spinner_item, stringList1);
//                                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                        kota.setAdapter(adapter1);
//
//                                        kota_id = itemKotas.get(position).getKotaId();
//
//
//                                    } else {
//                                        Toast.makeText(UbahAlamatActivity.this, "Gagal ambil data spiner 2", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ResponseKota> calll, Throwable t) {
//                                    Toast.makeText(UbahAlamatActivity.this, "jaringan gagal spiner 2", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                } else {
//                    Toast.makeText(UbahAlamatActivity.this, "gagal mengambil data" + res.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
//                Toast.makeText(UbahAlamatActivity.this, "internet masalah", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    private void spiner() {

        Call<ResponseProvinsi> call = InitRetrofit.getInstance().getProv();
        call.enqueue( new Callback<ResponseProvinsi>() {
            @Override
            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
                final ResponseProvinsi res = response.body();
                if (res.getData() != null) {
                    final List<DataItem> items = res.getData();
                    final List<String> stringList = new ArrayList<String>();

                    for (int i = 0; i < items.size(); i++) {
                        stringList.add( items.get( i ).getNamaProvinsi() );

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>( UbahAlamatActivity.this, android.R.layout.simple_spinner_item,
                            stringList );
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                    provs.setAdapter( adapter );


                    provs.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                            String idd = items.get( position ).getProvinsiId();
                            Call<ResponseKota> kotaCall = InitRetrofit.getInstance().postKota( idd );
                            kotaCall.enqueue( new Callback<ResponseKota>() {
                                @Override
                                public void onResponse(Call<ResponseKota> calll, Response<ResponseKota> responsee) {
                                    final ResponseKota responseKota = responsee.body();
                                    if (responseKota.getData() != null) {
                                        final List<DataItemKota> itemKotas = responseKota.getData();
                                        final List<String> stringList1 = new ArrayList<String>();
                                        for (int j = 0; j < itemKotas.size(); j++) {
                                            stringList1.add( itemKotas.get( j ).getNamaKota() );
                                        }
                                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( UbahAlamatActivity.this,
                                                android.R.layout.simple_spinner_item, stringList1 );
                                        adapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                                        kota.setAdapter( adapter1 );

                                        kota_id = itemKotas.get( 0 ).getKotaId();


                                    } else {
                                        Toast.makeText( UbahAlamatActivity.this, "Gagal ambil data spiner 2", Toast.LENGTH_SHORT ).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseKota> calll, Throwable t) {
                                    Toast.makeText( UbahAlamatActivity.this, "jaringan gagal spiner 2", Toast.LENGTH_SHORT ).show();
                                }
                            } );

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    } );
                } else {
                    Toast.makeText( UbahAlamatActivity.this, "gagal mengambil data" + res.getMessage(), Toast.LENGTH_SHORT ).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
                Toast.makeText( UbahAlamatActivity.this, "internet masalah", Toast.LENGTH_SHORT ).show();

            }
        } );
    }


    public void ubahalamat(String atasnama, String kotai, String nohp, String alamat,String almtketegori, String userId) {
        String idd = getIntent().getStringExtra("id");
        Call<ResponseTambahAlamat> call = InitRetrofit.getInstance().updateAlamat(idd, atasnama, kotai, nohp, alamat,almtketegori ,userId);
        call.enqueue(new Callback<ResponseTambahAlamat>() {
            @Override
            public void onResponse(Call<ResponseTambahAlamat> call, Response<ResponseTambahAlamat> response) {
                ResponseTambahAlamat res = response.body();

                if (res.isStatus()) {
                    finish();
                    Toast.makeText(UbahAlamatActivity.this, "Sukses Ubah alamat", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UbahAlamatActivity.this, "gagal Ubah alamat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahAlamat> call, Throwable t) {
                Toast.makeText(UbahAlamatActivity.this, "Jaringan gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
