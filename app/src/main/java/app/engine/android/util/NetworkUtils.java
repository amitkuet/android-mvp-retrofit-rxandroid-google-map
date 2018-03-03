package app.engine.android.util;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkUtils extends BroadcastReceiver{

    NetworkUtilsInterface networkUtilsInterface;

    public NetworkUtils(){
        this.networkUtilsInterface=null;
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if(networkUtilsInterface!=null && isNetworkConnected(context)) {
            this.networkUtilsInterface.networkConnect();
        } else if(networkUtilsInterface!=null && !isNetworkConnected(context)) {
            this.networkUtilsInterface.networkDisconnect();
        }
    }
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void setNetworkUtilsInterface(NetworkUtilsInterface networkUtilsInterface) {
        this.networkUtilsInterface = networkUtilsInterface;
    }
}
