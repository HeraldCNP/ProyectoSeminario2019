package com.example.mensajeri1;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Calificacion extends AppCompatActivity {

    ConstraintLayout rating;
    CheckBox star;

    static int calf=0;
    static String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);

        rating = (ConstraintLayout) findViewById(R.id.ratings);
        for (int i=1;i<=5;i++){
            star = (CheckBox)rating.findViewWithTag(String.valueOf(i));
            star.setOnClickListener(starsListener);
        }

        Bundle b=getIntent().getExtras();
        //id= (String) b.get("idcomprador");
        id="5d2e61d746958e003d65303a";


    }

    //anterio
    //boton atras
    public void atrasc (View view){
        Intent atrasc = new Intent(this, MainActivity.class);
        startActivity(atrasc);
        Toast.makeText(this, "Operacion Cancelada", Toast.LENGTH_SHORT).show();
    }
    //botonenviar
    public void enviarc (View view){
        Intent atrasc = new Intent(this, MainActivity.class);
        cargar();
        startActivity(atrasc);

    }

    private void cargar() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams p=new RequestParams();
        p.put("comprador",id);
        p.put("calif",calf);
        client.patch("http://192.168.0.18:8000/calificacion/"+id,p,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String res=response.getString("message");
                    Toast.makeText(getApplicationContext(), "Califiacion Enviada", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), ""+res, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private View.OnClickListener starsListener = new View.OnClickListener(){
        public void onClick(View view){
            int tag = Integer.valueOf((String)view.getTag());

            calf=tag;

            for(int i=1;i<tag;i++){
                star = (CheckBox) rating.findViewWithTag(String.valueOf(i));
                star.setChecked(true);
            }
            for(int i=tag+1;i<=5;i++){
                star=(CheckBox)rating.findViewWithTag(String.valueOf(i));
                star.setChecked(false);
            }

        }
    };
}
