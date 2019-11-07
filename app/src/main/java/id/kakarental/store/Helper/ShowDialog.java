package id.kakarental.store.Helper;

import android.app.ProgressDialog;
import android.content.Context;

public class ShowDialog {

    public static ProgressDialog showDialog(Context context){
        ProgressDialog dialog  = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Loading . . .");

        return dialog;

    }

}
