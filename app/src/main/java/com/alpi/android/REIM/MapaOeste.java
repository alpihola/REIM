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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class MapaOeste extends Activity {

    Button irAlNorte;
    Button irAlCentro;
    Button irAlSur;
    Button instruccionDebemosLLegarMuseo;
    ImageView ticketsMuseo;
    static String fechaInicioSesion;
    Fecha fecha = new Fecha();
    int contadorClickInstrucciones;
    static int id_sesion, id_pertenece, id_pertenece_tabla;

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
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
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

        setContentView(R.layout.vista_mapa_oeste);

        Bundle extras = getIntent().getExtras();
        final int contadorClickMapaIn = extras.getInt("CONTADOR_CLICK_MAPA");
        final int contadorClickInstruccionesIn = extras.getInt("CONTADOR_CLICK_INSTRUCCIONES");
        final int valorGamificacion = extras.getInt("VALOR_GAMIFICACION");
        final int contadorClickMapaOut = contadorClickMapaIn + 1;

        fechaInicioSesion = fecha.fechaActual;
        ticketsMuseo = (ImageView) findViewById(R.id.tickets);

        if (valorGamificacion == 0) {
            ticketsMuseo.setImageResource(R.drawable.tickets_0);
        } else if (valorGamificacion == 1) {
            ticketsMuseo.setImageResource(R.drawable.tickets_1);
        } else {
            ticketsMuseo.setImageResource(R.drawable.tickets_2);
        }

        consulta iniciarSesion = new consulta();
        iniciarSesion.execute();

        instruccionDebemosLLegarMuseo = (Button) findViewById(R.id.botonInstruccion);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.debemos_llegar_al_museo);
        instruccionDebemosLLegarMuseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                contadorClickInstrucciones += 1;
            }
        });

        irAlNorte = (Button) findViewById(R.id.botonIrAlNorte);
        irAlNorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int contadorClickInstruccionesOut = contadorClickInstruccionesIn + contadorClickInstrucciones;
                Intent intent = new Intent(MapaOeste.this, MapaNorte.class);
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
                Intent intent = new Intent(MapaOeste.this, MapaCentro.class);
                intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapaOut);
                intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        irAlSur = (Button) findViewById(R.id.botonIrAlSur);
        irAlSur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int contadorClickInstruccionesOut = contadorClickInstruccionesIn + contadorClickInstrucciones;
                Intent intent = new Intent(MapaOeste.this, MapaSur.class);
                intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapaOut);
                intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                startActivity(intent);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        instruccionDebemosLLegarMuseo.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                instruccionDebemosLLegarMuseo.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

    public class consulta extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... args) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://mysql.ulearnet.com:3306/ulearnet_des";
                Connection connection = DriverManager.getConnection(url, "ulearnet_des", "ulearnet_des@");

                String query3 = "select id from sf_guard_user where nombres='" + SeleccionarAlumno.nombre_alumno + "' and apellido_paterno= '" +
                        SeleccionarAlumno.apellido_pa_alumno + "' and apellido_materno= '" + SeleccionarAlumno.apellido_ma_alumno + "'";
                Statement stm3 = connection.prepareStatement(query3);
                ResultSet rs3 = stm3.executeQuery(query3);


                while (rs3.next()) {
                    id_pertenece = rs3.getInt("id");
                    System.out.println("id_pertenece_alumno==" + id_pertenece);
                }

                String query4 = "select id from PERTENECE_reim where sf_guard_user_id= '" + id_pertenece + "' ";
                Statement stm4 = connection.prepareStatement(query4);
                ResultSet rs4 = stm4.executeQuery(query4);


                while (rs4.next()) {
                    id_pertenece_tabla = rs4.getInt("id");

                }
                System.out.println("id_pertenece_tabla==" + id_pertenece_tabla);

                PreparedStatement insert = connection.prepareStatement("INSERT INTO ASIGNA_REALIZAR_SESION " +
                        "(REIM_id_reim, PERTENECE_id, datetime_inicio_sesion) VALUES (3,?,?)");
                insert.setInt(1, id_pertenece_tabla);
                insert.setTimestamp(2, Timestamp.valueOf(fechaInicioSesion));
                insert.execute();
                insert.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}