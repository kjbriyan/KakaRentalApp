package id.kakarental.store.Acitivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Model.registrasi.DataItem;
import id.kakarental.store.Model.registrasi.Kota.DataItemKota;
import id.kakarental.store.Model.registrasi.Kota.ResponseKota;
import id.kakarental.store.Model.registrasi.ResponseProvinsi;
import id.kakarental.store.Model.registrasi.ResponseRegister;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {
    private final ArrayList<ResponseProvinsi> spinnerArrayList = new ArrayList<>();
    private String[] items = {"Camera", "Gallery"};
    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;
    private final int CAMERA_RESULT = 101;
    EditText usname, uspasswd, fullname, email,  nohp, id_kk, instagram, tgl, tempat;
    TextView user;
    ImageView pictktp;
    Button btn_regis;
   // Spinner provs, kota,status;
    ProgressBar progressBar;
    Context context;
    File filegambar;
    private String postPath;
    Uri imgUri;
    String idkota, almtstatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registrasi );

        usname = findViewById( R.id.et_regUsername );
        uspasswd = findViewById( R.id.et_regPasswd );
        fullname = findViewById( R.id.et_regFullname );
        email = findViewById( R.id.et_regEmail );
        //alamat = findViewById( R.id.et_regAlamat );
        nohp = findViewById( R.id.et_regNohp );
        btn_regis = findViewById( R.id.btn_registrasi );
        progressBar = findViewById( R.id.pb_register );
//        provs = findViewById( R.id.sp_provinsi );
//        kota = findViewById( R.id.sp_kota );
//        status = findViewById( R.id.sp_kategorialamat );
        id_kk = findViewById( R.id.et_idkk );

        pictktp = findViewById( R.id.iv_ktp );


        pictktp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] dialogitem = {"Camera", "Galery"};
                AlertDialog.Builder builder = new AlertDialog.Builder( RegistrasiActivity.this );
                builder.setTitle( "Pilih" );

                builder.setItems( dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {

                            case 0:
                                open_camera();
                                break;

                            case 1:
                                open_galey();
                                break;
                        }
                    }
                } );
                builder.create().show();
            }
        } );

        progressBar.setVisibility( View.INVISIBLE );



       view();

    }
    private void view(){
        //spiner();
        btn_regis.setOnClickListener( new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (usname.length() == 0){
                    usname.setError( "Username tidak boleh kosong" );

                } else if (uspasswd.length() < 8){
                    uspasswd.setError( "Password minimal 8" );
                }else if (email.length() == 0){
                    email.setError( "email tidak boleh kosong" );
                }else if (fullname.length() == 0){
                    fullname.setError( "Nama lengkap harus di isi" );
                }else if (nohp.length() == 0){
                    nohp.setError( "no hp tidak boleh kosong" );
                }
                else {
                    kondisiimg();
                }
            }

        } );
    }


