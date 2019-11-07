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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import id.kakarental.store.Helper.DatePickerView;
import id.kakarental.store.Model.auth.DataLogin;
import id.kakarental.store.Model.registrasi.DataItemRegister;
import id.kakarental.store.Model.registrasi.ResponseRegister;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcountActivity extends AppCompatActivity {

    View view;
    AlertDialog.Builder builder;

    String TAG ="ini alamat ktp";
    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;
    Button upload;
    Uri imgUri;
    File filegambar;
    ImageView pictktp, back;
    EditText setnama, setemail, setNo, setWa, setTempat, setIg;
    DatePickerView setTgl;
    String upusername, uppassword, upalamat, upname, upemail, upno, upwa, upTgl, upTempat, upInstagram;
    Context context;
    List<DataItemRegister> register;
    DataItemRegister registerr;
    Button save;
    String text;
    ProgressBar progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_acount );
        context = this;

        setView();

    }


    private void setView(){
        back = findViewById( R.id.img_back );
        setnama = findViewById( R.id.tv_settingName );
        setemail = findViewById( R.id.tv_settingEmail );
        setNo = findViewById( R.id.tv_settingNohp );
        setWa = findViewById( R.id.tv_settingNoWa );

        setTgl = findViewById( R.id.tv_settingTglLahir );
        setTempat = findViewById( R.id.tv_settingTempatLahir );
        setIg = findViewById( R.id.tv_settingInstagram );

        save = findViewById( R.id.btn_saveProfil );
        progressBar = findViewById( R.id.pb_update );

        progressBar.setVisibility( View.INVISIBLE );

        pictktp = findViewById( R.id.iv_ktp );


        pictktp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] dialogitem = {"Camera", "Galery"};
                AlertDialog.Builder builder = new AlertDialog.Builder( AcountActivity.this );
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

        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setnama.length() == 0) {
                    setnama.setError( "Jangan ada yang kosong" );
                }else if (setTgl.length() == 0 ){
                    setTgl.setError( "Jangan ada yang kosong" );
                }else if (setTempat.length() == 0 ){
                    setTempat.setError( "Jangan ada yang kosong" );
                }
                else if (setemail.length() == 0){
                    setemail.setError( "Jangan ada yang kosong" );
                }else if (setWa.length() == 0){
                    setWa.setError( "Jangan ada yang kosong" );
                }
                else if (setNo.length() == 0){
                    setNo.setError( "Jangan ada yang kosong" );
                }
                else {
                    up();
                }

            }
        } );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        getData();
    }

    private void up() {
        upusername = Prefs.getString( SharedPreff.getUSERNAME(), "" );
        uppassword = Prefs.getString( SharedPreff.getPASSWORD(), "" );
        upname = setnama.getText().toString();
        upemail = setemail.getText().toString();
        upno = setNo.getText().toString();
        upwa = setWa.getText().toString();
        upTgl = setTgl.getText().toString();
        upInstagram = setIg.getText().toString();
        upTempat = setTempat.getText().toString();




        if (filegambar != null) {
            uploadImagee();
            update2(  upname, upemail, upno, upwa, upTgl, upInstagram, upTempat );

        } else if (filegambar == null){
            update(  upname, upemail, upno, upwa, upTgl, upInstagram, upTempat );

        }


    }

    private void getData() {
        String id = Prefs.getString( SharedPreff.getIdKonsumen(), "" );
        Call<ResponseRegister> call = InitRetrofit.getInstance().getdatauser( id );
        call.enqueue( new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                ResponseRegister res = response.body();
                if (res.getData() != null) {
                    register = res.getData();
                    setnama.setText( register.get( 0 ).getNamaLengkap() );
                    setemail.setText( register.get( 0 ).getEmail() );
                    setNo.setText( register.get( 0 ).getNoHp() );
                    setWa.setText( register.get( 0 ).getNoWa() );
                    setTempat.setText( register.get( 0 ).getTempatLahir() );
                    setTgl.setText( register.get( 0 ).getTanggalLahir() );
                    setIg.setText( register.get( 0 ).getInstagram() );

                    String ktp = InitRetrofit.getKtp()+register.get( 0 ).getFoto() ;
                    Toast.makeText( AcountActivity.this, ""+ktp, Toast.LENGTH_SHORT ).show();
                    Picasso.get().load( ktp ).error( R.drawable.placeholder_fail ).into( pictktp );
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {

            }
        } );
    }

    private void update( String fullname, String email, String notlpn, String nowa,
                        String tgl, String instagram, String tempat) {
        progressBar.setVisibility( View.VISIBLE );

        Call<ResponseRegister> call = InitRetrofit.getInstance().update( Prefs.getString( SharedPreff.getIdKonsumen(), "" ), fullname, email, notlpn, nowa, tgl, instagram, tempat );
        call.enqueue( new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                ResponseRegister preff = response.body();
                //register = preff.getData();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean sukses = response.body().isStatus();
                    if (sukses) {
//                        registerr = (DataItemRegister) preff.getData();
                        Toast.makeText( AcountActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        //setPrefference( register );
                        finish();

                    } else {
                        Toast.makeText( AcountActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        progressBar.setVisibility( View.GONE );
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                progressBar.setVisibility( View.GONE );
                Toast.makeText( AcountActivity.this, "Eror", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void update2( String fullname, String email, String notlpn, String nowa,
                         String tgl, String instagram, String tempat) {
        progressBar.setVisibility( View.VISIBLE );

        Call<ResponseRegister> call = InitRetrofit.getInstance().update2( Prefs.getString( SharedPreff.getIdKonsumen(), "" ), fullname, email, notlpn, nowa, tgl, instagram, tempat );
        call.enqueue( new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                ResponseRegister preff = response.body();
                //register = preff.getData();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean sukses = response.body().isStatus();
                    if (sukses) {
//                        registerr = (DataItemRegister) preff.getData();
                        Toast.makeText( AcountActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        //setPrefference( register );
                        finish();

                    } else {
                        //Toast.makeText( AcountActivity.this, "Gagal " + response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        progressBar.setVisibility( View.GONE );
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                progressBar.setVisibility( View.GONE );
                Toast.makeText( AcountActivity.this, "Eror", Toast.LENGTH_SHORT ).show();
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

    private void uploadImagee() {

        final RequestBody req = RequestBody.create( MediaType.parse( "multipart/form-file" ), filegambar );
        MultipartBody.Part partImage = MultipartBody.Part.createFormData( "gambar", filegambar.getName(), req );

        Call<ResponseRegister> call = InitRetrofit.getInstance().updatektp( partImage );

        call.enqueue( new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                ResponseRegister res = response.body();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean sukses = response.body().isStatus();
                    if (sukses) {

                        finish();
                    } else {
                        Toast.makeText( AcountActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {

            }
        } );

    }

    //private void setPrefference(List<DataItemRegister> data) {
//        Prefs.putString(SharedPreff.getIdKonsumen(), data.getIdKonsumen());
        //Prefs.putString(SharedPreff.getUSERNAME(), data.get( 0 ).getUsername());
//        Prefs.putString(SharedPreff.getPASSWORD(), data.getPassword());
//        Prefs.putString(SharedPreff.getNamaLengkap(), data.getNamaLengkap());
//        Prefs.putString(SharedPreff.getEMAIL(), data.getEmail());
//        Prefs.putString(SharedPreff.getJenisKelamin(), data.getJenisKelamin());
//        Prefs.putString(SharedPreff.getTanggalLahir(), data.getTanggalLahir());
//        Prefs.putString(SharedPreff.getTempatLahir(), data.getTempatLahir());
//        Prefs.putString(SharedPreff.getAlamatLengkap(), data.getAlamatLengkap());
//        Prefs.putString(SharedPreff.getIdKota(), data.getKotaId());
//        Prefs.putString(SharedPreff.getNoHp(), data.getNoHp());
   // }

}