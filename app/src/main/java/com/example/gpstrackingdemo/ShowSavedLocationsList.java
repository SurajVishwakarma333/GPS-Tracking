package com.example.gpstrackingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShowSavedLocationsList extends AppCompatActivity {

    ListView lv_savedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_locations_list);

        lv_savedLocation=findViewById(R.id.lv_wayPoints);
        MyApplication myApplication = (MyApplication)getApplicationContext();
        List<Location> savedLocations = myApplication.getMyLocation();

        lv_savedLocation.setAdapter(new ArrayAdapter<Location>( this ,android.R.layout.simple_list_item_1, savedLocations ));
    }
}