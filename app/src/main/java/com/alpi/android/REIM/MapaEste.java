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

public class MapaEste extends Activity {

    Button irAlNorte;
    Button irAlCentro;
    Button irAlSur;
    Button instruccionMiraMuseo;
    Button museo;
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

        setContentView(R.layout.vista_mapa_este);

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

        museo = (Button) findViewById(R.id.botonMuseo);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.para_entrar_museo);
        museo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valorGamificacion<2){
                    mediaPlayer.start();
                } else {
                    final int valorGamificacion = 0;
                    Intent intent = new Intent(MapaEste.this, Museo.class);
                    intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                    startActivity(intent);
                }

            }
        });

        irAlNorte = (Button) findViewById(R.id.botonIrAlNorte);
        irAlNorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaEste.this, MapaNorte.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlCentro = (Button) findViewById(R.id.botonIrAlCentro);
        irAlCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaEste.this, MapaCentro.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlSur = (Button) findViewById(R.id.botonIrAlSur);
        irAlSur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaEste.this, MapaSur.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        instruccionMiraMuseo = (Button) findViewById(R.id.botonInstruccion);
        final MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.mira_museo);
        instruccionMiraMuseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer2.start();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        instruccionMiraMuseo.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                instruccionMiraMuseo.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

    }
}