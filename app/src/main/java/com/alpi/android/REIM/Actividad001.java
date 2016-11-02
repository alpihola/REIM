package com.alpi.android.REIM;

import android.media.MediaPlayer;
import android.os.Bundle;
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

public class Actividad001 extends AppCompatActivity implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;
    Button instruccionActividad001;
    Button mostrarResultado;
    int duracionToast = Toast.LENGTH_SHORT;


    private final String nombreAlimento[] = {
            "Chocolate",
            "Hamburguesa",
            "Manzana",
            "Papas Fritas",
            "Pizza",
            "Queso",
            "Sandia",
            "Zanahoria",
    };

    private final String imagenAlimento[] = {
            "http://i.imgur.com/JTTYxxw.png",
            "http://i.imgur.com/N7IS3vq.png",
            "http://i.imgur.com/HgApYuS.png",
            "http://i.imgur.com/kdVzM9v.png",
            "http://i.imgur.com/M5pjsq9.png",
            "http://i.imgur.com/26FrW0d.png",
            "http://i.imgur.com/iRfzuf4.png",
            "http://i.imgur.com/jBfoKWu.png",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vista_actividad_001);

        final ArrayList<Alimento> alimentos = new ArrayList<>();

        for(int i=0;i<nombreAlimento.length;i++) {
            Alimento nuevoAlimento = new Alimento();
            nuevoAlimento.setNombreAlimento(nombreAlimento[i]);
            nuevoAlimento.setImagenAlimento(imagenAlimento[i]);
            alimentos.add(nuevoAlimento);
        }

        final AlimentoAdapter adapter = new AlimentoAdapter(this.getApplicationContext(), alimentos, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.matrizAlimentos);
        recyclerView.setAdapter(adapter);

        final int spanCount = getResources().getInteger(R.integer.grid_columns);
        final GridLayoutManager layoutManager = new GridLayoutManager(this.getApplicationContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        instruccionActividad001 = (Button) findViewById(R.id.botonInstruccion);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.instruccion_actividad_001);
        instruccionActividad001.setOnClickListener(new View.OnClickListener() {
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
            }
        });
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startSwipe(viewHolder);
    }

}