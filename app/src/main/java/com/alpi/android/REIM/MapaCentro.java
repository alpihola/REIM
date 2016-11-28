package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MapaCentro extends Activity {

    Button irAlEste;
    Button irAlNorte;
    Button irAlOeste;
    Button irAlSur;
    Button instruccionDebemosLlegarAlMuseo;
    ImageView ticketsMuseo;

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

        setContentView(R.layout.vista_mapa_centro);

        Bundle extras = getIntent().getExtras();
        final int valorGamificacion = extras.getInt("VALOR_GAMIFICACION");
        ticketsMuseo = (ImageView) findViewById(R.id.tickets);

        if(valorGamificacion == 0) {
            ticketsMuseo.setImageResource(R.drawable.tickets_0);
        } else if (valorGamificacion == 1) {
            ticketsMuseo.setImageResource(R.drawable.tickets_1);
        } else {
            ticketsMuseo.setImageResource(R.drawable.tickets_2);
        }

        irAlEste = (Button) findViewById(R.id.botonIrAlEste);
        irAlEste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaOeste.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlNorte = (Button) findViewById(R.id.botonIrAlNorte);
        irAlNorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaNorte.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlOeste = (Button) findViewById(R.id.botonIrAlOeste);
        irAlOeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaEste.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlSur = (Button) findViewById(R.id.botonIrAlSur);
        irAlSur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaSur.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        instruccionDebemosLlegarAlMuseo = (Button) findViewById(R.id.botonInstruccion);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.debemos_llegar_al_museo);
        instruccionDebemosLlegarAlMuseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        instruccionDebemosLlegarAlMuseo.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                instruccionDebemosLlegarAlMuseo.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

    }
}