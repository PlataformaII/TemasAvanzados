package com.example.daniel.temasavanzados;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Principal extends AppCompatActivity {
    ListView listTemas;
    private static final int ACCESO_RED=0;
    private static final int ENCRIPTACION=1;
    private static final int ACELEROMETRO=2;
    private static final int BRUJULA=3;
    private static final int GPS=4;
    private static final int MAPAS=5;
    private static final int HILOS=6;
    private static final int SERVICIO_WEB=7;
    private static final String [] TEMAS={"Acceso a la red","Encriptación","Acelerómetro","Brújula","GPS","Mapas","Hilos de ejecución","Servicios Web"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listTemas=(ListView)findViewById(R.id.listTemas);
        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,TEMAS);
        listTemas.setAdapter(adaptador);
        listTemas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case ACCESO_RED:
                        //llamar actividad de Acceso Red
                        break;
                    case ENCRIPTACION:
                        //Encriptar y desencriptar arreglo de bytes
                        break;
                    case ACELEROMETRO:
                        //Iniciar el uso de sensonres de Android
                        break;
                    case BRUJULA:
                        //Combinar el uso de sensores para un fin común
                        break;
                    case GPS:
                        //Conocer la ubicación actual del dispositivo
                        break;
                    case MAPAS:
                        //Hacer uso del servicio de Google Maps
                        break;
                    case HILOS:
                        //Crear hilos de ejecución tradicionales y con AsynTask
                        break;
                    case SERVICIO_WEB:
                        //Realizar una consulta a un servidor y procesar informacion
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
