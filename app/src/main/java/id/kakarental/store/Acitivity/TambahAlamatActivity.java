package id.kakarental.store.Acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Model.alamat.ResponseTambahAlamat;
import id.kakarental.store.Model.registrasi.DataItem;
import id.kakarental.store.Model.registrasi.Kota.DataItemKota;
import id.kakarental.store.Model.registrasi.Kota.ResponseKota;
import id.kakarental.store.Model.registrasi.ResponseProvinsi;
import id.kakarental.store.Model.registrasi.ResponseRegister;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahAlamatActivity extends AppCompatActivity {

    private Spinner provs, kota, status;
    EditText alamat ,nohp,atasnama;
    String kota_id, iduser, almt,hp,atsnama, statusalamt;
    Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tambah_alamat );




        view();
    }

    private void view()
    {
        spiner();
        iduser = Prefs.getString( SharedPreff.getIdKonsumen(), null );
        provs = findViewById( R.id.sp_tbprovinsi );
        kota = findViewById( R.id.sp_tbkota );
        alamat = findViewById( R.id.et_tbaAlamat );
        nohp = findViewById( R.id.et_tbanotlpn );
        atasnama = findViewById( R.id.et_tbaatasnama );
        simpan = findViewById( R.id.btn_tbaalamat );
        status = findViewById( R.id.sp_kategorialamat );

        statusalamt = status.getSelectedItem().toString();

        simpan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atsnama = atasnama.getText().toString() ;
                almt = alamat.getText().toString();
                hp =  nohp.getText().toString() ;

                if (atasnama.length()==0 ){
                    atasnama.setError( "ALamat kosong" );
                }else if (nohp.length()==0){
                    nohp.setError( "Nomor hp tidak boleh kosong" );
                }else if (alamat.length()==0){
                    alamat.setError( "atas nama tidak boleh kosong" );
                }
                else{
                    tambahalamat( atsnama,kota_id,hp,almt,statusalamt,iduser );

                }
            }
        } );



    }

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

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>( TambahAlamatActivity.this, android.R.layout.simple_spinner_item,
                            stringList );
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                    provs.setAdapter( adapter );


                    provs.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                            String idd = items.get( position ).getProvinsiId();
                            final Call<ResponseKota> kotaCall = InitRetrofit.getInstance().postKota( idd );
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
                                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( TambahAlamatActivity.this,
                                                android.R.layout.simple_spinner_item, stringList1 );
                                        adapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                                        kota.setAdapter( adapter1 );

                                        kota_id = itemKotas.get( position ).getKotaId();


                                    } else {
                                        Toast.makeText( TambahAlamatActivity.this, "Gagal ambil data spiner 2", Toast.LENGTH_SHORT ).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseKota> calll, Throwable t) {
                                    Toast.makeText( TambahAlamatActivity.this, "jaringan gagal spiner 2", Toast.LENGTH_SHORT ).show();
                                }
                            } );

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    } );
                } else {
                    Toast.makeText( TambahAlamatActivity.this, "gagal mengambil data" + res.getMessage(), Toast.LENGTH_SHORT ).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
                Toast.makeText( TambahAlamatActivity.this, "internet masalah", Toast.LENGTH_SHORT ).show();

            }
        } );
    }

    public void tambahalamat(String atasnama,String kotaid, String nohp, String alamat,String sts, String userId){

        Call<ResponseTambahAlamat> call = InitRetrofit.getInstance().tambahAlamat(atasnama,kotaid,nohp,alamat,sts,userId );
        call.enqueue( new Callback<ResponseTambahAlamat>() {
            @Override
            public void onResponse(Call<ResponseTambahAlamat> call, Response<ResponseTambahAlamat> response) {
                ResponseTambahAlamat res = response.body();
                if (res.isStatus()){
                    finish();
                    Toast.makeText( TambahAlamatActivity.this, "Sukses tambah alamat", Toast.LENGTH_SHORT ).show();
                }else {
                    Toast.makeText( TambahAlamatActivity.this, "gagal tambah alamat", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahAlamat> call, Throwable t) {
                Toast.makeText( TambahAlamatActivity.this, "Jaringan gagal", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
