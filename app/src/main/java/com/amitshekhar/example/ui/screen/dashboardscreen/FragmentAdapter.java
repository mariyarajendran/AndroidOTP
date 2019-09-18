package com.amitshekhar.example.ui.screen.dashboardscreen;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class FragmentAdapter extends FragmentPagerAdapter {
    private List<android.support.v4.app.Fragment> Fragment = new ArrayList<>(); //Fragment List
    private List<String> NamePage = new ArrayList<>(); // Fragment Name List

    public FragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return null;
    }


    public void add(android.support.v4.app.Fragment Frag, String Title) {
        Fragment.add(Frag);
        NamePage.add(Title);
    }

//    @Override
//    public android.app.Fragment getItem(int position) {
//        return Fragment.get(position);
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        return NamePage.get(position);
    }

    @Override
    public int getCount() {
        return Fragment.size();
    }
}