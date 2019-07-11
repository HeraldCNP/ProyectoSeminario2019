package com.example.herald.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {
    ArrayList<String> sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setSpinnerRegister();
        Button btnReg = findViewById(R.id.btn_reg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Registrando espere por favor", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                sendRegister();
            }


        });
    }
    private void sendRegister() {
        EditText names = findViewById(R.id.name);
        EditText emails = findViewById(R.id.email);
        EditText passwords = findViewById(R.id.password);
        EditText fonos = findViewById(R.id.fono);
        Spinner sexs = findViewById(R.id.sex);


        AsyncHttpClient user = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("name", names.getText().toString());
        params.add("email", emails.getText().toString());
        params.add("password", passwords.getText().toString());
        params.add("fono", fonos.getText().toString());
        params.add("sex", sexo.get(sexs.getSelectedItemPosition()));

        user.post(Utils.USER_SERVICE, params,  new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(response.has("name")){
                    Toast.makeText(RegisterActivity.this, "Usuario Registrado con exito", Toast.LENGTH_LONG).show();
                    Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(login);
                }
            }


            /*public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

            }*/

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });

    }

    private void setSpinnerRegister() {
        sexo = new ArrayList<>();
        sexo.add("Hombre");
        sexo.add("Mujer");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sexo);
        Spinner spinner = findViewById(R.id.sex);
        spinner.setAdapter(adapter);
//        spinner.getSelectedItemPosition();
    }
}
