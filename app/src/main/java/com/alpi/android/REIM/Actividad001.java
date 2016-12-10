package com.alpi.android.REIM;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


import com.alpi.android.REIM.helper.OnStartDragListener;
import com.alpi.android.REIM.helper.SimpleItemTouchHelperCallback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.alpi.android.REIM.Museo.id_sesion;

public class Actividad001 extends AppCompatActivity implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;
    Button instruccionActividad001, finalizarActividad;
    static String fechaInicioActividad, fechaTerminoActividad, fechaDismiss, matrizInicial, matrizFinal;
    Fecha datetimeInicioActividad = new Fecha();
    private int correcto = 0;
    int id_matriz, contadorTouch, contadorInstruccionesActividad;
    int contadorClickInstrucciones;

    private final String nombreAlimento[] = {
            "Brócoli",
            "Taco",
            "Champiñón",
            "Queso",
            "Platano",
            "Sandía",
            "Dona",
            "Doritos",
            "Empanada",
            "Completo",
            "Helado",
            "Cola-Cola",
            "Naranja",
            "Papas Fritas",
            "Manzana",
            "Sopa",
            "Uvas",
            "Hamburguesa",
            "Torta",
            "Pizza"
    };

    private final boolean correspondeAlimento[] = {
            true,
            false,
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            true,
            false,
            true,
            true,
            true,
            false,
            false,
            false
    };

    private final String imagenAlimento[] = {
            "http://i.imgur.com/GuXfgye.png",
            "http://i.imgur.com/9F28iz7.png",
            "http://i.imgur.com/Z8wDHyd.png",
            "http://i.imgur.com/8WTEaBS.png",
            "http://i.imgur.com/Nelb03n.png",
            "http://i.imgur.com/wnqG3jU.png",
            "http://i.imgur.com/Kw1Kh85.png",
            "http://i.imgur.com/tW2uM3z.png",
            "http://i.imgur.com/Al06K5r.png",
            "http://i.imgur.com/4tuGOU1.png",
            "http://i.imgur.com/oPOgFku.png",
            "http://i.imgur.com/ieshDVV.png",
            "http://i.imgur.com/a0VVWk1.png",
            "http://i.imgur.com/EfL36dK.png",
            "http://i.imgur.com/RC1b262.png",
            "http://i.imgur.com/7b6sBVp.png",
            "http://i.imgur.com/0kLfAPf.png",
            "http://i.imgur.com/pMnm0nZ.png",
            "http://i.imgur.com/kGHg1GX.png",
            "http://i.imgur.com/baPkYGR.png"

    };

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

        setContentView(R.layout.vista_actividad_001);

        Bundle extras = getIntent().getExtras();
        final int contadorClickMapa = extras.getInt("CONTADOR_CLICK_MAPA");
        final int contadorClickInstruccionesIn = extras.getInt("CONTADOR_CLICK_INSTRUCCIONES");
        final int valorGamificacionPrevio = extras.getInt("VALOR_GAMIFICACION");
        final int valorGamificacionFinal = valorGamificacionPrevio + 1;
        fechaInicioActividad = datetimeInicioActividad.fechaActual;

        final ArrayList<Alimento> alimentos = new ArrayList<>();

        for(int i=0;i<nombreAlimento.length;i++) {
            Alimento nuevoAlimento = new Alimento();
            nuevoAlimento.setNombreAlimento(nombreAlimento[i]);
            nuevoAlimento.setImagenAlimento(imagenAlimento[i]);
            nuevoAlimento.setCorrespondeAlimento(correspondeAlimento[i]);
            alimentos.add(nuevoAlimento);
        }

        long seed = System.nanoTime();
        Collections.shuffle(alimentos, new Random(seed));

        final ArrayList<Alimento> matrizAleatoriaAlimentos = new ArrayList<>();

        for(int i=0;i<12;i++) {
            Alimento nuevoAlimento = new Alimento();
            nuevoAlimento.setNombreAlimento(alimentos.get(i).getNombreAlimento());
            nuevoAlimento.setImagenAlimento(alimentos.get(i).getImagenAlimento());
            nuevoAlimento.setCorrespondeAlimento(alimentos.get(i).getCorrespondeAlimento());
            matrizAleatoriaAlimentos.add(nuevoAlimento);
        }

        final AlimentoAdapter adapter = new AlimentoAdapter(this.getApplicationContext(), matrizAleatoriaAlimentos, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.matrizAlimentos);
        recyclerView.setAdapter(adapter);

        final int spanCount = getResources().getInteger(R.integer.grid_columns);
        final GridLayoutManager layoutManager = new GridLayoutManager(this.getApplicationContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        instruccionActividad001 = (Button) findViewById(R.id.botonInstruccion);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.instruccion_actividad_001);
        instruccionActividad001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                contadorInstruccionesActividad += 1;
                contadorClickInstrucciones += 1;
            }
        });

        finalizarActividad = (Button) findViewById(R.id.botonMostrarResultado);
        finalizarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fecha datetimeTerminoActividad = new Fecha();
                fechaTerminoActividad = datetimeTerminoActividad.fechaActual;
                matrizFinal = TextUtils.join(", ", adapter.getMatrizFinal());
                if(adapter.getCorrespondeMatrizFinal()/adapter.getMatrizFinal().length > 0.5) {
                    correcto = 1;
                } else {
                    correcto = 0;
                }
                consulta finalizarActividad = new consulta();
                finalizarActividad.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final int contadorClickInstruccionesOut = contadorClickInstruccionesIn + contadorClickInstrucciones;
                        Intent intent = new Intent(Actividad001.this, MapaNorte.class);
                        intent.putExtra("CONTADOR_CLICK_MAPA", contadorClickMapa);
                        intent.putExtra("CONTADOR_CLICK_INSTRUCCIONES", contadorClickInstruccionesOut);
                        intent.putExtra("VALOR_GAMIFICACION", valorGamificacionFinal);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        });


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_boton_instrucciones);
        instruccionActividad001.setAnimation(animation);
        finalizarActividad.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                instruccionActividad001.startAnimation(animation);
                finalizarActividad.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        matrizInicial = TextUtils.join(", ", adapter.getMatrizInicial());
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startSwipe(viewHolder);
        Fecha datetimeDismiss = new Fecha();
        fechaDismiss = datetimeDismiss.fechaActual;
        contadorTouch += 1;
    }

    public class consulta extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {

                //conexion
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://mysql.ulearnet.com:3306/ulearnet_des";
                Connection connection = DriverManager.getConnection(url, "ulearnet_des", "ulearnet_des@");

                String getSesion = "SELECT id_sesion FROM ASIGNA_REALIZAR_SESION ORDER BY id_sesion DESC LIMIT 1";
                Statement statement = connection.prepareStatement(getSesion);
                ResultSet resultSet = statement.executeQuery(getSesion);

                while(resultSet.next()) {
                    id_sesion = resultSet.getInt("id_sesion");
                    System.out.println(id_sesion);
                }

                String setMatrizElemento = "INSERT INTO MATRIZ_ELEMENTO (matriz_inicial, matriz_resultante) VALUES (?, ?)";
                PreparedStatement statement2 = connection.prepareStatement(setMatrizElemento);
                statement2.setString(1, matrizInicial);
                statement2.setString(2, matrizFinal);
                statement2.execute();
                statement2.close();

                String getMatrizId = "SELECT id_matriz FROM MATRIZ_ELEMENTO ORDER BY id_matriz DESC LIMIT 1";
                Statement statement1 = connection.prepareStatement(getMatrizId);
                ResultSet resultSet1 = statement1.executeQuery(getMatrizId);

                while(resultSet1.next()) {
                    id_matriz = resultSet1.getInt("id_matriz");
                    System.out.println(id_matriz);
                }

                //declaro el statement con la query para despues ejecutarla
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ALUMNO_REALIZA_ACTIVIDAD " +
                        "(datetime_inicio_actividad, datetime_touch, datetime_termino_actividad, correcto, ACTIVIDAD_id_actividad, ASIGNA" +
                        "_REALIZAR_SESION_id_sesion, MATRIZ_ELEMENTO_id_matriz, contador_click_totales, contador_click_instrucciones)" +
                        " VALUES (?, ?, ?, ?, 4, ?, ?, ?, ?)");
                preparedStatement.setString(1, fechaInicioActividad);
                preparedStatement.setString(2, fechaDismiss);
                preparedStatement.setString(3, fechaTerminoActividad);
                preparedStatement.setInt(4, correcto);
                preparedStatement.setInt(5, id_sesion);
                preparedStatement.setInt(6, id_matriz);
                preparedStatement.setInt(7, contadorTouch);
                preparedStatement.setInt(8, contadorInstruccionesActividad);

                preparedStatement.execute();//se ejecuta la query
                preparedStatement.close();//cierro el statement con la query
                connection.close();//cierro la conexion

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}