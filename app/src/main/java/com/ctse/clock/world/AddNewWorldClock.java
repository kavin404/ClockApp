package com.ctse.clock.world;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ctse.clock.R;
import com.ctse.clock.helpers.DBHelper;
import com.ctse.clock.models.CountryItem;

import java.util.ArrayList;
import java.util.List;

public class AddNewWorldClock extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<CountryItem> list;
    private NewWorldClockListAdapter newWolrdClockListAdapter;
    private String country[] = {"Japan", "New Zealand", "Australia", "USA", "UK", "China", "France", "Russia", "Canada", "Mexico", "Turkey", "Singapore"};
    private String timeZones[] = {"GMT+9:00", "GMT+13:00", "GMT+11:00", "GMT-9:00", "GMT+0:00", "GMT+8:00", "GMT+1:00", "GMT+6:00", "GMT-6:00", "GMT-5:00", "GMT+3:00", "GMT+8:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(getApplicationContext());
        db.populateFirstTime();
        setContentView(R.layout.activity_add_new_world_clock);
        recyclerView = (RecyclerView) findViewById(R.id.list_new_world);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        newWolrdClockListAdapter = new NewWorldClockListAdapter(list, getApplicationContext());
        if (getResources().getConfiguration().orientation == 2) {
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < country.length; i++) {
            list.add(new CountryItem(country[i], timeZones[i], db.read(country[i])));
        }

        recyclerView.setAdapter(newWolrdClockListAdapter);
    }
}
