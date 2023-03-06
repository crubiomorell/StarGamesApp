package com.example.juegos.juegolightsout;

import android.view.View;
import android.widget.ImageButton;

import com.example.juegos.R;

public class ControladorLightsOut {
    private TableroLightsOut tableroLightsOut;
    private ImageButton[][] bombillas;
    private LightsOut game;

    private boolean mostrandoPistas = false;

    public ControladorLightsOut(ImageButton[][] bombillas, LightsOut game) {
        this.game = game;
        this.bombillas = bombillas;
        this.tableroLightsOut = new TableroLightsOut(this.bombillas.length, this.bombillas[0].length);
        tableroLightsOut.lucesAleatorias();
        updateView();
    }

    public void click(int i, int j) {
        if (!game.isPartidaAcabada()) {
            tableroLightsOut.click(i, j);
            if(mostrandoPistas){
            if(tableroLightsOut.getPos(i,j).esPista()){
                tableroLightsOut.getPos(i,j).setPista(false);
            }else{
                tableroLightsOut.getPos(i,j).setPista(true);
            }
            }
            updateView();

        }
    }

    public void mostrarPistas() {
        if (mostrandoPistas) {
            tableroLightsOut.dejarDeMostrarPistas();
            mostrandoPistas = false;
            updateView();
        } else {
            mostrandoPistas = true;
            if (!game.isPartidaAcabada()) {
                tableroLightsOut.actualizarPistas();
                updateView();
            }
        }
    }

    public void updateView() {
        for (int i = 0; i < bombillas.length; i++) {
            for (int j = 0; j < bombillas[i].length; j++) {
                if (tableroLightsOut.getPos(i, j).estaEncendida()) {
                    bombillas[i][j].setImageResource(R.drawable.lighton);
                    bombillas[i][j].setVisibility(View.VISIBLE);
                } else if (!tableroLightsOut.getPos(i, j).estaEncendida()) {
                    bombillas[i][j].setImageResource(R.drawable.lightoff);
                    bombillas[i][j].setVisibility(View.VISIBLE);
                }
                if (mostrandoPistas) {
                    if (tableroLightsOut.getPos(i, j).esPista()) {
                        if (tableroLightsOut.getPos(i, j).estaEncendida()) {
                            bombillas[i][j].setImageResource(R.drawable.lightonpista);
                            bombillas[i][j].setVisibility(View.VISIBLE);
                        } else if (!tableroLightsOut.getPos(i, j).estaEncendida()) {
                            bombillas[i][j].setImageResource(R.drawable.lightoffpista);
                            bombillas[i][j].setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    }

    public boolean ganar() {
        return this.tableroLightsOut.ganar();
    }

}