package com.example.kimhao.first_project.Maps;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.kimhao.first_project.Adapter.UserViewPagerAdapter;
import com.example.kimhao.first_project.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by KimHao on 31/03/2017.
 */

public class MapsActivity extends AppCompatActivity implements LocationListener {
    private GoogleMap myMap;
    private ProgressDialog myProgress;
    private static final String MYTAG = "MYTAG";
    private static final int REQUEST_ID_ACCESS_COURNSE_FINE_LOCATION = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ViewPager viewPagerMap = (ViewPager) findViewById(R.id.viewPagerMap);
        viewPagerMap.setClipToPadding(false);
        viewPagerMap.setPageMargin(20);
        UserViewPagerAdapter mUserViewPagerAdapter = new UserViewPagerAdapter(getSupportFragmentManager(),getBaseContext());
        viewPagerMap.setAdapter(mUserViewPagerAdapter);
        viewPagerMap.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mUserViewPagerAdapter.notifyDataSetChanged();
        //create progess bar;
        myProgress = new ProgressDialog(this);
        myProgress.setTitle("Map loading...");
        myProgress.setMessage("Please wait");
        myProgress.setCancelable(true);

        //show progess
        myProgress.show();

        SupportMapFragment mapFragment =  (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        //set event location map ready
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                onMyReady(googleMap);
            }
        });

    }

    private void onMyReady(GoogleMap googleMap) {
        myMap = googleMap;

        myMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                myProgress.dismiss();
                askPermissionsAndShowMyLocation();
            }
        });

        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        myMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        myMap.setMyLocationEnabled(true);
    }

    private void askPermissionsAndShowMyLocation() {
        if (Build.VERSION.SDK_INT >= 23){
            int accessCoarsePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFinePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED
                    || accessFinePermission != PackageManager.PERMISSION_GRANTED){

                //set permission to user
                String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};

                //show dialog
                ActivityCompat.requestPermissions(this,permissions,REQUEST_ID_ACCESS_COURNSE_FINE_LOCATION);
                return;
            }
        }
        this.showMyLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_ID_ACCESS_COURNSE_FINE_LOCATION:{
                if (grantResults.length >1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    this.showMyLocation();
                }else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    //find provider
    private String getEnabledLocationProvider(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String bestProvider = locationManager.getBestProvider(criteria,true);

        boolean enable = locationManager.isProviderEnabled(bestProvider);
        if (!enable){
            Toast.makeText(this, "No location provider enable", Toast.LENGTH_SHORT).show();
            Log.i(MYTAG, "getEnabledLocationProvider: ");
            return null;
        }
        return bestProvider;
    }

    private void showMyLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String locationProvider = this.getEnabledLocationProvider();
        if (locationManager == null) {
            return;
        }
        final long MIN_TIME_BW_UPDATES = 1000;
        final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
        Location myLocation = null;
        try {

            // Đoạn code nay cần người dùng cho phép (Hỏi ở trên ***).
            locationManager.requestLocationUpdates(
                    locationProvider,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);

            // Lấy ra vị trí.
            myLocation = locationManager
                    .getLastKnownLocation(locationProvider);
        }
        // Với Android API >= 23 phải catch SecurityException.
        catch (SecurityException e) {
            Toast.makeText(this, "Show My Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(MYTAG, "Show My Location Error:" + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (myLocation != null) {

            LatLng latLng = new LatLng(16.075658, 108.233709);
            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
            myMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(16.075658, 108.233709))             // Sets the center of the map to location user
                            .zoom(15)                   // Sets the zoom
                            .bearing(90)                // Sets the orientation of the camera to east
                            .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    return true;
                }
            });
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)             // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            // Thêm Marker cho Map:
            MarkerOptions option = new MarkerOptions();
            option.title("My Location");
            option.snippet("....");
            option.position(latLng);
            Marker currentMarker = myMap.addMarker(option);
            currentMarker.showInfoWindow();
        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_LONG).show();
            Log.i(MYTAG, "Location not found");
        }
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
