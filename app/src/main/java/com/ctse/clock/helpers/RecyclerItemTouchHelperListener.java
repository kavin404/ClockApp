package com.ctse.clock.helpers;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Kavindu on 3/25/2018.
 */

public interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
