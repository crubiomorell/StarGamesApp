package com.example.juegos.juego2048;

import android.view.View;
import android.widget.ImageView;

import com.example.juegos.R;

public class Controlador2048 {
    Tablero2048 tablero;
    ImageView[][] casillas;

    public Controlador2048(ImageView[][] imagenesCasillas) {
        casillas = imagenesCasillas;
        tablero = new Tablero2048(this.casillas.length, this.casillas[0].length);
        tablero.anadirCasilla();
        updateView();
    }

    public void move(String movement) {
        switch (movement) {
            case "arriba":
                tablero.arriba();
                break;
            case "abajo":
                tablero.abajo();
                break;
            case "izquierda":
                tablero.izquierda();
                break;
            case "derecha":
                tablero.derecha();
                break;
        }
    }

    public void updateView(){
        for (int i = 0; i < tablero.getHeight(); i++) {
            for (int j = 0; j < tablero.getWidth(); j++) {
                switch (tablero.getPos(i, j).getValorCasilla()) {
                    case 0:
                        casillas[i][j].setImageResource(R.drawable.empty);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        casillas[i][j].setImageResource(R.drawable.tile1);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        casillas[i][j].setImageResource(R.drawable.tile2);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        casillas[i][j].setImageResource(R.drawable.tile3);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 16:
                        casillas[i][j].setImageResource(R.drawable.tile4);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 32:
                        casillas[i][j].setImageResource(R.drawable.tile5);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 64:
                        casillas[i][j].setImageResource(R.drawable.tile6);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 128:
                        casillas[i][j].setImageResource(R.drawable.tile7);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 256:
                        casillas[i][j].setImageResource(R.drawable.tile8);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 512:
                        casillas[i][j].setImageResource(R.drawable.tile9);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 1024:
                        casillas[i][j].setImageResource(R.drawable.tile10);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 2048:
                        casillas[i][j].setImageResource(R.drawable.tile11);
                        casillas[i][j].setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
    }

    public boolean hasGanado() {return tablero.ganaste();}

    public boolean hasPerdido() {return tablero.perdiste();}

    public void addRandomCell() {
        tablero.anadirCasilla();}

    public int getPuntuacion() {return tablero.getPuntuacion();}

}
