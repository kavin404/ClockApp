package com.ctse.clock.basic;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;

import com.ctse.clock.R;
import com.ctse.clock.alarm.AlarmFragment;
import com.ctse.clock.world.WorldClockFragment;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable r;
    private Time mTime;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ClockFragment(), "");
        viewPagerAdapter.addFragment(new AlarmFragment(), "");
        viewPagerAdapter.addFragment(new WorldClockFragment(), "");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_access_time_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_alarm_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_update_white_24dp);

    }

}
