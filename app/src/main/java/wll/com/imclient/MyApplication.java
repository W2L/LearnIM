package wll.com.imclient;

import android.app.Application;
import android.widget.Toast;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        int vCode = BuildConfig.VERSION_CODE;
        String vName = BuildConfig.VERSION_NAME;
        Toast.makeText(this, "vCode=" + vCode + "  vName=" + vName, Toast.LENGTH_LONG).show();
    }
}
