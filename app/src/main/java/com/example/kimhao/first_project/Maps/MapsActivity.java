package com.example.kimhao.first_project.Maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
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

    private Animation slide_out_down, slide_in_up;
    public static GoogleMap map;
    private SupportMapFragment supportMapFragment;
    Marker previousSelectedMarker;
    private static ViewPager event_pager;

    CustomMap customMap;

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

//        slide_out_down = AnimationUtils.loadAnimation(MapsActivity.this, R.anim.slide_out_down);
//        slide_in_up = AnimationUtils.loadAnimation(MapsActivity.this, R.anim.slide_in_up);

        event_pager = (ViewPager) findViewById(R.id.viewPagerMap);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                map = googleMap;
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                customMap = new CustomMap(map, latLngsArrayList, MapsActivity.this);
//
//                try {
//                    customMap.setCustomMapStyle(R.raw.mapstyle);
//                    // Customise the styling of the base map using a JSON object defined in a raw resource file.
//                } catch (Resources.NotFoundException e) {
//                    Log.e("Explore detail activity", "Can't find style. Error: " + e);
//                }

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
                Point mappoint = map.getProjection().toScreenLocation(
                        new LatLng(location.getLatitude(), location.getLongitude()));
                Toast.makeText(MapsActivity.this, location.getLatitude()+ " " +location.getLongitude(), Toast.LENGTH_SHORT).show();
                mappoint.set(mappoint.x, mappoint.y - 20);
                map.animateCamera(CameraUpdateFactory.newLatLng(map.getProjection().fromScreenLocation(mappoint)));
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
//                    try {
//                        if (previousSelectedMarker != null) {
//
//                            //MyLocation location = latLngsArrayList.get(mPosition);
//
////                            if (map.getCameraPosition().zoom >= 10) {
////                                previousSelectedMarker.setIcon(BitmapDescriptorFactory.fromBitmap(
////                                        BitmapFactory.decodeResource(getResources(),
////                                                R.drawable.ic_place_red_900_24dp)));
////                            } else if (map.getCameraPosition().zoom < 10) {
//                                previousSelectedMarker.setIcon(BitmapDescriptorFactory.fromBitmap(
//                                        BitmapFactory.decodeResource(getResources(),
//                                                R.drawable.ic_backspace_deep_purple_900_24dp)));
////                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory.decodeResource(getResources(),
                                    R.drawable.ic_place_brown_500_36dp)));

                    previousSelectedMarker = marker;

                    if (event_pager.getVisibility() != View.VISIBLE) {

                        event_pager.startAnimation(slide_in_up);
                        slide_in_up.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation arg0) {

                                event_pager.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation arg0) {

                            }

                            @Override
                            public void onAnimationEnd(Animation arg0) {

                            }
                        });
                        event_pager.setCurrentItem(mPosition, true);

                    } else {
                        event_pager.setCurrentItem(mPosition, true);
                    }

                    return false;
                }
            });

//            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                @Override
//                public void onMapClick(LatLng latLng) {
//
//                    if (event_pager.getVisibility() == View.VISIBLE) {
//                        event_pager.startAnimation(slide_out_down);
//
//                        slide_out_down.setAnimationListener(new Animation.AnimationListener() {
//                            @Override
//                            public void onAnimationStart(Animation arg0) {
//
//                            }
//                            @Override
//                            public void onAnimationRepeat(Animation arg0) {
//
//                            }
//                            @Override
//                            public void onAnimationEnd(Animation arg0) {
//                                event_pager.setVisibility(View.GONE);
//                                event_pager.clearAnimation();
//                            }
//                        });
//                    }
//                }
//            });

            //return the position on the viewpager
            map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    MyLocation location = latLngsArrayList.get(event_pager.getCurrentItem());
                    Point mappoint = map.getProjection().toScreenLocation(
                            new LatLng(location.getLatitude(), location.getLongitude()));
                    mappoint.set(mappoint.x, mappoint.y - 20);
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
