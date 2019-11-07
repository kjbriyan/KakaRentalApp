package id.kakarental.store.Acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.kakarental.store.R;
import id.kakarental.store.Utils.Move;

public class KategoriActivity extends AppCompatActivity {

    ImageView back;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_kategori );
        view();
    }

    private void view(){
        back = findViewById( R.id.img_back );

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);


        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        tv1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "baby-box--boks-bayi");
            }
        } );

        tv2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "bouncer--ayunan-bayi-manual");
            }
        } );
        tv3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "baby-walker");
            }
        } );
        tv4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "bouncer--ayunan-bayi-manual");
            }
        } );
        tv4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "car-seat");
            }
        } );
        tv5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "car-seat");
            }
        } );
        tv6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "high-chair--kursi-makan");
            }
        } );

        tv7.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "push-walker--dorongan");
            }
        } );

        tv8.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "stroller--kereta-dorong");
            }
        } );

        tv9.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "perosotan-anak-musikal-mainan-anak");
            }
        } );

        tv10.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.moveData(KategoriActivity.this, Kategori_ItemActivity.class,
                        "data", "piano--keyboard-mainan-anak");
            }
        } );
    }


}
