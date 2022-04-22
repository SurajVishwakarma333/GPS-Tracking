package com.example.gpstrackingdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gpstrackingdemo.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    List<Location> savedLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MyApplication myApplication = (MyApplication)getApplicationContext();
        savedLocations = myApplication.getMyLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       // Add a marker in India and move the camera
       LatLng sydney = new LatLng(19, 72);
       //mMap.addMarker(new MarkerOptions().position(India).title("Marker in India"));
       //mMap.moveCamera(CameraUpdateFactory.newLatLng(India));

        LatLng lastlocationPlaced = sydney;

        for (Location location: savedLocations){
            LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latlng);
            markerOptions.title("Lat:"+ location.getLatitude()+"lon:"+location.getLongitude());
            mMap.addMarker(markerOptions);
            lastlocationPlaced=latlng;
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastlocationPlaced,12.0f));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                Integer clicks = (Integer) marker.getTag();
                if (clicks==null){
                    clicks=0;
                }
                clicks++;
                marker.setTag(clicks);
                Toast.makeText(MapsActivity.this, "Marker"+ marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
            
        });
    }
}