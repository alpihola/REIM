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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.alpi.android.REIM.helper.OnStartDragListener;
import com.alpi.android.REIM.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Actividad002 extends AppCompatActivity implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;
    Button instruccionActividad002;
    Button mostrarResultado;

    private final String nombreElementoBomberos[] = {
            "Botas",
            "Cámara",
            "Casco",
            "Chaqueta Negra",
            "Chaqueta Roja",
            "Cono Advertencia",
            "Cuadro",
            "Cubo Rubik",
            "Estetoscopio",
            "Micrófono",
            "Extintor",
            "Balón Fútbol",
            "Guantes Box",
            "Hacha",
            "Guitarra",
            "Pingüino",
            "Manguera",
            "Guantes Bombero",
            "Paleta Ping-Pong",
            "Skate"
    };

    private final boolean correspondeElementoBomberos[] = {
            true,
            false,
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            true,
            false,
            false,
            true,
            false,
            false,
            true,
            true,
            false,
            false
    };

    private final String imagenElementoBomberos[] = {
            "http://i.imgur.com/dHWXFB6.png",
            "http://i.imgur.com/6WhKEKp.png",
            "http://i.imgur.com/OnKX4Qm.png",
            "http://i.imgur.com/K0xBuRC.png",
            "http://i.imgur.com/sxIraEl.png",
            "http://i.imgur.com/QZtfSDw.png",
            "http://i.imgur.com/D38l4We.png",
            "http://i.imgur.com/35yx5d0.png",
            "http://i.imgur.com/rtnZ9v3.png",
            "http://i.imgur.com/TlQQtb3.png",
            "http://i.imgur.com/5XSLuJN.png",
            "http://i.imgur.com/7Ygm7Az.png",
            "http://i.imgur.com/L6Pu2vo.png",
            "http://i.imgur.com/jQtWxXW.png",
            "http://i.imgur.com/ofRrImR.png",
            "http://i.imgur.com/iakl2wM.png",
            "http://i.imgur.com/f5lTzrP.png",
            "http://i.imgur.com/Ss9RF3x.png",
            "http://i.imgur.com/15tfO5B.png",
            "http://i.imgur.com/FM5J0gT.png"
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.vista_actividad_002);

        ArrayList<ElementoBomberos> elementosBomberos = new ArrayList<>();
        for (int i = 0; i < nombreElementoBomberos.length; i++) {
            ElementoBomberos nuevoElementoBomberos = new ElementoBomberos();
            nuevoElementoBomberos.setNombreElementoBomberos(nombreElementoBomberos[i]);
            nuevoElementoBomberos.setImagenElementoBomberos(imagenElementoBomberos[i]);
            nuevoElementoBomberos.setCorrespondeElementoBomberos(correspondeElementoBomberos[i]);
            elementosBomberos.add(nuevoElementoBomberos);
        }

        long seed = System.nanoTime();
        Collections.shuffle(elementosBomberos, new Random(seed));

        final ArrayList<ElementoBomberos> matrizAleatoriaElementosBomberos = new ArrayList<>();

        for(int i=0;i<12;i++) {
            ElementoBomberos nuevoElementoBomberos = new ElementoBomberos();
            nuevoElementoBomberos.setNombreElementoBomberos(elementosBomberos.get(i).getNombreElementoBomberos());
            nuevoElementoBomberos.setImagenElementoBomberos(elementosBomberos.get(i).getImagenElementoBomberos());
            nuevoElementoBomberos.setCorrespondeElementoBomberos(elementosBomberos.get(i).getCorrespondeElementoBomberos());
            matrizAleatoriaElementosBomberos.add(nuevoElementoBomberos);
        }

        final ElementoBomberosAdapter adapter = new ElementoBomberosAdapter(this.getApplicationContext(), matrizAleatoriaElementosBomberos, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaElementosBomberos);
        recyclerView.setAdapter(adapter);

        final int spanCount = 3;
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

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        instruccionActividad002.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                instruccionActividad002.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

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
