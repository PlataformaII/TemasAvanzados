package com.example.jiovanny.temasavanzados;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Acelerometro extends AppCompatActivity implements SensorEventListener{

    TextView lblSensor;
    long tiempo;
    boolean color;
    SensorManager manager;
    MediaPlayer tono;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lblSensor = (TextView) findViewById(R.id.lblSensor);
        tiempo = System.currentTimeMillis();
        color = false;
        lblSensor.setBackgroundColor(Color.RED);
        tono = MediaPlayer.create(this, R.raw.tono);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float aceleracion[] = event.values;
            float x = aceleracion[0];
            float y = aceleracion[1];
            float z = aceleracion[2];

            float resultado = (x*x + y*y + z*z) /
                    (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

            if (resultado >= 2) {
                long tiempoReal = event.timestamp;
                if (tiempoReal - tiempo >= 500) {
                    tono.start();
                    Toast.makeText(this,"Se agitó el dispositivo.",Toast.LENGTH_SHORT).show();

                    tiempo = tiempoReal;
                    if (color) {
                        lblSensor.setBackgroundColor(Color.RED);
                    }
                    else {
                        lblSensor.setBackgroundColor(Color.GREEN);
                        color = !color;
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        manager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
}
