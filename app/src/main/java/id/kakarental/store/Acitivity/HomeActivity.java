package id.kakarental.store.Acitivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.CarouselViewPagerTransformer;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import id.kakarental.store.Model.CartModel.DataItemCart;
import id.kakarental.store.Model.CartModel.ResponseCart;
import id.kakarental.store.Model.banner.BannerItem;
import id.kakarental.store.Model.banner.ResponseBanner;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import id.kakarental.store.Utils.Move;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements DroidListener {

    private static final String TAG = "HomeActivity";

    CardView sewa, jual, iklan, akun, bantuan, status, keranjang, kategori, sedang, ip;
    boolean doubleBackToExitPressedOnce = false;
    private DroidNet mDroidNet;
    ImageView notifikasi;
    CarouselView carouselView;
    ArrayList<String> image;
    int[] sampleImages = {R.drawable.placeholder_fail, R.drawable.placeholder_fail};
    String[] sampleNetworkImageURLs;
    TextView badgeCart;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);

        view();
        getbanner();
        getCart();
        initButton();
    }

    private void view() {
        carouselView = findViewById(R.id.banner);

        notifikasi = findViewById(R.id.notifikasi);
        badgeCart = findViewById(R.id.tv_iconCartBadge);
        sewa = findViewById(R.id.form1);
        jual = findViewById(R.id.form2);
        iklan = findViewById(R.id.form3);
        kategori = findViewById(R.id.form4);
        keranjang = findViewById(R.id.form5);
        status = findViewById(R.id.form6);
        sedang = findViewById(R.id.form7);
        akun = findViewById(R.id.form8);
        bantuan = findViewById(R.id.form9);

        progressBar = findViewById(R.id.progressBar2);

        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sampleNetworkImageURLs[position]).placeholder(sampleImages[0])
                    .error(sampleImages[1]).fit().into(imageView, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
    };

    private void getCart() {

        Call<ResponseCart> cart = InitRetrofit.getInstance().getCart(Prefs.getString(SharedPreff.getIdKonsumen(), null));

        cart.enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {

                ResponseCart res = response.body();

                if (!response.isSuccessful()) {

                    return;
                } else if (res.isStatus()) {
                    List<DataItemCart> items = res.getData();
                    int size = items.size();
                    setbadge(size);

                } else {
                    setbadge(0);
                }

            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                getCart();

            }
        });
    }

    private void initButton() {
        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Move.move(HomeActivity.this, NotifikasiActivity.class);
            }
        });

        jual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, All_itemActivity.class);
                i.putExtra("data", "Jual");
                startActivity(i);
            }
        });

        iklan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, All_itemActivity.class);
                i.putExtra("data", "Iklan");
                startActivity(i);
            }
        });

        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, All_itemActivity.class);
                i.putExtra("data", "Sewa");
                startActivity(i);
            }

        });

        keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.move(HomeActivity.this, KeranjangActivity.class);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.move(HomeActivity.this, StatusPesananActivity.class);
            }
        });
        sedang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.move(HomeActivity.this, DaftarSewa_Activity.class);
            }
        });

        akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.move(HomeActivity.this, SettingActivity.class);
            }
        });
        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Move.move(HomeActivity.this, BantuanActivity.class);
            }
        });
        kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, KategoriActivity.class);
                startActivity(i);
            }
        });

//        ip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
//                LayoutInflater inflater = HomeActivity.this.getLayoutInflater();
//                View views = inflater.inflate(R.layout.z_ip_dialog, null);
//
//                TextView status = views.findViewById(R.id.tv_statusPerpanjang);
//                final EditText ip = views.findViewById(R.id.ip_user);
//                Button button = views.findViewById(R.id.save);
//                Button button1 = views.findViewById(R.id.web);
//                status.setText(Prefs.getString(SharedPreff.getSTATUS(), ""));
//
//                dialog.setView(views);
//
//                dialog.show();
//
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        Prefs.putString(SharedPreff.getIP(), ip.getText().toString());
//                        Prefs.putString(SharedPreff.getSTATUS(), "ip");
//                        Prefs.edit();
//
//                        Toast.makeText(HomeActivity.this, Prefs.getString(SharedPreff.getIP(), null)
//                                + " " + Prefs.getString(SharedPreff.getSTATUS(), null), Toast.LENGTH_SHORT).show();
//
//                        dialog.dismiss();
//                        recreate();
//                    }
//                });
//
//                button1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Prefs.putString(SharedPreff.getSTATUS(), "web");
//                        Prefs.edit();
//
//                        Toast.makeText(HomeActivity.this, Prefs.getString(SharedPreff.getSTATUS(), null), Toast.LENGTH_SHORT).show();
//
//                        dialog.dismiss();
//                        recreate();
//                    }
//                });
//
//
//            }
//        });
    }

    private void getbanner() {

        Call<ResponseBanner> banner = InitRetrofit.getInstance().banner();
        banner.enqueue(new Callback<ResponseBanner>() {
            @Override
            public void onResponse(Call<ResponseBanner> call, Response<ResponseBanner> response) {
                ResponseBanner res = response.body();

                if (response.isSuccessful()) {
                    if (res.isStatus()) {
                        List<BannerItem> list = res.getBanner();
                        image = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            image.add(InitRetrofit.getIMG_URL() + list.get(i).getGambar());
                        }
                        sampleNetworkImageURLs = image.toArray(new String[image.size()]);

                        carouselView.setPageCount(sampleNetworkImageURLs.length);
                        carouselView.setPageTransformInterval(1000);
                        carouselView.setPageTransformer(CarouselViewPagerTransformer.DEPTH);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBanner> call, Throwable t) {

            }
        });

    }

    private void setbadge(int size) {
        if (size > 0) {
            badgeCart.setVisibility(View.VISIBLE);
            badgeCart.setText(Integer.toString(size));
        } else {
            badgeCart.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }


    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {
            //do Stuff with internet

//            Snackbar.with(this, null)
//                    .type(Type.SUCCESS)
//                    .message("Profile updated successfully!")
//                    .duration(Duration.SHORT)
//                    .fillParent(true)
//                    .textAlign(Align.LEFT)
//                    .show();

//            Fragment_Dasboard dasboard = (Fragment_Dasboard) adapter.getItem(0);
//            dasboard.initMainAct();


//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        } else {
            //no internet

            Snackbar.with(this, null)
                    .type(Type.WARNING)
                    .message("Tidak ada koneksi internet!")
                    .duration(Duration.INFINITE)
                    .fillParent(true)
                    .textAlign(Align.LEFT)
                    .show();

//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }
}
