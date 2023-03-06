package com.example.juegos.puntuaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.juegos.R;


public class AdaptadorPuntuaciones2048 extends RecyclerView.Adapter<AdaptadorPuntuaciones2048.ScoreViewHolder>{
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

    public AdaptadorPuntuaciones2048(Context context, DataBase baseDatos, String juego) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.juego = juego;
        this.baseDatos = baseDatos;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.formato_puntuaciones_2048, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        Puntuaciones2048 current = baseDatos.query2048(position);
        holder.nombreJugador.setText(current.getNombreJugador());
        holder.puntuacion.setText(current.getPuntuacion());
    }

    @Override
    public int getItemCount() {
        return (int) baseDatos.count(juego);
    }
}

