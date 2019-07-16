package com.example.mensajeri1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Agendar extends AppCompatActivity implements View.OnClickListener {
    Button bfecha,bhora;
    EditText tfecha,thora;
    private int dia,mes,ano,hora,minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        bfecha = (Button) findViewById(R.id.btnfecha);
        bhora = (Button) findViewById(R.id.btnhora);
        tfecha = (EditText) findViewById(R.id.efecha);
        thora = (EditText) findViewById(R.id.ehora);
        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);




    }
    //boton atras
    public void atras (View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
        Toast.makeText(this, "Operacion Cancelada", Toast.LENGTH_SHORT).show();
    }

    //boton enviar
    public void enviar (View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
        Toast.makeText(this, "Cita enviada al correo electronico", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        if(v==bfecha){
            final Calendar c = Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tfecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if(v==bhora){
            final Calendar c = Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minuto=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    thora.setText(hourOfDay+":"+minute);
                }
            },hora,minuto,false);
            timePickerDialog.show();
        }
    }
}
