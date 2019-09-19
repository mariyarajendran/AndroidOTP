package com.amitshekhar.example.ui.screen.dashboardscreen;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dashboard extends BaseActivity {

    @BindView(R.id.dashboard_toolbar)
    Toolbar mToolbar;
    ArrayList<String> navigation_items;
    private DashboardDrawerListAdapter dashboardDrawerListAdapter;
    @BindView(R.id.dashboard_lv_drawer)
    public ListView mListView;
    @BindView(R.id.dashboard_drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        addDrawerArrayData();
        setDrawerLayout();
        mToolbar.setTitle("Shop");
        loadFragment(new FragmentHomeScreen());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.content_dashboard_bottomnavigation_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public void addDrawerArrayData() {
        navigation_items = new ArrayList<>();
        navigation_items.add("Call");
        navigation_items.add("Favorite");
        navigation_items.add("Search");
    }

    private void setDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        dashboardDrawerListAdapter = new DashboardDrawerListAdapter(Dashboard.this, navigation_items);
        mListView.setAdapter(dashboardDrawerListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    mToolbar.setTitle("Shop");
                    fragment = new FragmentHomeScreen();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_item_email:
                    mToolbar.setTitle("My Gifts");
                    fragment = new FragmentAccountScreen();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_item_search:
                    mToolbar.setTitle("Cart");
                    fragment = new FragmentHomeScreen();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_item_account:
                    mToolbar.setTitle("Profile");
                    fragment = new FragmentAccountScreen();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_dashboard_framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
