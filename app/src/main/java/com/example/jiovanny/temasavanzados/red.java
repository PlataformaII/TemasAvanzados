package com.example.jiovanny.temasavanzados;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            MiNavegador nav= new MiNavegador();
            nav.setContext(this);
            webEjemplo.setWebViewClient(nav);
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
class MiNavegador extends WebViewClient{
    private long tiempo;
    private Context context;
    public void setContext(Context c){
        context =c;
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Uri uri=Uri.parse(url);
        String host=uri.getHost();
        if (host.equals("es.wikipedia.org")||host.equals("es.m.wikipedia.org")){
            view.loadUrl(url);
            return false;
        }
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        tiempo=System.currentTimeMillis();
        Toast.makeText(context,"Se esta empezando a cargar la página",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        tiempo=tiempo-System.currentTimeMillis();
        Date date = new Date(tiempo);
        SimpleDateFormat f=new SimpleDateFormat("mm:ss:SSS",Locale.getDefault());
        String t1=f.format(date);
        Toast.makeText(context,t1,Toast.LENGTH_SHORT).show();
        /*Una forma de hacerlo
        String tiempo= new SimpleDateFormat("mm:ss:SSS", Locale.getDefault()).format(date);
        Toast.makeText(context,tiempo,Toast.LENGTH_SHORT).show();*/
    }
}
