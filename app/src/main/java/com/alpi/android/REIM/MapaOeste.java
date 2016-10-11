package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapaOeste extends Activity {

    Button irAlNorte;
    Button irAlCentro;
    Button irAlSur;

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

    }
}