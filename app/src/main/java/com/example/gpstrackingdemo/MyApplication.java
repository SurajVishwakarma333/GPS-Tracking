package com.example.gpstrackingdemo;

import android.app.Application;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static MyApplication singleton;

    private List<Location> myLocations;

    public List<Location> getMyLocation() {
        return myLocations;
    }

    public void setMyLocation(List<Location> myLocation) {
        this.myLocations = myLocation;
    }

    public MyApplication getInstance(){
        return singleton;
    }
    public void onCreate(){
        super.onCreate();
        singleton = this ;
        myLocations = new ArrayList<>();
    }

}
