package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapaOeste extends Activity {

    Button irAlNorte;
    Button irAlCentro;
    Button irAlSur;
    Button instruccionMiraMuseo;
    Button instruccionRecolectarTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vista_mapa_oeste);
        irAlNorte = (Button) findViewById(R.id.botonIrAlNorte);
        irAlNorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaOeste.this, MapaNorte.class);
                startActivity(intent);
            }
        });

        irAlCentro = (Button) findViewById(R.id.botonIrAlCentro);
        irAlCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaOeste.this, MapaCentro.class);
                startActivity(intent);
            }
        });

        irAlSur = (Button) findViewById(R.id.botonIrAlSur);
        irAlSur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaOeste.this, MapaSur.class);
                startActivity(intent);
            }
        });

        instruccionRecolectarTickets = (Button) findViewById(R.id.botonMuseoActividadEsteban);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.para_entrar_museo);
        instruccionRecolectarTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
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

    }
}