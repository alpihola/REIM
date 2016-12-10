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


public class Museo extends Activity {

    Button terminar;
    ImageView ticketsMuseo;
    final int valorGamificacionNuevo = 0;
    static int id_sesion, id_pertenece, id_pertenece_tabla;
    boolean isSuccess = false;
    Fecha fecha = new Fecha();
    String fechaTerminoSesion = fecha.fechaActual;

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

        consulta finalizarSesion = new consulta();
        finalizarSesion.execute();

        ticketsMuseo = (ImageView) findViewById(R.id.tickets);
        ticketsMuseo.setImageResource(R.drawable.tickets_0);

        terminar = (Button) findViewById(R.id.terminar);
        terminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Museo.this, MapaOeste.class);
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

    public class consulta extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... args) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://mysql.ulearnet.com:3306/ulearnet_des";//"jdbc:mysql:///10.0.3.2:3306/dbname"
                Connection connection = DriverManager.getConnection(url, "ulearnet_des", "ulearnet_des@");

                String getSesion = "SELECT id_sesion FROM ASIGNA_REALIZAR_SESION ORDER BY id_sesion DESC LIMIT 1";
                Statement statement = connection.prepareStatement(getSesion);
                ResultSet resultSet = statement.executeQuery(getSesion);

                while(resultSet.next()) {
                    id_sesion = resultSet.getInt("id_sesion");
                    System.out.println(id_sesion);
                }

                String getNombreAlumno = "SELECT id FROM sf_guard_user WHERE nombres= '"+SeleccionarAlumno.nombre_alumno+"' AND apellido_paterno = '"+
                        SeleccionarAlumno.apellido_pa_alumno+"'";

                Statement statement1 = connection.prepareStatement(getNombreAlumno);
                ResultSet resultSet1 = statement1.executeQuery(getNombreAlumno);

                while(resultSet1.next()) {
                    id_pertenece = resultSet1.getInt("id");
                    System.out.println("id_pertenece_alumno)=="+id_pertenece);
                }

                String getId = "SELECT id FROM PERTENECE_reim WHERE sf_guard_user_id="+id_pertenece+"";
                Statement statement2 = connection.prepareStatement(getId);
                ResultSet resultSet2 = statement2.executeQuery(getId);


                while(resultSet2.next()) {
                    id_pertenece_tabla = resultSet2.getInt("id");
                }
                System.out.println("id_pertenece_tabla=="+id_pertenece_tabla);

                PreparedStatement setSesion = connection.prepareStatement("INSERT INTO ASIGNA_REALIZAR_SESION" +
                        " (REIM_id_reim, PERTENECE_id, datetime_inicio_sesion, datetime_termino_sesion) VALUES (3, ?, ?, ?)");
                setSesion.setInt(1, id_pertenece_tabla);
                setSesion.setTimestamp(2, Timestamp.valueOf(MapaOeste.fechaInicioSesion));
                setSesion.setTimestamp(3, Timestamp.valueOf(fechaTerminoSesion));
                setSesion.execute();
                setSesion.close();
                isSuccess = true;

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                isSuccess = false;
            }
            return null;
        }

    }
}
