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

public class MapaNorte extends Activity {

    Button irAlOeste;
    Button irAlEste;
    Button irAlCentro;
    Button casaActividad001;
    Button instruccionMiraCasa;
    ImageView ticketsMuseo;
    int contadorClickInstrucciones;

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

        setContentView(R.layout.vista_mapa_norte);

        Bundle extras = getIntent().getExtras();
        final int contadorClickMapaIn = extras.getInt("CONTADOR_CLICK_MAPA");
        final int contadorClickInstruccionesIn = extras.getInt("CONTADOR_CLICK_INSTRUCCIONES");
        final int valorGamificacion = extras.getInt("VALOR_GAMIFICACION");
        final int contadorClickMapaOut = contadorClickMapaIn + 1;

        ticketsMuseo = (ImageView) findViewById(R.id.tickets);

        if(valorGamificacion == 0) {
            ticketsMuseo.setImageResource(R.drawable.tickets_0);
        } else if (valorGamificacion == 1) {
            ticketsMuseo.setImageResource(R.drawable.tickets_1);
        } else {
            ticketsMuseo.setImageResource(R.drawable.tickets_2);
        }

        instruccionMiraCasa = (Button) findViewById(R.id.botonInstruccion);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mira_casa);
        instruccionMiraCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                contadorClickInstrucciones += 1;
            }
        });

        irAlOeste = (Button) findViewById(R.id.botonIrAlEste);
        irAlOeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int contadorClickInstruccionesOut = contadorClickInstruccionesIn + contadorClickInstrucciones;
                Intent intent = new Intent(MapaNorte.this, MapaEste.class);
                intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapaOut);
                intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlEste = (Button) findViewById(R.id.botonIrAlOeste);
        irAlEste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int contadorClickInstruccionesOut = contadorClickInstruccionesIn + contadorClickInstrucciones;
                Intent intent = new Intent(MapaNorte.this, MapaOeste.class);
                intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapaOut);
                intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlCentro = (Button) findViewById(R.id.botonIrAlCentro);
        irAlCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int contadorClickInstruccionesOut = contadorClickInstruccionesIn + contadorClickInstrucciones;
                Intent intent = new Intent(MapaNorte.this, MapaCentro.class);
                intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapaOut);
                intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        casaActividad001 = (Button) findViewById(R.id.botonCasaActividad001);
        casaActividad001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int contadorClickInstruccionesOut = contadorClickInstruccionesIn + contadorClickInstrucciones;
                Intent intent = new Intent(MapaNorte.this, Actividad001.class);
                intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapaOut);
                intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        instruccionMiraCasa.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                instruccionMiraCasa.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }
}