package com.ctse.clock.basicClock;


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

import com.ctse.clock.R;


public class ClockFragment extends Fragment implements Runnable {
    View v;

    private Handler handler;
    private Runnable r;
    private Time mTime;
    LinearLayout l;
    ClockFragment.drawingView dv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.clock_fragment,container,false);
        mTime = new Time();

//        l = v.findViewById(R.id.linearLayout);
//        mTime.setToNow();
//        dv = new ClockFragment.drawingView(v.getContext(),mTime.hour,mTime.minute,mTime.second,mTime.weekDay,mTime.monthDay,getBatteryLevel());
//        l.addView(dv);
//
//        handler = new Handler();
//        handler.postDelayed(this,1000);
        return v;
    }

    public float getBatteryLevel(){
        Intent batteryIntent = getActivity().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);

        if(level == -1 || scale == -1){
            return 50.0f;
        }
        return ((float)level /(float)scale) * 100.0f;
    }

    @Override
    public void run() {
        mTime.setToNow();
        dv = new ClockFragment.drawingView(v.getContext(),mTime.hour,mTime.minute,mTime.second,mTime.weekDay,mTime.monthDay,getBatteryLevel());
        l.removeAllViews();
        l.addView(dv);
        l.invalidate();
        l.requestLayout();
    }

    public class drawingView extends View {
        Typeface tf;
        Paint mBackgroundPaint, mTextPaint, mTextPaintBack;
        int hours, minutes, seconds, weekday, date;
        float battery;

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            float width = canvas.getWidth();
            float height = canvas.getHeight();

            canvas.drawRect(0, 0, width, height, mBackgroundPaint);
            float centerX = width / 2f;
            float centerY = height / 2f;

            int cur_hour = hours;
            String cur_ampm = "AM";
            if (cur_hour == 0) {
                cur_hour = 12;
            }
            if (cur_hour > 12) {
                cur_hour = cur_hour = 12;
                cur_ampm = "PM";
            }
            String text = String.format("%02d:%02d:%02d", cur_hour, minutes, seconds);
            String day_of_week = "";
            switch (weekday) {
                case 1:
                    day_of_week = "MON";
                    break;
                case 2:
                    day_of_week = "TUE";
                    break;
                case 3:
                    day_of_week = "WED";
                    break;
                case 4:
                    day_of_week = "THU";
                    break;
                case 5:
                    day_of_week = "FRI";
                    break;
                case 6:
                    day_of_week = "SAT";
                    break;
                case 0:
                    day_of_week = "SUN";
                    break;
            }
            String text2 = String.format("DATE: %s %d", day_of_week, date);
            String batteryLevel = "BATTERY: " + (int) battery + "%";
            canvas.drawText("00:00:00", centerX, centerY, mTextPaintBack);

            mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.text));
            mTextPaint.setTextSize(getResources().getDimension(R.dimen.text_size));
            canvas.drawText(text, centerX, centerY, mTextPaint);

            mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.text));
            mTextPaint.setTextSize(getResources().getDimension(R.dimen.text_size_small));
            canvas.drawText(batteryLevel + " " + text2,
                    centerX,
                    centerY + getResources().getDimension(R.dimen.text_size_small),
                    mTextPaint);


        }

        public drawingView(Context context, int hours, int minutes, int seconds, int weekday, int date, float battery) {
            super(context);
            tf = Typeface.createFromAsset(getContext().getAssets(), "digital-7 (mono).ttf");

            mBackgroundPaint = new Paint();
            mBackgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.background));

            mTextPaint = new Paint();
            mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.text));
            mTextPaint.setAntiAlias(true);
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            mTextPaint.setTextSize(getResources().getDimension(R.dimen.text_size));
            mTextPaint.setTypeface(tf);

            mTextPaintBack = new Paint();
            mTextPaintBack.setColor(ContextCompat.getColor(getContext(), R.color.text_back));
            mTextPaintBack.setAntiAlias(true);
            mTextPaintBack.setTextAlign(Paint.Align.CENTER);
            mTextPaintBack.setTextSize(getResources().getDimension(R.dimen.text_size));
            mTextPaintBack.setTypeface(tf);

            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
            this.date = date;
            this.battery = battery;
            this.weekday = weekday;


        }

    }

}
