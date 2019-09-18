package com.amitshekhar.example.ui.screen.dashboardscreen;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amitshekhar.example.R;
import com.amitshekhar.example.ui.base.BaseActivity;
import com.amitshekhar.example.ui.screen.accountscreen.FragmentAccountScreen;
import com.amitshekhar.example.ui.screen.homescreen.FragmentHomeScreen;

import java.util.ArrayList;

public class Dashboard extends BaseActivity {

    Toolbar toolbar;
    ArrayList<String> navigation_items;
    private DashboardDrawerListAdapter dashboardDrawerListAdapter;
    public ListView lv_drawer;
    ViewPager viewPager;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        SetDrawer();
        toolbar.setTitle("Shop");
        loadFragment(new FragmentHomeScreen());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);


        navigation_items = new ArrayList<>();

//adding menu items for naviations
        navigation_items.add("Call");
        navigation_items.add("Favorite");
        navigation_items.add("Search");

        lv_drawer = (ListView) findViewById(R.id.lv_drawer);

    }

    private void SetDrawer() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        dashboardDrawerListAdapter = new DashboardDrawerListAdapter(Dashboard.this, navigation_items);
        lv_drawer.setAdapter(dashboardDrawerListAdapter);

        lv_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.bottom_navigation_item_home:
                    toolbar.setTitle("Shop");
                    fragment = new FragmentHomeScreen();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_item_email:
                    toolbar.setTitle("My Gifts");
                    fragment = new FragmentAccountScreen();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_item_search:
                    toolbar.setTitle("Cart");
                    fragment = new FragmentHomeScreen();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_item_account:
                    toolbar.setTitle("Profile");
                    fragment = new FragmentAccountScreen();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
