package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class Museo extends Activity {

    Button terminar;
    ImageView ticketsMuseo;
    final int valorGamificacionNuevo = 0;
    int contadorClickMapa, contadorClickInstrucciones;

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

        setContentView(R.layout.vista_museo);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.felicitaciones);
        mediaPlayer.start();

        ticketsMuseo = (ImageView) findViewById(R.id.tickets);
        ticketsMuseo.setImageResource(R.drawable.tickets_0);

        Bundle extras = getIntent().getExtras();
        contadorClickMapa = extras.getInt("CONTADOR_CLICK_MAPA");
        contadorClickInstrucciones = extras.getInt("CONTADOR_CLICK_INSTRUCCIONES");
        System.out.println("Contador instrucciones: "+ contadorClickInstrucciones);
        System.out.println("Contador click mapa: "+ contadorClickMapa);

        terminar = (Button) findViewById(R.id.terminar);
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int contadorClickInstruccionesOut = 0;
                final int contadorClickMapaOut = 0;
                Intent intent = new Intent(Museo.this, MapaOeste.class);
                intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapaOut);
                intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacionNuevo);
                startActivity(intent);
                finish();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        terminar.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                terminar.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

}
