package id.kakarental.store.Acitivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

public class UpdateKtpActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;
    Button upload;
    Uri imgUri;
    File filegambar;
    ImageView pictktp, back;
    ProgressDialog dialog;
    List<DataItemRegister> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update_ktp );

        dialog = new ProgressDialog( this );
        dialog.setMessage( "Uploading..." );
        dialog.setCancelable( false );


        upload = findViewById( R.id.btn_updatefotoktp );

        pictktp = findViewById( R.id.iv_ktp );
        back = findViewById( R.id.iv_backstatus );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        pictktp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] dialogitem = {"Camera", "Galery"};
                AlertDialog.Builder builder = new AlertDialog.Builder( UpdateKtpActivity.this );
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

        upload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImagee();
            }
        } );

        getData();
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

                    String ktp = InitRetrofit.getKtp() + register.get( 0 ).getFoto();
                    Toast.makeText( UpdateKtpActivity.this, "" + ktp, Toast.LENGTH_SHORT ).show();
                    Picasso.get().load( ktp ).error( R.drawable.placeholder_fail ).into( pictktp );
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {

            }
        } );
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
                        Toast.makeText( UpdateKtpActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {

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

}
