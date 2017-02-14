package com.alpi.android.REIM.helper;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
* Interfaz para notificar al ViewHolder de un item sobre algún callback relevante desde
* {@link android.support.v7.widget.helper.ItemTouchHelper.Callback}.
**/

public interface ItemTouchHelperViewHolder {

    /**
     * Llamado cuando el {@link ItemTouchHelper} registra un primer movimiento o swap de un item.
     * Implementaciones deberían actualizar la vista del item para indicar su estado activo.
     */

    void onItemSelected();

    /**
     * Llamado cuando el {@link ItemTouchHelper} ha completado un swipe y el estado de un item
     * activo debe ser despejado.
     */

    void onItemClear();

}