package id.kakarental.store.Acitivity;

import android.content.Intent;
import android.service.autofill.TextValueSanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import id.kakarental.store.R;
import id.kakarental.store.Utils.Move;

public class SettingActivity extends AppCompatActivity {
    TextView datadiri, alamat,pswd,ktp;
    Button logout;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_setting );

        datadiri = findViewById( R.id.tv_stdatadiri );
        alamat = findViewById( R.id.tv_stalamat );
        pswd = findViewById( R.id.tv_stpasword );
//        ktp = findViewById( R.id.tv_stktp );

        back = findViewById( R.id.img_back );
        logout = findViewById( R.id.btn_logout );
//
//        ktp.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Move.move( SettingActivity.this, UpdateKtpActivity.class );
//            }
//        } );

        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.clear();
                SettingActivity.this.finish();
                Move.move( SettingActivity.this, LoginActivity.class );

            }
        } );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        datadiri.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( SettingActivity.this, AcountActivity.class ) );
            }
        } );

        alamat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( SettingActivity.this, PilihAlamatActivity2.class ) );
            }
        } );

        pswd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.move( SettingActivity.this,SetingProfileActivity.class );

            }
        } );

 
    }
}
