package com.hm_master.masterapp;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private boolean AlertAlreadyShown = false;

    public static Toolbar toolbar;
    public static MainActivity Instance;
    public static NavigationView navigationView;
    public static DatabaseHelper SqLiteDB;
    private GoogleMap mMap;
    private List<String[]> mapLocations;
    private boolean ShowOnLoaction = false;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Instance = this;
        SqLiteDB = new DatabaseHelper(this);
        mapLocations = new ArrayList<>();
        mapLocations.add(getResources().getStringArray(R.array.location_HM));

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Register Timclock , so List changes every minute
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.setAction("packagename.ACTION");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60, pendingIntent);

        MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_home);
        MainActivity.Instance.onNavigationItemSelected(item);

        //Debugging
        //MenuItem item2 = MainActivity.navigationView.getMenu().findItem(R.id.nav_maps);
        //MainActivity.Instance.onNavigationItemSelected(item2);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        navigationView.setCheckedItem(id);
        return ChangeFragment(id);
    }

    public boolean ChangeFragment(int id) {

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        switch (id) {

            case (R.id.nav_home):
            case (R.string.nav_Home):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Home())
                        .addToBackStack(null).commit();
                break;

            case (R.id.nav_pc_room):
            case (R.string.nav_PcRooms):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_PCRoom())
                        .addToBackStack(null).commit();
                break;

            case (R.id.nav_classroom):
            case (R.string.nav_Classroom):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Classroom())
                        .addToBackStack(null).commit();
                break;

            case (R.id.nav_library):
            case (R.string.nav_Library):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Bib())
                        .addToBackStack(null).commit();

                break;

            case (R.id.nav_food):
            case (R.string.nav_Food):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Food())
                        .addToBackStack(null).commit();
                break;

            case (R.id.nav_mvv):
            case (R.string.nav_Mvv):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Mvv())
                        .addToBackStack(null).commit();
                break;

            case (R.id.nav_fun):
            case (R.string.nav_Fun):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Nigthlife())
                        .addToBackStack(null).commit();

                break;

            case (R.id.nav_maps):
            case (R.string.nav_GoogleMaps):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_GoogleMaps())
                        .addToBackStack(null).commit();
                SetOwnPosition(true);
                break;

            case (R.id.nav_imperssum):
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Instance);

                String[] autoren = getResources().getStringArray(R.array.Autoren);
                String appName = getResources().getString(R.string.app_name);
                String alertText = getResources().getString(R.string.Impressum,
                        appName,autoren[0], autoren[1], autoren[2], autoren[3]);

                alertDialogBuilder.setMessage(alertText).setPositiveButton("Danke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        float zoomLevel = (float) 16.0;
        LatLng location = new LatLng(48.142659, 11.568068);

        for (String[] locationArray : mapLocations) {
            String name = locationArray[0];
            double lat = Double.parseDouble(locationArray[1]);
            double lon = Double.parseDouble(locationArray[2]);
            location = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(location).title(name));
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));

        boolean test1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        boolean test2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return;

        }

        mMap.setMyLocationEnabled(true);
    }

    public void changeMapMarker(String[] nameLatLon) {

        mapLocations.clear();
        if (nameLatLon != null)
            mapLocations.add(nameLatLon);

        ShowOnLoaction = false;
        if (mMap == null) {
            return;
        }

        float zoomLevel = (float) 16.0;

        String name = nameLatLon[0];
        double lat = Double.parseDouble(nameLatLon[1]);
        double lon = Double.parseDouble(nameLatLon[2]);
        LatLng loaction = new LatLng(lat, lon);

        mMap.addMarker(new MarkerOptions().position(loaction).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loaction, zoomLevel));

    }

    public int addMarker(String[] nameLatLon) {

        mapLocations.add(nameLatLon);

        if (mMap == null) {
            return -1;
        }

        String name = nameLatLon[0];
        double lat = Double.parseDouble(nameLatLon[1]);
        double lon = Double.parseDouble(nameLatLon[2]);

        LatLng location = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(location).title(name));
        float zoomLevel = (float) 14.0;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));

        return mapLocations.size() - 1;
    }

    public void changeMarker(int pos, String[] nameLatLon) {
        if (mapLocations.size() == 0)
            return;

        mapLocations.set(pos, nameLatLon);

        if (mMap != null) {
            mMap.clear();
            for (String[] postion :
                    mapLocations) {
                addMarker(postion);
            }
        }
    }

    public static void SetOwnPosition(boolean setLocation) {
        if (Instance == null)
            return;

        LocationManager locationManager = (LocationManager) Instance.getSystemService(Context.LOCATION_SERVICE);

        Instance.ShowOnLoaction = setLocation;

        if (setLocation && !Instance.AlertAlreadyShown)
            // Testen, ob GPS verfgbar
            try {
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Instance);

                    alertDialogBuilder.setMessage(R.string.GPSDeaktivated).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    Instance.AlertAlreadyShown = true;
                }
            } catch (Exception ex) {

                        }
    }
}


