package com.example.den.alenintestcityguide.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.den.alenintestcityguide.fragment.AboutFragment;
import com.example.den.alenintestcityguide.fragment.NewsFragment;
import com.example.den.alenintestcityguide.R;
import com.example.den.alenintestcityguide.fragment.SettingsFragment;

public class MainActivity extends Activity {
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private String[] drawerListTitles;
    private ActionBarDrawerToggle drawerToggle;//helps to invoke navbar and open/close activity react

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find our views
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.drawer_list);
        drawerListTitles = getResources().getStringArray(R.array.menu_names);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer);
        ArrayAdapter<String> drawerMenuAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                drawerListTitles
        );
        drawerList.setAdapter(drawerMenuAdapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        drawerLayout.addDrawerListener(drawerToggle);
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null)
            selectItem(0);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // sync  onRestoreInstanceState.
        drawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))// let drawerToggle act
            return true;
        return super.onOptionsItemSelected(item);
    }


    private void selectItem(int position) {
        Fragment fragment;
        switch (position){
            case 1:
                fragment = new SettingsFragment();
                break;
            case 2:
                fragment = new AboutFragment();
                break;
            default:
                fragment = new NewsFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        setActionBarTitle(position);
        drawerLayout.closeDrawer(drawerList);
    }


    private void setActionBarTitle(int position){
        String title = drawerListTitles[position];
        getActionBar().setTitle(title);
    }
}
