package app.engine.android.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import app.engine.android.AppEngine;
import app.engine.android.BuildConfig;
import app.engine.android.R;

public class GoogleMapView implements OnMapReadyCallback, LocationListener {

    private static final int LOCATION_UPDATE_MIN_DISTANCE = 0;
    private static final int LOCATION_UPDATE_MIN_TIME = 0;

    private MapView mapView;
    private GoogleMap googleMap;
    private Bundle savedInstanceState;
    private LocationManager mLocationManager;
    private Context context;
    private boolean permission;

    private static final String MAP_VIEW_BUNDLE_KEY = AppEngine.getInstance().constants.GOOGLE_API_KEY;

    public GoogleMapView() {

    }

    private void initView(Context context, MapView mapView, Bundle savedInstanceState, boolean permission) {
        this.context = context;
        this.savedInstanceState = savedInstanceState;
        Bundle mapViewBundle = null;
        if (this.savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        this.mapView = mapView;
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
        this.permission = permission;
        if(permission) {
            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            getCurrentLocation();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        if(this.permission) {
            getCurrentLocation();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("location: ","changed");
        if (location != null) {
            //drawMarker(location);
            drawMarkerOffset(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    private void getCurrentLocation() {
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled))
            Snackbar.make(this.mapView,"error location provider", Snackbar.LENGTH_INDEFINITE).show();
        else {
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                this.mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, this);
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, this);
                location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        if (location != null) {
            //drawMarker(location);
            drawMarkerOffset(location);
        }
    }

    private void drawMarker(Location location) {
        String currentPosition = context.getResources().getString(R.string.share_location_cur_loc_text);
        if (this.googleMap != null) {
            this.googleMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            this.googleMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title(currentPosition));
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));
        }
    }

    private void drawMarkerOffset(Location location) {
        String currentPosition = context.getResources().getString(R.string.share_location_cur_loc_text);
        if (this.googleMap != null) {
            this.googleMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            this.googleMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title(currentPosition));

            LatLng target = new LatLng(location.getLatitude(), location.getLongitude());
            Projection projection = googleMap.getProjection();
            Point screenLocation = projection.toScreenLocation(target);
            screenLocation.x += 100;
            screenLocation.y += 100;
            LatLng offsetTarget = projection.fromScreenLocation(screenLocation);
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(offsetTarget, 12));
        }
    }
    public void requestPermissionCallback(int requestCode, int[] grantResults, Activity activity) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppEngine.getInstance().googleMapView.initView(activity, (MapView) activity.findViewById(R.id.mapView), savedInstanceState, true);
                } else {
                    AppEngine.getInstance().googleMapView.initView(activity, (MapView) activity.findViewById(R.id.mapView), savedInstanceState, false);
                }
                AppEngine.getInstance().googleMapView.mapView.onStart();
            }
        }
    }
}
