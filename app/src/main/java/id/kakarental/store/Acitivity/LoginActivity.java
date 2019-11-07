package id.kakarental.store.Acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.pixplicity.easyprefs.library.Prefs;

import id.kakarental.store.Model.auth.DataLogin;
import id.kakarental.store.Model.auth.ResponseLogin;
import id.kakarental.store.Network.InitRetrofit;
import id.kakarental.store.R;
import id.kakarental.store.SharedPrefereced.SharedPreff;
import id.kakarental.store.Utils.Move;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    Button login;
    TextView lupa, regis;
    EditText username, paswd;
    DataLogin dataLogin;
    Context context;
    ProgressDialog dialog;
    String name;
    String pass;
    String token;
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        startinit();
    }

    private void startinit() {

        String username = Prefs.getString(SharedPreff.getUSERNAME(),null);
        String Password = Prefs.getString(SharedPreff.getPASSWORD(),null);

//        if (Prefs.getString(SharedPreff.getUSERNAME(), null) != null){
//            checkLogin(username, Password);
//            dialog.show();
//        }else {
//            initUI();
//        }

        if (Prefs.getString(SharedPreff.getUSERNAME(), null) == null) {
            initUI();
            return;
        }
        Move.move(context, HomeActivity.class);
        finish();
    }

    private void initUI() {
        container = findViewById(R.id.container);
        login = findViewById(R.id.btn_login);
        lupa = findViewById(R.id.tv_lupaPass);
        regis = findViewById(R.id.tv_createAcount);
        username = findViewById(R.id.et_username);
        paswd = findViewById(R.id.et_password);



        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(LoginActivity.this, RegistrasiActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = String.valueOf(username.getText());
                pass = String.valueOf(paswd.getText());

                if (username.equals("")) {
                    username.setError("Username Kosong");
                } else if (paswd.equals("")) {
                    paswd.setError("Password Kosong");
                } else {
                    dialog.show();
                    checkLogin(name, pass);
                }
            }
        });
    }

    private void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        // Log and toast
                        String msg = "Token Result : " + token;
                        Log.d(TAG, msg);
                       //Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkLogin(String usname, String uspass) {

        Call<ResponseLogin> check = InitRetrofit.getInstance().getUser(usname, uspass, token);

        check.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                dialog.dismiss();
                ResponseLogin login = response.body();
                Boolean status = login.isStatus();
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!status) {
                    Toast.makeText(LoginActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                dataLogin = login.getData();
                Log.d(TAG, "onResponse: " + dataLogin.getAlamatLengkap());
                Prefs.clear();
                setPrefference(dataLogin);
                getToken();
                Move.move(LoginActivity.this, HomeActivity.class);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "Koneksi Tidak ada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPrefference(DataLogin data) {
        Prefs.putString(SharedPreff.getIdKonsumen(), data.getIdKonsumen());
        Prefs.putString(SharedPreff.getUSERNAME(), data.getUsername());
        Prefs.putString(SharedPreff.getPASSWORD(), paswd.getText().toString());
        Prefs.putString( SharedPreff.getFoto_ktp(), data.getFotoktp() );
        Prefs.putString(SharedPreff.getNamaLengkap(), data.getNamaLengkap());
        Prefs.putString(SharedPreff.getEMAIL(), data.getEmail());
        Prefs.putString(SharedPreff.getJenisKelamin(), data.getJenisKelamin());
        Prefs.putString(SharedPreff.getTanggalLahir(), data.getTanggalLahir());
        Prefs.putString(SharedPreff.getTempatLahir(), data.getTempatLahir());
        Prefs.putString(SharedPreff.getAlamatLengkap(), data.getAlamatLengkap());
        Prefs.putString(SharedPreff.getIdKota(), data.getKotaId());
        Prefs.putString(SharedPreff.getNoHp(), data.getNoHp());
    }
}
