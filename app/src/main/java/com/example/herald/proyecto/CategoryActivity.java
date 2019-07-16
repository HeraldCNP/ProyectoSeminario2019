package com.example.herald.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void Seleccion(View view){

        switch (view.getId()){
            case R.id.Cat_Musica:
                Toast.makeText(CategoryActivity.this, "Categoria Musica", Toast.LENGTH_LONG).show();
                break;

            case R.id.Cat_Deportes:
                Toast.makeText(CategoryActivity.this, "Categoria Deportes", Toast.LENGTH_LONG).show();
                break;

        }

    }
}
