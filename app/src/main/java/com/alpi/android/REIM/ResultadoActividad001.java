package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActividad001 extends Activity {

    TextView resultado;
    Button continuar;

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

        setContentView(R.layout.vista_resultado_actividad);
        resultado = (TextView) findViewById(R.id.resultado);

        Bundle extras = getIntent().getExtras();
        String[] alimentosFinales = extras.getStringArray("ALIMENTOS_FINALES");

        if (alimentosFinales[0].equals("Vacío")) {
            resultado.append("El alumno no ha escogido elementos saludables");
        } else {
        resultado.append("Los elementos finales escogidos por el alumno como" +
                " elementos saludables son: ");
        resultado.append("\n");
        for (int i = 0; i < alimentosFinales.length; i++) {
            resultado.append(alimentosFinales[i]);
            resultado.append("\n");
        }
        }

        continuar = (Button) findViewById(R.id.botonMostrarResultado);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadoActividad001.this, MapaNorte.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
