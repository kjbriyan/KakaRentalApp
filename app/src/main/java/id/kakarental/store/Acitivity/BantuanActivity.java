package id.kakarental.store.Acitivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.kakarental.store.R;

public class BantuanActivity extends AppCompatActivity {
    TextView ig,no,email,fb,web;
    ImageView back,logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bantuan );

        ig = findViewById( R.id.tv_igkk);
        no = findViewById( R.id.tv_nokk );
        email = findViewById( R.id.tv_emailkk );
        fb = findViewById( R.id.tv_fvkk );
        web = findViewById( R.id.tv_webkk );
        back = findViewById( R.id.back );
        logo = findViewById( R.id.logoisun );

        logo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","developerisun@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Pesan aplikasi");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Deskripsi aplikasi dan budget");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        ig.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = InstagramIntent( BantuanActivity.this );
                finish();
                startActivity( i );

            }
        } );
        no.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = TeleponIntent( BantuanActivity.this );
                finish();
                startActivity( i );

            }
        } );
        email.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = EmailIntent( BantuanActivity.this );
                finish();
                startActivity( i );

            }
        } );
        fb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = FacebookIntent( BantuanActivity.this );
                finish();
                startActivity( i );

            }
        } );
        web.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = InternetIntent( BantuanActivity.this );
                finish();
                startActivity( i );

            }
        } );


    }
    public static Intent TeleponIntent(Context context) {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+62 82299996281"));
        try {
            context.startActivity(i);
            return i;
        } catch (Exception e) {
            // this can happen if the device can't make phone calls
            // for example, a tablet
            return i;
        }
    }



    public static Intent EmailIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.gm", 0)  ; //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://mail.google.com/")); //Trys to make intent with Instagram's URI
        }
        catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://mail.google.com/")); //catches and opens a url to the desired page
        }
    }

    public static Intent InstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.android.chrome", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/kaka_rentalperlengkapanbayi/")); //Trys to make intent with Instagram's URI
        }
        catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/kaka_rentalperlengkapanbayi/")); //catches and opens a url to the desired page
        }
    }

    public static Intent FacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0)  ; //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/Kaka-Rental-Perlengkapan-Bayi-170963929671058/")); //Trys to make intent with Instagram's URI
        }
        catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/Kaka-Rental-Perlengkapan-Bayi-170963929671058/")); //catches and opens a url to the desired page
        }
    }

    public static Intent InternetIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.android.chrome", 0)  ; //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.lagiviral.web.id/")); //Trys to make intent with Instagram's URI
        }
        catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.lagiviral.web.id/")); //catches and opens a url to the desired page
        }
    }
}
