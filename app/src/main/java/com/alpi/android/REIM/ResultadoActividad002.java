package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActividad002 extends Activity {

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
        String[] elementosBomberosFinales = extras.getStringArray("ELEMENTOS_BOMBEROS_FINALES");

        if (elementosBomberosFinales[0].equals("Vacío")) {
            resultado.append("El alumno no ha reconocido elementos del cuartel de bomberos");
        } else {
            resultado.append("Los elementos finales escogidos por el alumno como" +
                    " elementos pertenecientes al cuartel de bomberos son: ");
            resultado.append("\n");
            for (int i = 0; i < elementosBomberosFinales.length; i++) {
                resultado.append(elementosBomberosFinales[i]);
                resultado.append("\n");
            }
        }

        continuar = (Button) findViewById(R.id.botonMostrarResultado);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadoActividad002.this, MapaSur.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
