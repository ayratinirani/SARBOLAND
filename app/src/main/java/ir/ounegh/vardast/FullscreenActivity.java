package ir.ounegh.vardast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements LocationListener{
    /**/
    private static final int REQUEST_LOCATION = 0;
    private static final String[] PERMISSION_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION};
    private LocationManager locManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ProgressBar pb=new ProgressBar(this);



        //Check if the Location permission is already available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //Permission not granted.
            requestLocationPermission();

        } else {
            //Permission granted.
            initLocManager();
        }

    }





    private void requestLocationPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            //Permission has been previously denied.
            //Show message...
        } else {
            //Permission has not been granted yet. It is requested directly.
            ActivityCompat.requestPermissions(this, PERMISSION_LOCATION, REQUEST_LOCATION);
        }
    }


    /**
     * Callback.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_LOCATION) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Permission granted.
                initLocManager();
            } else {
                //Permission not granted.
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public void initLocManager(){

        //Sample code...



        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if(locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                }
            }else{
                locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        } else{
            Toast.makeText(this, "No location services", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}


    @Override
    public void onLocationChanged(Location location) {

        removeUpdatesLocListener();
        startActivity(new Intent(this,MainActivity.class).putExtra("latitude",location.getLatitude()).putExtra("longitude",location.getLongitude()));
       finish();
    }


    private void removeUpdatesLocListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
                if(locManager!=null)
                    locManager.removeUpdates(this);
            }
        }else{
            if(locManager!=null)
                locManager.removeUpdates(this);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        removeUpdatesLocListener();
    }

}
