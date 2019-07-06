package com.example.herald.proyecto;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {
    ArrayList<String> categoria;

    //imagen
    GridLayoutManager lay;
    RecyclerView recycler;
    menuAdapter men;
    Button cargar,insert;
    ImageView img;
    private final int CODE_PERMISSIONS = 101;

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        //img

        /*recycler=findViewById(R.id.recyclerId);
        lay=new GridLayoutManager(this,2);
        recycler.setLayoutManager(lay);
        men=new menuAdapter(this);
        cargar();
        recycler.setAdapter(men);

        cargar=findViewById(R.id.cargarImagen);
        cargar.setOnClickListener((View.OnClickListener) this);
        insert=findViewById(R.id.insertarImagen);

        insert.setVisibility(View.INVISIBLE);
        if(reviewPermissions()){
            insert.setVisibility(View.VISIBLE);
        }
        insert.setOnClickListener((View.OnClickListener) this);

        img=findViewById(R.id.vistaImagenInsertar);*/

        //finimg

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

    //img
  /*  private void cargar() {
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(Utils.localhost+"image",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject dat=response.getJSONObject(i);
                        String url=dat.getString("url");
                        men.add(new item(url));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }*/



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
                    Toast.makeText(ProductosActivity.this, "Datos enviados", Toast.LENGTH_LONG).show();
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