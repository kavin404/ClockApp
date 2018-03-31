package com.ctse.clock.alarm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctse.clock.worldClock.ListAdapter;
import com.ctse.clock.R;
import com.ctse.clock.helpers.AlarmItemTouchHelper;
import com.ctse.clock.helpers.RecyclerItemTouchHelperListener;
import com.ctse.clock.models.WorldClockListItem;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment implements RecyclerItemTouchHelperListener {
    View v;
    private RecyclerView recyclerView;
    private List<WorldClockListItem> list;
    private AlarmListAdapter listAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.alarm_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.listAlarm);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        listAdapter = new AlarmListAdapter(list,v.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FloatingActionButton fab = v.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddNewAlarm.class);
                startActivity(intent);
            }
        });
        String header,description ;
        for(int i=0;i<4;i++){
            header = "12:45 AM";
            description = " Affrica ";
            WorldClockListItem li = new WorldClockListItem(header,description,true);
            list.add(li);
        }

        recyclerView.setAdapter(listAdapter);
        ItemTouchHelper.SimpleCallback item = new AlarmItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(item).attachToRecyclerView(recyclerView);
        return v;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof ListAdapter.ViewHolder){
            WorldClockListItem item = list.get(viewHolder.getAdapterPosition());
            int deleteIndex = viewHolder.getAdapterPosition();
            listAdapter.removeItem(deleteIndex);
//            CoordinatorLayout root = findViewById(R.id.root_layout);
//            Snackbar snackbar = Snackbar.make(root,"Removed from clocks ",Snackbar.LENGTH_SHORT);
        }
    }
}
