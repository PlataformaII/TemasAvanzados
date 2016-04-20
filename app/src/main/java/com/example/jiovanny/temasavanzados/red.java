package com.example.jiovanny.temasavanzados;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class red extends AppCompatActivity {

    WebView webEjemplo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red);
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

        /*Intent  i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(i);*/
        webEjemplo = (WebView) findViewById(R.id.webEjemplo);

        if (isDisponible()){
            webEjemplo.loadUrl("http://es.wikipedia.org");
        } else {
            Toast.makeText(red.this, "Ocurrio un error al conectarse", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public boolean isDisponible(){
        ConnectivityManager manager =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager == null)
            return false;

        NetworkInfo info = manager.getActiveNetworkInfo();
        if (!info.isAvailable())
            return false;
        return true;


    }

}
