package com.example.herald.proyecto;

import cz.msebera.android.httpclient.Header;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.*;


import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;

import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductosActivity extends AppCompatActivity implements OnMapReadyCallback{
    ArrayList<String> categoria;
    private GoogleMap mMap;
    private MapView map;
    Geocoder geocoder;
    private TextView street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

    setContentView(R.layout.activity_productos);

        map = findViewById(R.id.mapView);
        map.onCreate(savedInstanceState);
        map.onResume();
        MapsInitializer.initialize(this);
        map.getMapAsync(this);
        geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        street = findViewById(R.id.location);

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
                    Toast.makeText(ProductosActivity.this, "Datos enviados", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(ProductosActivity.this, AddImagenActivity.class);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-19.571134, -65.7551786);
        mMap.addMarker(new MarkerOptions().position(sydney).title("You are Here").zIndex(21).draggable(true));
        mMap.setMinZoomPreference(10);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }
}