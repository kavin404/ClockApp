package com.ctse.clock.world;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.ctse.clock.R;
import com.ctse.clock.models.WorldClockListItem;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<WorldClockListItem> list;
    private Context context;


    public ListAdapter(List<WorldClockListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        WorldClockListItem listItem = list.get(position);
        holder.textClock.setTimeZone(listItem.getHead());
        holder.description.setText(listItem.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(WorldClockListItem item, int position){
        list.add(position,item);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextClock textClock;
        public TextView description;
        public LinearLayout view_background;

        public ViewHolder(View itemView){
            super(itemView);

            textClock =(TextClock) itemView.findViewById(R.id.header);
            description = (TextView) itemView.findViewById(R.id.description);
            view_background = itemView.findViewById(R.id.background);
        }
    }
}
