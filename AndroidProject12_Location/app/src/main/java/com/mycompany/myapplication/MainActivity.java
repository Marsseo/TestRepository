package com.mycompany.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LocationManager locationManager;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private List<LatLng> listLocation = new ArrayList<>();
    private Polyline polyline;
    private LatLng currlatlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();


    }

    public void checkPermission(){

        int permissionGPS = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionNetwork = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permissionGPS != PackageManager.PERMISSION_GRANTED || permissionNetwork != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1
            );
        }
    }


    public void handleBtnCurrentLocation(View v){
        checkPermission();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                locationManager.NETWORK_PROVIDER,
                10000,
                0,
                locationListener
        );


    }

    private LocationListener locationListener = new LocationListener(){

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.i(TAG, "위도: "+latitude);
            Log.i(TAG, "경도: "+longitude);
            locationManager.removeUpdates(locationListener);
            showMap(latitude, longitude);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        @Override
        public void onProviderEnabled(String provider) {}
        @Override
        public void onProviderDisabled(String provider) {}
    };

    public void handleBtnFromAddressToLcation(View v){
        String url = "http://apis.daum.net/local/geo/addr2coord";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("apikey", "ed39f1a2d5d0ef6900f484030523afe2")
                .addQueryParameter("q", "서울특별시 강동구 구천면로47길 45")
                .addQueryParameter("output", "json")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.i(TAG, e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                // \u00aa 등을 한글로 변환하기 위해 StringEscapeUtils 사용
                // compile 'commons-lang:commons-lang:2.6' 추가해야 함
                final String json = StringEscapeUtils.unescapeJava(response.body().string());
                Log.i(TAG, json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    jsonObject = jsonObject.getJSONObject("channel");
                    JSONArray jsonArray = jsonObject.getJSONArray("item");
                    jsonObject = jsonArray.getJSONObject(0);
                    double latitude = jsonObject.getDouble("lat");
                    double longitude = jsonObject.getDouble("lng");
                    Log.i(TAG, "위도 : "+latitude);
                    Log.i(TAG, "경도 : "+longitude);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void handleBtnMap(View v){

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MainActivity.this.googleMap = googleMap;

                handleBtnCurrentLocation(null);
            }
        });

    }

    private void showMap(double latitude,double longitude){

        currlatlng = new LatLng(latitude, longitude);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currlatlng, 16));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        checkPermission();
        googleMap.setMyLocationEnabled(true);

        // 지도를 드로잉하기 위한 코드
        listLocation.clear();
        listLocation.add(currlatlng);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                listLocation.add(latLng);
                drawingPath();
            }
        });

    }

    public void handleBtnCurrLocationReturn(View v){
        listLocation.add(currlatlng);
        drawingPath();
    }

    public void handleBtnRemovePath(View v){
        listLocation.clear();
        listLocation.add(currlatlng);
        polyline.remove();
    }

    private void drawingPath(){
        if(polyline!=null) polyline.remove();

        LatLng[] paths = new LatLng[listLocation.size()];
        PolylineOptions polylineOptions = new PolylineOptions()
                .add(listLocation.toArray(paths))
                .width(10)
                .color(Color.RED);

        polyline = googleMap.addPolyline(polylineOptions);
    }

}
