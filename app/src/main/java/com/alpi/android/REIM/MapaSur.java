package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapaSur extends Activity {

    Button irAlEste;
    Button irAlCentro;
    Button irAlOeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vista_mapa_sur);

        irAlEste = (Button) findViewById(R.id.botonIrAlEste);
        irAlEste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaSur.this, MapaEste.class);
                startActivity(intent);
            }
        });

        irAlCentro = (Button) findViewById(R.id.botonIrAlCentro);
        irAlCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaSur.this, MapaCentro.class);
                startActivity(intent);
            }
        });

        irAlOeste = (Button) findViewById(R.id.botonIrAlOeste);
        irAlOeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaSur.this, MapaOeste.class);
                startActivity(intent);
            }
        });

    }
}