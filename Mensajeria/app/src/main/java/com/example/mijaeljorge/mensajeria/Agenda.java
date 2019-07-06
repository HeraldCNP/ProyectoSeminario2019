package com.example.mijaeljorge.mensajeria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Agenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
    }

    //atras
    public void cancelar (View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
}
