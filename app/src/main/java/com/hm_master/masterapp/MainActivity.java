package com.hm_master.masterapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static Toolbar toolbar;
    public static MainActivity Instance;
    public static NavigationView navigationView;
    public static DatabaseHelper SqLiteDB;
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

        MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_home);
        MainActivity.Instance.onNavigationItemSelected(item);

        /*
        Debugging

       MenuItem item2 = MainActivity.navigationView.getMenu().findItem(R.id.nav_pc_room);
         MainActivity.Instance.onNavigationItemSelected(item2);

         */

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
        int id = item.getItemId();
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

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager ();

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

                //Intent intent = new Intent(this,Fragment_PCRoom.class);
                //startActivity(intent);

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

                //Intent intent = new Intent(this,Fragment_PCRoom.class);
                //startActivity(intent);

                break;

            case (R.id.nav_maps):
            case (R.string.nav_GoogleMaps):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_GoogleMaps())
                        .addToBackStack(null).commit();
                break;

            case (R.integer.food_k6):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_GoogleMaps ()).commit();
                break;


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
