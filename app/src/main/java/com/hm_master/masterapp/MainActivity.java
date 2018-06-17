package com.hm_master.masterapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static Toolbar toolbar;
    public static MainActivity Instance;
    public static NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Instance = this;
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

        FragmentManager fragmentManager = getFragmentManager();
        MenuItem item;
        switch (id) {

            case (R.id.nav_home):
            case (R.string.nav_Home):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Home()).commit();
                getSupportActionBar().setTitle(R.string.actionText_HomeText);
                break;

            case (R.id.nav_pc_room):
            case (R.string.nav_PcRooms):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_PCRoom()).commit();
                getSupportActionBar().setTitle(R.string.nav_PcRooms);
                break;

            case (R.id.nav_classroom):
            case (R.string.nav_Classroom):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Classroom()).commit();
                getSupportActionBar().setTitle(R.string.nav_Classroom);
                break;

            case (R.id.nav_library):
            case (R.string.nav_Library):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Bib()).commit();
                getSupportActionBar().setTitle(R.string.nav_Library);
                break;

            case (R.id.nav_food):
            case (R.string.nav_Food):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Food()).commit();
                getSupportActionBar().setTitle(R.string.nav_Food);
                break;

            case (R.id.nav_mvv):
            case (R.string.nav_Mvv):

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_Mvv()).commit();
                getSupportActionBar().setTitle(R.string.nav_Mvv);
                break;

            case (R.id.nav_fun):
            case (R.string.nav_Fun):


                //fragmentManager.beginTransaction()
                  //      .replace(R.id.content_frame, new Fragment_Nigthlife()).commit();
                //getSupportActionBar().setTitle(R.string.nav_Fun);
                Intent intent = new Intent (this, Activity_nightlife.class);
                startActivity(intent);



                break;

            case (R.id.nav_maps):
            case (R.string.nav_GoogleMaps):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new Fragment_PCRoom()).commit();
                getSupportActionBar().setTitle(R.string.nav_GoogleMaps);
                break;


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
