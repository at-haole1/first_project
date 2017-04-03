package com.example.kimhao.first_project.Maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.kimhao.first_project.Maps.customstyledmap.CustomMap;
import com.example.kimhao.first_project.Maps.model.MyLocation;
import com.example.kimhao.first_project.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {
    private ArrayList<MyLocation> latLngsArrayList;
    private static GoogleMap map;
    private SupportMapFragment supportMapFragment;
    private static ViewPager event_pager;

    private CustomMap customMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setContents();
    }

    private void setContents() {
        latLngsArrayList = new ArrayList<>();
        latLngsArrayList.clear();
        latLngsArrayList = GlobalUtils.getLatLongArray();

        event_pager = (ViewPager) findViewById(R.id.viewPagerMap);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                customMap = new CustomMap(map, latLngsArrayList, MapsActivity.this);
                handleMap();
                customMap.addCustomPin();
                event_pager.setAdapter(new MapViewPagerAdapter(MapsActivity.this,
                        latLngsArrayList));
            }
        });


        event_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                MyLocation location = latLngsArrayList.get(position);
                Point mapPoint = map.getProjection().toScreenLocation(
                        new LatLng(location.getLatitude(), location.getLongitude()));
                Toast.makeText(MapsActivity.this, location.getLatitude()+ " " +location.getLongitude(), Toast.LENGTH_SHORT).show();
                mapPoint.set(mapPoint.x, mapPoint.y - 50);
                map.animateCamera(CameraUpdateFactory.newLatLng(map.getProjection().fromScreenLocation(mapPoint)));
                customMap.addSelectedCustomPin(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void handleMap() {
        if (map != null) {
            if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);
                return;
            }
            map.setMyLocationEnabled(true);

            map.getUiSettings().setMapToolbarEnabled(false);
            map.getUiSettings().setZoomControlsEnabled(false);

            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    final int mPosition = (int) marker.getTag();

                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory.decodeResource(getResources(),
                                    R.drawable.ic_place_red_500_36dp)));
                    event_pager.setCurrentItem(mPosition, true);
                    return false;
                }
            });

            //return the position on the viewpager
            map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    MyLocation location = latLngsArrayList.get(event_pager.getCurrentItem());
                    Point mappoint = map.getProjection().toScreenLocation(
                            new LatLng(location.getLatitude(), location.getLongitude()));
                    mappoint.set(mappoint.x, mappoint.y - 50);
                    map.animateCamera(CameraUpdateFactory.newLatLng(map.getProjection().fromScreenLocation(mappoint)));
                    customMap.addSelectedCustomPin(event_pager.getCurrentItem());

                    return true;
                }
            });
        } else {
            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    customMap = new CustomMap(map, latLngsArrayList, MapsActivity.this);
                    Toast.makeText(MapsActivity.this,"aaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                    handleMap();
                }
            });

        }
    }


}