//    private void spiner() {
//
//        Call<ResponseProvinsi> call = InitRetrofit.getInstance().getProv();
//        call.enqueue( new Callback<ResponseProvinsi>() {
//            @Override
//            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
//                final ResponseProvinsi res = response.body();
//                if (res.getData() != null) {
//                    final List<DataItem> items = res.getData();
//                    final List<String> stringList = new ArrayList<String>();
//
//                    for (int i = 0; i < items.size(); i++) {
//                        stringList.add( items.get( i ).getNamaProvinsi() );
//
//                    }
//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>( RegistrasiActivity.this, android.R.layout.simple_spinner_item,
//                            stringList );
//                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
//                    provs.setAdapter( adapter );
//
//
//                    provs.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
//                            String idd = items.get( position ).getProvinsiId();
//                            Call<ResponseKota> kotaCall = InitRetrofit.getInstance().postKota( idd );
//                            kotaCall.enqueue( new Callback<ResponseKota>() {
//                                @Override
//                                public void onResponse(Call<ResponseKota> calll, Response<ResponseKota> responsee) {
//                                    final ResponseKota responseKota = responsee.body();
//                                    if (responseKota.getData() != null) {
//                                        final List<DataItemKota> itemKotas = responseKota.getData();
//                                        final List<String> stringList1 = new ArrayList<String>();
//                                        for (int j = 0; j < itemKotas.size(); j++) {
//                                            stringList1.add( itemKotas.get( j ).getNamaKota() );
//                                        }
//                                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( RegistrasiActivity.this,
//                                                android.R.layout.simple_spinner_item, stringList1 );
//                                        adapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
//                                        kota.setAdapter( adapter1 );
//
//                                        idkota = itemKotas.get( 0 ).getKotaId();
//
//
//                                    } else {
//                                        Toast.makeText( RegistrasiActivity.this, "Gagal ambil data spiner 2", Toast.LENGTH_SHORT ).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ResponseKota> calll, Throwable t) {
//                                    Toast.makeText( RegistrasiActivity.this, "jaringan gagal spiner 2", Toast.LENGTH_SHORT ).show();
//                                }
//                            } );
//
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    } );
//                } else {
//                    Toast.makeText( RegistrasiActivity.this, "gagal mengambil data" + res.getMessage(), Toast.LENGTH_SHORT ).show();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
//                Toast.makeText( RegistrasiActivity.this, "internet masalah", Toast.LENGTH_SHORT ).show();
//
//            }
//        } );
//    }

    private void postRegistrasi(String username, String pass, String namalengkap, String email, String nohp,
                                String idkk) {


        progressBar.setVisibility( View.VISIBLE );

        Call<ResponseRegister> call = InitRetrofit.getInstance().postRegistrasi( username, pass, namalengkap, email, nohp
                ,  idkk );


        call.enqueue( new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                if (response.isSuccessful() && response.body() != null) {
                    Boolean sukses = response.body().isStatus();
                    if (sukses) {
                        Toast.makeText( RegistrasiActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        finish();
                    } else {
                        Toast.makeText( RegistrasiActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                progressBar.setVisibility( View.GONE );
                Toast.makeText( RegistrasiActivity.this, "Eror ", Toast.LENGTH_SHORT ).show();
            }
        } );
    }


    private void open_camera() {
        Intent camera = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        startActivityForResult( camera, REQUEST_CODE_CAMERA );
    }

    private void open_galey() {
        Intent galery = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI );
        startActivityForResult( galery, REQUEST_CODE_GALLERY );
    }

    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_GALLERY) {
                if (data != null) {
                    imgUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap( this.getContentResolver()
                                , imgUri );
                        tmpFile( bitmap );
                        pictktp.setImageBitmap( bitmap );
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                Bitmap bitmap = (Bitmap) data.getExtras().get( "data" );
                tmpFile( bitmap );
                pictktp.setImageBitmap( bitmap );
            }
        }

    }

    private File tmpFile(Bitmap bitmap) {
        filegambar = new File( getExternalFilesDir( Environment.DIRECTORY_PICTURES )
                , System.currentTimeMillis() + "_iamge.JPEG" );
        ByteArrayOutputStream bos = new ByteArrayOutputStream();


        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, bos );
        byte[] bitmadata = bos.toByteArray();

        try {
            FileOutputStream fos = new FileOutputStream( filegambar );
            fos.write( bitmadata );
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filegambar;
    }



    private void kondisiimg() {
        final String txtusname = usname.getText().toString().trim();
        final String txtpswd = uspasswd.getText().toString().trim();
        final String txtemail = email.getText().toString();
        final String txtfullname = fullname.getText().toString();
        //final String txtalamat = alamat.getText().toString();
        final String txtnohp = nohp.getText().toString();

        final String idkk = id_kk.getText().toString();


        //almtstatus = status.getSelectedItem().toString();






        if (filegambar != null) {
            uploadImage();

            postRegistrasi( txtusname, txtpswd, txtfullname, txtemail, txtnohp, idkk );
            finish();

        } else {
            Toast.makeText( RegistrasiActivity.this, "tolong masukan foto", Toast.LENGTH_SHORT ).show();
        }
    }

    private void uploadImage() {


        final RequestBody req = RequestBody.create( MediaType.parse( "multipart/form-file" ), filegambar );
        MultipartBody.Part partImage = MultipartBody.Part.createFormData( "gambar", filegambar.getName(), req );

        Call<ResponseRegister> call = InitRetrofit.getInstance().uploadpoto( partImage );

        call.enqueue( new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                ResponseRegister res = response.body();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean sukses = response.body().isStatus();
                    if (sukses) {
                        Toast.makeText( RegistrasiActivity.this, "sukses dong gambar " + response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        finish();
                    } else {
                        Toast.makeText( RegistrasiActivity.this, "gagal dong gambarnya " + response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {

            }
        } );

    }


}
