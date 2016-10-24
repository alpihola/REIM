package com.alpi.android.REIM;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.alpi.android.REIM.helper.OnStartDragListener;
import com.alpi.android.REIM.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class Actividad002 extends AppCompatActivity implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;

    private final String nombreElementoBomberos[] = {
            "Balon Futbol",
            "Casco",
            "Cono Advertencia",
            "Estetoscopio",
            "Extintor",
            "Guantes",
            "Hacha",
            "Manguera",
    };

    private final String imagenElementoBomberos[] = {
            "http://i.imgur.com/KU0Y7a7.png",
            "http://i.imgur.com/rGykl5D.png",
            "http://i.imgur.com/tmtSr9t.png",
            "http://i.imgur.com/F7cAxoo.png",
            "http://i.imgur.com/pgqsqLK.png",
            "http://i.imgur.com/lt28yxB.png",
            "http://i.imgur.com/KgfsWxH.png",
            "http://i.imgur.com/HFmxkkt.png",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vista_actividad002);

        ArrayList<ElementosBomberos> elementosBomberos = new ArrayList<>();
        for(int i=0;i<nombreElementoBomberos.length;i++) {
            ElementosBomberos nuevoElementoBomberos = new ElementosBomberos();
            nuevoElementoBomberos.setNombreElementoBomberos(nombreElementoBomberos[i]);
            nuevoElementoBomberos.setImagenElementoBomberos(imagenElementoBomberos[i]);
            elementosBomberos.add(nuevoElementoBomberos);
        }

        final ElementosBomberosAdapter adapter = new ElementosBomberosAdapter(this.getApplicationContext(), elementosBomberos, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaElementosBomberos);
        recyclerView.setAdapter(adapter);

        final int spanCount = 2;
        final GridLayoutManager layoutManager = new GridLayoutManager(this.getApplicationContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startSwipe(viewHolder);
    }

}
