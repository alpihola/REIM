package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapaCentro extends Activity {

    Button irAlEste;
    Button irAlNorte;
    Button irAlOeste;
    Button irAlSur;
    Button instruccionDebemosLlegarAlMuseo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vista_mapa_centro);

        irAlEste = (Button) findViewById(R.id.botonIrAlEste);
        irAlEste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaEste.class);
                startActivity(intent);
            }
        });

        irAlNorte = (Button) findViewById(R.id.botonIrAlNorte);
        irAlNorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaNorte.class);
                startActivity(intent);
            }
        });

        irAlOeste = (Button) findViewById(R.id.botonIrAlOeste);
        irAlOeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaOeste.class);
                startActivity(intent);
            }
        });

        irAlSur = (Button) findViewById(R.id.botonIrAlSur);
        irAlSur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaCentro.this, MapaSur.class);
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

    }
}