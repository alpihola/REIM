package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Login extends Activity {

    Button iniciarSesion;
    EditText usuario;
    EditText pass;
    static int id_usuario;
    static String salt, passwordBD, passwordEncrypted, passwordFinal;
    ProgressBar progressBar;
    HashText hashText = new HashText();

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

        setContentView(R.layout.vista_login);
        iniciarSesion = (Button) findViewById(R.id.botonIniciarSesion);
        usuario = (EditText) findViewById(R.id.ingresaUsuario);
        pass = (EditText) findViewById(R.id.ingresaPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarSesion iniciarSesion = new IniciarSesion();
                iniciarSesion.execute("");
            }
        });
    }

    public class IniciarSesion extends AsyncTask<String, String, String> {
        String toast = "";
        Boolean isSuccess = false;


        String user = usuario.getText().toString();
        String password = pass.getText().toString();


        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }

        @Override
        protected void onPostExecute(String toast) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Login.this, toast, Toast.LENGTH_SHORT).show();

            if (isSuccess) {
                Intent intent = new Intent(Login.this, SeleccionarCurso.class);
                startActivity(intent);
                finish();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            if (user.trim().equals("") || password.trim().equals(""))
                toast = "Por favor ingrese un Usuario y una Contraseña";
            else {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://mysql.ulearnet.com:3306/ulearnet_des";
                    Connection con = DriverManager.getConnection(url, "ulearnet_des", "ulearnet_des@");

                    if (con == null) {
                        toast = "Error en la conexion con el servidor de Ulearnet";
                    } else {

                        String query = "select id, salt, password from sf_guard_user where username= '" + user + "'";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);


                        if (rs.next()) {

                            id_usuario = rs.getInt("id");
                            salt = rs.getString("salt");
                            passwordBD = rs.getString("password");
                        }

                        passwordEncrypted = salt + password;
                        System.out.println(passwordEncrypted);
                        passwordFinal = hashText.sha1(passwordEncrypted);

                        String query2 = "select * from sf_guard_user where username= '" + user + "' and password = '" + passwordFinal + "' ";

                        Statement stm2 = con.prepareStatement(query2);
                        ResultSet rs2 = stm2.executeQuery(query2);

                        if (rs2.next()) {

                            toast = "Login Exitoso!";
                            isSuccess = true;

                        } else {
                            toast = "Usuario o Contraseña incorrectos";
                            isSuccess = false;
                        }

                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    toast = "Exceptions";
                }
            }

            return toast;
        }
    }
}
