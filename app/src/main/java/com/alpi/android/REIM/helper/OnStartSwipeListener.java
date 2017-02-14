package com.alpi.android.REIM.helper;

import android.support.v7.widget.RecyclerView;

/**
* Listener para una iniciación manual de un swipe.
**/

public interface OnStartSwipeListener {

    /**
     * Llamado cuando una vista solicita el inicio de un swipe.
     * @param viewHolder es el holder de la vista a ser descartada.
     **/

    void startSwipe(RecyclerView.ViewHolder viewHolder);

}