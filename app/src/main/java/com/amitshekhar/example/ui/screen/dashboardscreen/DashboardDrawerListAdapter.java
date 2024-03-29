package com.amitshekhar.example.ui.screen.dashboardscreen;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amitshekhar.example.R;

import java.util.ArrayList;

/**
 * Created by ramees on 8/16/2016.
 */
public class DashboardDrawerListAdapter extends BaseAdapter {
    Activity activity;
    private static LayoutInflater inflater = null;
    ArrayList<String> titles;

    public DashboardDrawerListAdapter(Activity activity, ArrayList<String> titles) {
// TODO Auto-generated constructor stub
        this.titles = titles;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
// TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv_title;
        ImageView im_icon;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        Holder holder = new Holder();
        View view;
        view = inflater.inflate(R.layout.layout_drawer_item, null);

        holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
        holder.tv_title.setText(titles.get(position));
        return view;
    }

}