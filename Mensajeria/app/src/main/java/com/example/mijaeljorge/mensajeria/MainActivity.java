package com.example.mijaeljorge.mensajeria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //BOTON AGENDA
    public void agenta(View view){
        Intent siguiente = new Intent(this, Agenda.class);
        startActivity(siguiente);
    }
}
