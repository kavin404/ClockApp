package com.ctse.clock.worldClock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ctse.clock.R;
import com.ctse.clock.helpers.DBHelper;
import com.ctse.clock.models.CountryItem;


import java.util.List;

public class NewWorldClockListAdapter extends RecyclerView.Adapter<NewWorldClockListAdapter.ViewHolder> implements View.OnClickListener {

    private List<CountryItem> list;
    private Context context;
    View view;

    public NewWorldClockListAdapter(List<CountryItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NewWorldClockListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_world_clock_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewWorldClockListAdapter.ViewHolder holder, int position) {
        CountryItem listItem = list.get(position);
        holder.textClock.setText(listItem.getHead());
        holder.check_circle.setVisibility(listItem.isChecked()? View.VISIBLE:View.INVISIBLE);
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

    @Override
    public void onClick(View v) {
        //int itemPosition = ((RecyclerView)this.view.findViewById(R.id.list_new_world)).getChildLayoutPosition(v);
        String country = (String)((TextView)(v.findViewById(R.id.header))).getText();
        boolean checked = ((ImageView)v.findViewById(R.id.checked_item)).getVisibility() != View.VISIBLE;
        DBHelper db = new DBHelper(context);
        db.update(country,checked);
        if(checked){
            ((ImageView)v.findViewById(R.id.checked_item)).setVisibility(View.VISIBLE);
        }else{
            ((ImageView)v.findViewById(R.id.checked_item)).setVisibility(View.INVISIBLE);
        }

        Toast.makeText(this.context, country, Toast.LENGTH_LONG).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textClock;
        public LinearLayout view_background;
        public ImageView check_circle;

        public ViewHolder(View itemView) {
            super(itemView);
            textClock = (TextView) itemView.findViewById(R.id.header);
            view_background = itemView.findViewById(R.id.background);
            check_circle = itemView.findViewById(R.id.checked_item);
        }

    }
}
