package com.ctse.clock.worldClock;

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
import com.ctse.clock.models.CountryItem;


import java.util.List;

public class NewWolrdClockListAdapter extends RecyclerView.Adapter<NewWolrdClockListAdapter.ViewHolder> {

    private List<CountryItem> list;
    private Context context;


    public NewWolrdClockListAdapter(List<CountryItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NewWolrdClockListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_world_clock_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewWolrdClockListAdapter.ViewHolder holder, int position) {
        CountryItem listItem = list.get(position);
        holder.textClock.setText(listItem.getHead());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(CountryItem item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textClock;
        public LinearLayout view_background;

        public ViewHolder(View itemView) {
            super(itemView);

            textClock = (TextView) itemView.findViewById(R.id.header);
            view_background = itemView.findViewById(R.id.background);
        }
    }
}
