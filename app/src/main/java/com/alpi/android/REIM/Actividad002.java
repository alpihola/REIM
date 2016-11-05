package com.alpi.android.REIM;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alpi.android.REIM.helper.OnStartDragListener;
import com.alpi.android.REIM.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class Actividad002 extends AppCompatActivity implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;
    Button instruccionActividad002;
    Button mostrarResultado;

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

        setContentView(R.layout.vista_actividad_002);

        ArrayList<ElementoBomberos> elementosBomberos = new ArrayList<>();
        for (int i = 0; i < nombreElementoBomberos.length; i++) {
            ElementoBomberos nuevoElementoBomberos = new ElementoBomberos();
            nuevoElementoBomberos.setNombreElementoBomberos(nombreElementoBomberos[i]);
            nuevoElementoBomberos.setImagenElementoBomberos(imagenElementoBomberos[i]);
            elementosBomberos.add(nuevoElementoBomberos);
        }

        final ElementoBomberosAdapter adapter = new ElementoBomberosAdapter(this.getApplicationContext(), elementosBomberos, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaElementosBomberos);
        recyclerView.setAdapter(adapter);

        final int spanCount = 2;
        final GridLayoutManager layoutManager = new GridLayoutManager(this.getApplicationContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        instruccionActividad002 = (Button) findViewById(R.id.botonInstruccion);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.instruccion_actividad_002);
        instruccionActividad002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        mostrarResultado = (Button) findViewById(R.id.botonMostrarResultado);
        mostrarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.getResult();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent = new Intent(Actividad002.this, MapaSur.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startSwipe(viewHolder);
    }

}
