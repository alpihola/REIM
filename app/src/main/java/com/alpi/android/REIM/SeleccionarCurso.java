package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v4.view.MotionEventCompat;
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

public class SeleccionarCurso extends Activity {

    Button seleccionarAlumno;
    ProgressBar progressBar;
    ArrayList<String> listaCursos = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView listViewCursos;
    static String elemento_lista_curso, nombre_curso;

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

        setContentView(R.layout.vista_seleccionar_curso);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Weas para lista de cursos
        listViewCursos = (ListView) findViewById(R.id.listaCursos);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaCursos);
        listViewCursos.setAdapter(arrayAdapter);

        progressBar = (ProgressBar) findViewById(R.id.progressBarSeleccionCurso);
        progressBar.setVisibility(View.GONE);

        //Cargamos cursos correspondientes al usuario login.
        consulta query = new consulta();
        query.execute();

        seleccionarAlumno = (Button) findViewById(R.id.botonSeleccionarAlumno);

        listViewCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            elemento_lista_curso = String.valueOf(parent.getItemAtPosition(position));
            Toast.makeText(SeleccionarCurso.this, "Seleccionó el curso: " + elemento_lista_curso, Toast.LENGTH_LONG).show();
            }
        }
        );
        seleccionarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elemento_lista_curso != null) {
                    Intent intent = new Intent(SeleccionarCurso.this, SeleccionarAlumno.class);
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

                String query = "select nombre FROM curso c , PERTENECE_reim p , sf_guard_user u where u.id='" + Login.id_usuario + "' and p.sf_guard_user_id='" + Login.id_usuario + "' and p.CURSO_id_curso=c.id";

                Statement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    nombre_curso = resultSet.getString("nombre");
                    listaCursos.add(nombre_curso);
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
