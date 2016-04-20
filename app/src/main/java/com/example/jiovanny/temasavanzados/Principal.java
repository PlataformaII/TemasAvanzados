package com.example.jiovanny.temasavanzados;

import android.content.Intent;
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
    private static final int ACCESO_RED = 0;
    private static final int ENCRIPTACION = 1;
    private static final int ACELEROMETRO = 2;
    private static final int BRUJULA = 3;
    private static final int GPS = 4;
    private static final int MAPAS = 5;
    private static final int HILOS = 6;
    private static final int SERVICIO_WEB = 7;
    private static final String[] TEMAS = {"Acceso a la red", "Encriptacion", "Acelerometro", "Brujula", "GPS", "Mapas", "Hilos de ejecucion", "Servicios web"};



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

        listTemas = (ListView) findViewById(R.id.listTemas);
        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TEMAS);
        listTemas.setAdapter(adaptador);
        listTemas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case ACCESO_RED:
                        Intent i = new Intent(getApplicationContext(), red.class);
                        startActivity(i);
                        break;
                    case ENCRIPTACION:

                        break;

                    case ACELEROMETRO:
                        //Iniciar el uso de sensores de android
                        break;

                    case BRUJULA:
                        //Combinar el uso de sensores para un fin comun
                        break;

                    case GPS:
                        //Conocer la ubicacion actual del dispositivo
                        break;

                    case MAPAS:
                        //Hacer el uso de servicios de Google Maps
                        break;

                    case HILOS:
                        //Crear hilos de ejecucion tradicionales
                        break;

                    case SERVICIO_WEB:

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
