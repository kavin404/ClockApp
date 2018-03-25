package com.ctse.clock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctse.clock.R;
import com.ctse.clock.helpers.RecyclerItemTouchHelper;
import com.ctse.clock.helpers.RecyclerItemTouchHelperListener;
import com.ctse.clock.models.ListItem;

import java.util.ArrayList;
import java.util.List;

public class WorldClockFragment extends Fragment implements RecyclerItemTouchHelperListener  {
    View v;
    private RecyclerView recyclerView;
    private List<ListItem> list;
    private ListAdapter listAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.world_clock_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        listAdapter = new ListAdapter(list,v.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        String header,description ;
        for(int i=0;i<4;i++){
            header = "12:45 AM";
            description = " Affrica ";
            ListItem li = new ListItem(header,description,true);
            list.add(li);
        }

        recyclerView.setAdapter(listAdapter);
        ItemTouchHelper.SimpleCallback item = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(item).attachToRecyclerView(recyclerView);
        return v;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof ListAdapter.ViewHolder){
            ListItem item = list.get(viewHolder.getAdapterPosition());
            int deleteIndex = viewHolder.getAdapterPosition();
            listAdapter.removeItem(deleteIndex);
//            CoordinatorLayout root = findViewById(R.id.root_layout);
//            Snackbar snackbar = Snackbar.make(root,"Removed from clocks ",Snackbar.LENGTH_SHORT);
        }
    }
}
