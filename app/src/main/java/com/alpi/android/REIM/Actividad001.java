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

public class Actividad001 extends AppCompatActivity implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;
    Button instruccionActividad001;
    Button mostrarResultado;

    private final String nombreAlimento[] = {
            "Brócoli",
            "Taco",
            "Champiñón",
            "Queso",
            "Platano",
            "Sandía",
            "Dona",
            "Doritos",
            "Empanada",
            "Completo",
            "Helado",
            "Cola-Cola",
            "Naranja",
            "Papas Fritas",
            "Manzana",
            "Sopa",
            "Uvas",
            "Hamburguesa",
            "Torta",
            "Pizza"
    };

    private final boolean correspondeAlimento[] = {
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
            false,
            false,
            true,
            false,
            true,
            true,
            true,
            false,
            false,
            false
    };

    private final String imagenAlimento[] = {
            "http://i.imgur.com/GuXfgye.png",
            "http://i.imgur.com/9F28iz7.png",
            "http://i.imgur.com/Z8wDHyd.png",
            "http://i.imgur.com/8WTEaBS.png",
            "http://i.imgur.com/Nelb03n.png",
            "http://i.imgur.com/wnqG3jU.png",
            "http://i.imgur.com/Kw1Kh85.png",
            "http://i.imgur.com/tW2uM3z.png",
            "http://i.imgur.com/Al06K5r.png",
            "http://i.imgur.com/4tuGOU1.png",
            "http://i.imgur.com/oPOgFku.png",
            "http://i.imgur.com/ieshDVV.png",
            "http://i.imgur.com/a0VVWk1.png",
            "http://i.imgur.com/EfL36dK.png",
            "http://i.imgur.com/RC1b262.png",
            "http://i.imgur.com/7b6sBVp.png",
            "http://i.imgur.com/0kLfAPf.png",
            "http://i.imgur.com/pMnm0nZ.png",
            "http://i.imgur.com/kGHg1GX.png",
            "http://i.imgur.com/baPkYGR.png"

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

        setContentView(R.layout.vista_actividad_001);

        Bundle extras = getIntent().getExtras();
        final int valorGamificacionPrevio = extras.getInt("VALOR_GAMIFICACION");
        final int valorGamificacionFinal = valorGamificacionPrevio + 1;

        final ArrayList<Alimento> alimentos = new ArrayList<>();

        for(int i=0;i<nombreAlimento.length;i++) {
            Alimento nuevoAlimento = new Alimento();
            nuevoAlimento.setNombreAlimento(nombreAlimento[i]);
            nuevoAlimento.setImagenAlimento(imagenAlimento[i]);
            nuevoAlimento.setCorrespondeAlimento(correspondeAlimento[i]);
            alimentos.add(nuevoAlimento);
        }

        long seed = System.nanoTime();
        Collections.shuffle(alimentos, new Random(seed));

        final ArrayList<Alimento> matrizAleatoriaAlimentos = new ArrayList<>();

        for(int i=0;i<12;i++) {
            Alimento nuevoAlimento = new Alimento();
            nuevoAlimento.setNombreAlimento(alimentos.get(i).getNombreAlimento());
            nuevoAlimento.setImagenAlimento(alimentos.get(i).getImagenAlimento());
            nuevoAlimento.setCorrespondeAlimento(alimentos.get(i).getCorrespondeAlimento());
            matrizAleatoriaAlimentos.add(nuevoAlimento);
        }

        final AlimentoAdapter adapter = new AlimentoAdapter(this.getApplicationContext(), matrizAleatoriaAlimentos, this);

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
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent = new Intent(Actividad001.this, ResultadoActividad001.class);
                        intent.putExtra("ALIMENTOS_FINALES", adapter.getResult());
                        intent.putExtra("VALOR_GAMIFICACION", valorGamificacionFinal);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        instruccionActividad001.setAnimation(animation);
        mostrarResultado.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                instruccionActividad001.startAnimation(animation);
                mostrarResultado.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startSwipe(viewHolder);
    }

}