package com.example.mensajeri1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    //boton enviar agendar
    public void enviaragenda(View v){
        Intent siguiente = new Intent(this, Agendar.class);
        startActivity(siguiente);
    }
    //boton enciar calificacion
    public void enviarcalificacion (View v){
        Intent siguiente = new Intent(this, Calificacion.class);
        startActivity(siguiente);
    }

}
