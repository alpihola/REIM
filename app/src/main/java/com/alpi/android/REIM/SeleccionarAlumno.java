package com.alpi.android.REIM;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SeleccionarAlumno extends AppCompatActivity {

    Button iniciarReim;
    ProgressBar progressBar;
    ArrayList<String> listaAlumnos = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView listViewAlumnos;
    SeleccionarCurso seleccionarCurso = new SeleccionarCurso();
    static int valorGamificacion = 0;
    static int contadorClickInstrucciones = 0;

    static String elemento_lista_alumno;
    static int id_curso;
    static String nombre_alumno;
    static String apellido_pa_alumno;
    static String apellido_ma_alumno;
    static String fecha_inicio;
    Fecha fecha = new Fecha();

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

        setContentView(R.layout.vista_seleccionar_alumno);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        listViewAlumnos = (ListView) findViewById(R.id.listaAlumnos);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAlumnos);
        listViewAlumnos.setAdapter(arrayAdapter);


        progressBar = (ProgressBar) findViewById(R.id.progressBarSeleccionAlumno);
        progressBar.setVisibility(View.GONE);

        consulta query = new consulta();
        query.execute();

        iniciarReim = (Button) findViewById(R.id.botonIniciarReim);
        listViewAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                elemento_lista_alumno = String.valueOf(parent.getItemAtPosition(position));
                String partes[] = elemento_lista_alumno.split("\\s+");

                nombre_alumno = partes[0];
                apellido_pa_alumno = partes[1];
                apellido_ma_alumno = partes[2];

                Toast.makeText(SeleccionarAlumno.this, "Seleccionó al alumno: " + elemento_lista_alumno, Toast.LENGTH_LONG).show();
            }
        });

        iniciarReim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elemento_lista_alumno != null) {
                    fecha_inicio = fecha.fechaActual;
                    Intent intent = new Intent(SeleccionarAlumno.this, MapaOeste.class);
                    intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstrucciones);
                    intent.putExtra("VALOR_GAMIFICACION", valorGamificacion);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public class consulta extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }


        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://mysql.ulearnet.com:3306/ulearnet_des";
                Connection connection = DriverManager.getConnection(url, "ulearnet_des", "ulearnet_des@");

                String query = "select id from curso where nombre ='" + seleccionarCurso.elemento_lista_curso + "' ";
                Statement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    id_curso = resultSet.getInt("id");
                    System.out.println(id_curso);
                }

                System.out.println("id_curso===" + id_curso);

                String query2 = "select u.nombres, u.apellido_paterno, u.apellido_materno from sf_guard_user u, PERTENECE_reim p, curso c where c.id='" + id_curso + "' and p.CURSO_id_curso='" + id_curso + "' and u.id=p.sf_guard_user_id and u.alumno !=0";
                Statement statement2 = connection.prepareStatement(query2);
                ResultSet resultSet2 = statement2.executeQuery(query2);

                while (resultSet2.next()) {
                    String nombre = resultSet2.getString("nombres");
                    String apellido_pa = resultSet2.getString("apellido_paterno");
                    String apellido_ma = resultSet2.getString("apellido_materno");
                    listaAlumnos.add(nombre + " " + apellido_pa + " " + apellido_ma);
                }

                connection.close();//cierra conexion
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case (MotionEvent.ACTION_UP):
                arrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
