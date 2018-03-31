package com.ctse.clock.worldClock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.Toast;

import com.ctse.clock.R;
import com.ctse.clock.helpers.DBHelper;
import com.ctse.clock.helpers.RecyclerItemTouchHelper;
import com.ctse.clock.helpers.RecyclerItemTouchHelperListener;
import com.ctse.clock.models.WorldClockListItem;

import java.util.ArrayList;
import java.util.List;

public class WorldClockFragment extends Fragment implements RecyclerItemTouchHelperListener {
    View v;
    private RecyclerView recyclerView;
    private List<WorldClockListItem> list;
    private ListAdapter listAdapter;
    private String timeZones[] = {"GMT+9:00", "GMT+13:00", "GMT+11:00", "GMT-9:00", "GMT+0:00", "GMT+8:00", "GMT+1:00", "GMT+6:00", "GMT-6:00", "GMT-5:00", "GMT+3:00", "GMT+8:00"};
    private String country[] = {"Japan", "New Zealand", "Australia", "USA", "UK", "China", "France", "Russia", "Canada", "Mexico", "Turkey", "Singapore"};
    DBHelper db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.world_clock_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        db = new DBHelper(v.getContext());
        db.populateFirstTime();
        listAdapter = new ListAdapter(list, v.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab = v.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), AddNewWorldClock.class);
                startActivity(myIntent);
            }
        });
        String header, description;
        for (int i = 0; i < timeZones.length; i++) {
            header = timeZones[i];
            description = country[i];
            if(db.read(country[i])){
                list.add( new WorldClockListItem(header, description,true));
            }
        }

        recyclerView.setAdapter(listAdapter);
        ItemTouchHelper.SimpleCallback item = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(item).attachToRecyclerView(recyclerView);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i("MYTAG","Resume");
        String header, description;
        list.clear();
        for (int i = 0; i < timeZones.length; i++) {
            header = timeZones[i];
            description = country[i];
            if(db.read(country[i])){
                list.add( new WorldClockListItem(header, description,true));
            }
        }
        listAdapter = new ListAdapter(list, v.getContext());
        recyclerView.setAdapter(listAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i("MYTAG","STart");
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ListAdapter.ViewHolder) {
            WorldClockListItem item = list.get(viewHolder.getAdapterPosition());
            int deleteIndex = viewHolder.getAdapterPosition();
            listAdapter.removeItem(deleteIndex);
//            CoordinatorLayout root = findViewById(R.id.root_layout);
//            Snackbar snackbar = Snackbar.make(root,"Removed from clocks ",Snackbar.LENGTH_SHORT);
        }
    }
}
