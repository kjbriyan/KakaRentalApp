package id.kakarental.store.Acitivity;

import android.os.Bundle;
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

import java.util.List;

import id.kakarental.store.Model.registrasi.DataItemRegister;
import id.kakarental.store.Model.registrasi.ResponseRegister;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetingProfileActivity extends AppCompatActivity {


    private EditText oldPass,newPass,repPass,username;
    String pasold,passnew,passrep,upusername,upalamat,upname,upemail,upno,upwa,upTgl,upTempat,upInstagram;
    private Button save;
    ProgressBar progressBar;
    List<DataItemRegister> register;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_seting_profile );
        // username = findViewById( R.id.tv_settingUsername );
        oldPass = findViewById( R.id.et_oldPass );
        newPass = findViewById( R.id.et_settingNewPass );
        repPass = findViewById( R.id.et_settingrepeetPass );
        save = findViewById( R.id.btn_saveAcount );
        back = findViewById( R.id.img_back );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        //Toast.makeText( this, ""+Prefs.getString( SharedPreff.getPASSWORD() ,"" ), Toast.LENGTH_SHORT ).show();

        progressBar = findViewById( R.id.pb_profile );
        progressBar.setVisibility( View.INVISIBLE );
        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kondisiPass();

            }
        } );


    }

    private void kondisiPass(){
        pasold = oldPass.getText().toString();
        passnew = newPass.getText().toString();
        passrep = repPass.getText().toString();
        upusername = Prefs.getString( SharedPreff.getUSERNAME(),"" );
        String old =Prefs.getString( SharedPreff.getPASSWORD(), " " ) ;

        if (pasold.equalsIgnoreCase( old ) ) {

            if (passnew.length() >= 8 ){
                if (passnew.equalsIgnoreCase( passrep )) {
                    update( passrep );
                } else {
                    Toast.makeText( SetingProfileActivity.this, "Password tidak sama", Toast.LENGTH_SHORT ).show();
                }

            }else{
                newPass.setError( "password minimal 8 karakter" );
            }

        }else {
            Toast.makeText( this, "Pasword lama anda tidak benar", Toast.LENGTH_SHORT ).show();
        }
//        getData();




    }
//    private void getData() {
//        String id = Prefs.getString( SharedPreff.getIdKonsumen(), "" );
//        Call<ResponseRegister> call = InitRetrofit.getInstance().getdatauser( id );
//        call.enqueue( new Callback<ResponseRegister>() {
//            @Override
//            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
//                ResponseRegister res = response.body();
//                if (res.getData() != null) {
//                    register = res.getData();
//                    upname = register.get( 0 ).getNamaLengkap() ;
//                    upemail =register.get( 0 ).getEmail() ;
//                    upno =  register.get( 0 ).getNoHp() ;
//                    upwa = register.get( 0 ).getNoWa() ;
//                    upTempat = register.get( 0 ).getTempatLahir() ;
//                    upTgl = register.get( 0 ).getTanggalLahir();
//                    upInstagram = register.get( 0 ).getInstagram();
//
//
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseRegister> call, Throwable t) {
//
//            }
//        } );
//    }

    private void update( String pass){
        progressBar.setVisibility( View.VISIBLE );

        Call<ResponseRegister> call = InitRetrofit.getInstance().updatePass(Prefs.getString( SharedPreff.getIdKonsumen(),""),pass);
        call.enqueue( new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                ResponseRegister preff = response.body();


                if (response.isSuccessful()&& response.body() != null){
                    Boolean sukses = response.body().isStatus();
                    register = preff.getData();
                    if (sukses ){
                        Toast.makeText( SetingProfileActivity.this, "Sukses " + response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        setPrefference();
                        finish();

                    }else {
                        Toast.makeText( SetingProfileActivity.this, "Gagal " + response.body().getMessage(), Toast.LENGTH_SHORT ).show();
                        progressBar.setVisibility( View.GONE );
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                progressBar.setVisibility( View.GONE );
                Toast.makeText( SetingProfileActivity.this, "Eror", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
    public void setPrefference() {

        Prefs.putString(SharedPreff.getPASSWORD(), repPass.getText().toString() );


    }
}
