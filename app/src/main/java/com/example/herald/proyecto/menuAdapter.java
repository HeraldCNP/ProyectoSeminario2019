package com.example.herald.proyecto;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class menuAdapter extends RecyclerView.Adapter<menuAdapter.Datos> {
    ArrayList<item> list;
    Context context;
    menuAdapter(Context c){
        list=new ArrayList<>();
        context=c;
    }
    public void add(item i){
        list.add(i);
        notifyItemInserted(list.indexOf(i));
    }
    @NonNull
    @Override
    public Datos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.imagen,viewGroup,false);
        return new Datos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Datos datos, int i) {
        item it=list.get(i);
        datos.txt.setText(it.getUrl());
        Glide.with(context).load(Utils.localhost+"uploads/"+it.getUrl()).into(datos.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Datos extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        public Datos(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.miImagen);
            txt=itemView.findViewById(R.id.textoCualquiera);
        }
    }
}
