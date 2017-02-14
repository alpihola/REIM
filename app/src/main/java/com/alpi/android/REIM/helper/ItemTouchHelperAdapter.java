package com.alpi.android.REIM.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
* Interfaz para escuchar el evento de descarte (dismiss) desde {@link ItemTouchHelper.Callback}
**/

public interface ItemTouchHelperAdapter {

    /**
    * LLamado cuando un item ha sido descartado con un swipe.
    * Implementaciones deberían llamar {@link RecyclerView.Adapter#notifyItemRemoved(int)} luego
    * de ajustar los datos subyacentes para reflejar esta eliminación.
    *
    * @param position es la posición del item descartado.
    * @see RecyclerView#getAdapterPositionFor(RecyclerView.ViewHolder)
    * @see RecyclerView.ViewHolder#getAdapterPosition()
    **/

    void onItemDismiss(int position);

}