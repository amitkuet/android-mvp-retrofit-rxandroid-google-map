package app.engine.android.activity.authentication;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.activity.BaseUIController;
import app.engine.android.presenter.authentication.LoginPresenter;
import app.engine.android.view.authentication.LoginIterface;

public class LoginActivity extends BaseUIController implements LoginIterface, View.OnClickListener {

    private LoginPresenter loginPresenter;
    private LinearLayout mainOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setLayoutType(AppEngine.getInstance().constants.EMPTY_LAYOUT);
        super.onCreate(savedInstanceState);
        this.addLayout(R.layout.authentication_login_coreapp);
        this.init();
    }

    private void init() {
        this.mainOverlay = (LinearLayout) this.findViewById(R.id.mainOverlay);
        this.mainOverlay.setOnClickListener(this);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        AppEngine.getInstance().googleMapView.requestPermissionCallback(requestCode, grantResults, this);
    }

    @Override
    public <T> void openActivity(Class <T> activityClass) {

    }

    @Override
    public void loginAPI() {

    }

    @Override
    public void error() {

    }

    @Override
    public void onClick(View view) {
        if(view.equals(this.mainOverlay)) {

        }
    }
}
