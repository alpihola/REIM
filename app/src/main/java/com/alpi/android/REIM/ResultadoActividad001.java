package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActividad001 extends Activity {

    TextView resultado;
    Button boton_continuar;

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
        final int valorGamificacion = extras.getInt("VALOR_GAMIFICACION");

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

        boton_continuar = (Button) findViewById(R.id.botonContinuar);
        boton_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadoActividad001.this, MapaNorte.class);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
                finish();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        boton_continuar.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boton_continuar.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

    }
}
