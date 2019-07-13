package com.example.herald.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLog = findViewById(R.id.btn_login);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Iniciando Sesion", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                sendLogin();
            }



        });
    }

    private void sendLogin() {
        final EditText email = findViewById(R.id.txt_email);
        EditText password = findViewById(R.id.txt_password);
//        final TextView welcome = findViewById(R.id.welcome);
//        welcome.setVisibility(View.GONE);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("email", email.getText().toString());
        params.add("password", password.getText().toString());
        client.post(Utils.LOGIN_SERVICE, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(response.has("token")){
                    try {
                        Utils.TOKEN = response.getString("token");
                        Toast.makeText(LoginActivity.this, "Sesion Iniciada", Toast.LENGTH_SHORT).show();
//                        welcome.setVisibility(View.VISIBLE);
//                        welcome.setText("Bienvenido" + email.getText());
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(main);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.btn_register:{
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
            }
//            case R.id.btn_login:{
//                Intent login = new Intent(this, ProductosActivity.class);
//                startActivity(login);
//            }
        }
    }

}
