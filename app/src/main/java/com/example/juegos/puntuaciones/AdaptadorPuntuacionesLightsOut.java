package com.example.juegos.puntuaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegos.R;


public class AdaptadorPuntuacionesLightsOut extends RecyclerView.Adapter<AdaptadorPuntuacionesLightsOut.ScoreViewHolder>{
    class ScoreViewHolder extends RecyclerView.ViewHolder {
        public final TextView nombreJugador;
        public final TextView puntuacion;
        public TextView dificultad;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            nombreJugador =  itemView.findViewById(R.id.name);
            puntuacion = itemView.findViewById(R.id.score);
            dificultad = itemView.findViewById(R.id.difficulty);
        }
    }

    private final LayoutInflater mInflater;
    DataBase baseDatos;
    Context context;
    String juego;

    public AdaptadorPuntuacionesLightsOut(Context context, DataBase baseDatos, String juego) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.juego = juego;
        this.baseDatos = baseDatos;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.formato_puntuaciones_lightsout, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        PuntuacionesLightsOut current = baseDatos.queryLightsOut(position);
        holder.nombreJugador.setText(current.getNombreJugador());
        holder.dificultad.setText(current.getDificultad());
        holder.puntuacion.setText(current.getPuntuacion());

        String dificultad = current.getDificultad();
        int color = 0;

        switch (dificultad){
            case "Fácil":
                color = R.color.verde;
                break;
            case "Normal":
                color = R.color.amarillo;
                break;
            case "Difícil":
                color = R.color.rojo;
                break;
        }

        holder.dificultad.setTextColor(ContextCompat.getColor(context, color));
    }


    @Override
    public int getItemCount() {
        return (int) baseDatos.count(juego);
    }
}

