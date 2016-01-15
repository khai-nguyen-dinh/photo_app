package com.snapsoft.khain.photo_app;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.snapsoft.khain.photo_app.adapters.Pager_adater;
import com.snapsoft.khain.photo_app.objects.Data;

import java.util.ArrayList;

public class Viewpager_activity extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_activity);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        Pager_adater adater = new Pager_adater(MainActivity.data,Viewpager_activity.this);
        Intent p = getIntent();
       int position = p.getExtras().getInt("position");

        viewPager.setAdapter(adater);
        viewPager.setCurrentItem(position);

    }
}
