package com.example.a2301876316;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2301876316.models.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

class MarkerLocation{
    private String name;
    private JSONObject location;
    private double lat;
    private double lng;

    public String getName(){
        return name;
    }

    public JSONObject getLocation(){
        return location;
    }

    public double getLat(){
        return lat;
    }

    public double getLng(){
        return lng;
    }

    public MarkerLocation(String name, JSONObject location, double lat, double lng){
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
    }
}

public class AboutUsActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    ImageButton arrowBack;
    User user;

    private final String url = "https://raw.githubusercontent.com/acad600/JSONRepository/master/ISYS6203/O212-ISYS6203-SO02-00-BlueDoll.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJSONBlueDollsLocation();
        setContentView(R.layout.about_us_activity);
        init();
        onClick();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_maps);
        mapFragment.getMapAsync(this);
    }

    private void init(){
        user = (User)getIntent().getSerializableExtra("user");
        arrowBack = findViewById(R.id.arrowBack);
    }

    private void onClick(){
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }

    ArrayList<MarkerLocation>markerLists = new ArrayList<>();

    private void getJSONBlueDollsLocation(){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("markers");
                            for (int i = 0; i <jsonArray.length();i++){
                                JSONObject markers = jsonArray.getJSONObject(i);
                                System.out.println(markers);
                                String name = markers.getString("name");
                                JSONObject location = markers.getJSONObject("location");
                                double lat = location.getDouble("lat");
                                double lng = location.getDouble("lng");

                                MarkerLocation marker = new MarkerLocation(name, location, lat, lng);
                                markerLists.add(marker);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<Marker> maps = new ArrayList<>();

        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(14.0f);

        for (MarkerLocation marker: markerLists) {
            Marker marker1 = mMap.addMarker(new MarkerOptions()
            .position(new LatLng(marker.getLat(), marker.getLng()))
            .title(marker.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(marker.getLat(), marker.getLng()), 15));
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(marker.getLat(), marker.getLng()))
                    .zoom(17)
                    .bearing(90)
                    .tilt(30)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            maps.add(marker1);
        }

        for(int i = 0 ; i < maps.size() ; i++){
//            maps.get(i).moveCamera(CameraUpdateFactory.newLatLng(new LatLng(maps, marker.getLng())));
            maps.get(i).setTag(0);
        }


    }
}
