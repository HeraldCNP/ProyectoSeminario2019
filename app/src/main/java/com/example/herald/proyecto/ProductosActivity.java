package com.example.herald.proyecto;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {
    ArrayList<String> categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        setSpinner();
        Button btn = findViewById(R.id.btnpr);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Guardando datos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
                sendData();
            }
        });

    }
    private void sendData() {
        EditText txtname = findViewById(R.id.name);
        EditText txtprice = findViewById(R.id.price);
        Spinner spcategory = findViewById(R.id.category);
        EditText txtdescription = findViewById(R.id.description);
        EditText txtstock = findViewById(R.id.stock);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("name", txtname.getText().toString());
        params.add("price", txtprice.getText().toString());
        params.add("category", categoria.get(spcategory.getSelectedItemPosition()));
        params.add("description", txtdescription.getText().toString());
        params.add("stock", txtstock.getText().toString());

        client.post(Utils.PRODUCTS_SERVICE, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(response.has("price")){
                    Toast.makeText(ProductosActivity.this, "Datos enviados", Toast.LENGTH_LONG);
                    Intent main = new Intent(ProductosActivity.this, MainActivity.class);
                    startActivity(main);
                }
            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

            }

            /*public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }*/

        });

    }
    private void setSpinner() {
        categoria = new ArrayList<>();
        categoria.add("Hogar");
        categoria.add("Entretenimiento");
        categoria.add("Comercio");
        categoria.add("Electronica");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoria);
        Spinner spinner = findViewById(R.id.category);
        spinner.setAdapter(adapter);
    }
}