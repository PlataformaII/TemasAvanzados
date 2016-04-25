package com.example.jiovanny.temasavanzados;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encriptacion extends AppCompatActivity {
    private EditText edtTxtEncriptar,edtTxtEncriptado,edtTxtDesencriptar;
    private Button btnEncriptar,btnDesencriptar;
    SecretKeySpec sks;
    byte[] datosEncriptados=null;
    byte[] datosDesencriptados=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encriptacion);
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

        edtTxtEncriptar=(EditText)findViewById(R.id.edtTxtEncriptar);
        edtTxtEncriptado=(EditText)findViewById(R.id.edtTxtEncriptado);
        edtTxtDesencriptar=(EditText)findViewById(R.id.edtTxtDesencriptado);
        btnEncriptar=(Button)findViewById(R.id.btnEncriptar);
        btnDesencriptar=(Button)findViewById(R.id.btnDesencriptar);

        //generar la llave
        try {
            SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
            //no se recomienda
            //sr.setSeed("Cualquier cosa sirve de semilla".getBytes());
            KeyGenerator kg=KeyGenerator.getInstance("AES");
            kg.init(128,sr);
            SecretKey sk=kg.generateKey();
            byte[]arreglo=sk.getEncoded();
            sks=new SecretKeySpec(arreglo,"AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public void eventoEncriptar(View view)  {
        if(view==btnEncriptar){
            try {
                Cipher cipher=Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE,sks);
                datosEncriptados=cipher.doFinal(edtTxtEncriptar.getText().toString().getBytes());
                edtTxtEncriptado.setText(Base64.encodeToString(datosEncriptados,Base64.DEFAULT));
            }catch (Exception e){
                Toast.makeText(this,"Error al encriptar datos.",Toast.LENGTH_SHORT).show();
            }/*
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }*/
        }else{
            try {
                Cipher cipher=Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE,sks);
                datosDesencriptados=cipher.doFinal(datosEncriptados);
                edtTxtDesencriptar.setText(new String(datosDesencriptados));
            }catch (Exception e){
                Toast.makeText(this,"Error al desencriptar los datos",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
