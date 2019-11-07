package id.kakarental.store.Service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.renderscript.RenderScript;

import com.droidnet.DroidNet;
import com.pixplicity.easyprefs.library.Prefs;

import net.danlew.android.joda.JodaTimeAndroid;

import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;

public class App extends Application {
    public static final String CHANEL_ID = "CHANEL 1";

    @Override
    public void onCreate() {
        super.onCreate();

        createChanelNotif();
        JodaTimeAndroid.init(this);
        DroidNet.init(this);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    private void createChanelNotif() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANEL_ID,
                    "Chanel satu",
                    NotificationManager.IMPORTANCE_HIGH
            );

            channel.setDescription("CHANEL SATU");

            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
            channel.getImportance();

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners();
    }
}
