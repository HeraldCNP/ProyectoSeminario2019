package com.example.herald.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.btn_register:{
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
            }
        }
    }

}
