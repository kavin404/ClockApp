package com.ctse.clock.basic;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextClock;

import com.ctse.clock.R;


public class ClockFragment extends Fragment implements Runnable {
    View v;

    private Handler handler;
    private Runnable r;
    private Time mTime;
    LinearLayout l;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.clock_fragment, container, false);
        mTime = new Time();
        ((TextClock) v.findViewById(R.id.hour)).setTimeZone("GMT+5:30");
        if (getResources().getConfiguration().orientation == 1) {
            ((TextClock) v.findViewById(R.id.seconds)).setTimeZone("GMT+5:30");
        }
        return v;
    }


    @Override
    public void run() {
        mTime.setToNow();
    }
}
