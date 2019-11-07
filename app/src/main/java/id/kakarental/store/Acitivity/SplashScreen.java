package id.kakarental.store.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import id.kakarental.store.R;

public class SplashScreen extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );

        iv = findViewById( R.id.iv_splash );

        Animation splash = AnimationUtils.loadAnimation( this, R.anim.splash );
        iv.startAnimation( splash );

        final Intent i = new Intent( this, LoginActivity.class );
        Thread timer = new Thread(  ){
            public void run(){
                try{
                    sleep( 2000 );

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity( i );
                    finish();
                }
            }
        };
        timer.start();
    }
}
