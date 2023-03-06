package com.example.juegos.juegolightsout;

import java.util.ArrayList;
import java.util.Random;

public class TableroLightsOut {
    private CasillaLightsOut[][] tablero;
    private ArrayList<int[]> coordenadasValoresAlterados = new ArrayList<>();
    public TableroLightsOut(int h, int w){
        this.crearTablero(h, w);
    }

    public void crearTablero(int h, int w){
        tablero = new CasillaLightsOut[h][w];
        for(int i = 0; i < tablero.length; i++) {
            for(int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new CasillaLightsOut();
                tablero[i][j].setEncendida(false);
            }
        }
    }

    public boolean ganar(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j].estaEncendida()) {
                    return false;
                }
            }
        }
        return true;
    }
    public void click(int x, int y){
        if (x >= tablero.length || y >= tablero[0].length || x < 0 || y <0) {
            throw new IllegalArgumentException();
        }
        int[] coordenadas = new int[2];
        coordenadas[0] = x;
        coordenadas[1] = y;
        coordenadasValoresAlterados.add(coordenadas);
        tablero[x][y].cambiarEstado();
        if (x - 1 >= 0) {
            tablero[x - 1][y].cambiarEstado();
        }
        if (x + 1 < tablero.length) {
            tablero[x + 1][y].cambiarEstado();
        }
        if (y - 1 >= 0) {
            tablero[x][y - 1].cambiarEstado();
        }
        if (y + 1 < tablero[0].length) {
            tablero[x][y + 1].cambiarEstado();
        }
    }

    public void lucesAleatorias(){
        Random random = new Random();
        int x1 = random.nextInt(tablero.length);
        int y1 = random.nextInt(tablero[0].length);
        int x2 = random.nextInt(tablero.length);
        int y2 = random.nextInt(tablero[0].length);
        click(x1, y1);
        click(x2, y2);
        do{for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[i].length; j++) {
                double porEncendidas = 0.55;
                if (Math.random() < porEncendidas) {
                    click(i,j);
                }
            }
        }}
        while(!esTableroResoluble());
    }

    public CasillaLightsOut getPos(int i, int j) {return tablero[i][j];}

    public int getAlt(){return this.tablero.length;}

    public int getEstado(int i, int j) {
        if (tablero[i][j].estaEncendida()){
            return 1;
        } else {
            return 0;
        }
    }

    public boolean esTableroResoluble() {
        int[][] matriz = new int[getAlt()][getAlt()];
        for(int i = 0; i < getAlt(); i++){
            for(int j = 0; j < getAlt(); j++){
                if(tablero[i][j].estaEncendida()){
                    matriz[i][j] = 1;
                } else {
                    matriz[i][j] = 0;
                }
            }
        }

        // Calcular el número de inversiones
        int inversiones = 0;
        for(int i = 0; i < getAlt(); i++){
            for(int j = 0; j < getAlt(); j++){
                for(int k = i; k < getAlt(); k++){
                    int l = 0;
                    if(k == i){
                        l = j + 1;
                    }
                    for(; l < getAlt(); l++){
                        if(matriz[i][j] > matriz[k][l] && matriz[k][l] != 0){
                            inversiones++;
                        }
                    }
                }
            }
        }

        // Verificar la paridad de las filas y columnas
        int filasImpares = 0;
        int columnasImpares = 0;
        for(int i = 0; i < getAlt(); i++){
            int sumFilas = 0;
            int sumColumnas = 0;
            for(int j = 0; j < getAlt(); j++){
                sumFilas += matriz[i][j];
                sumColumnas += matriz[j][i];
            }
            if(sumFilas % 2 != 0){
                filasImpares++;
            }
            if(sumColumnas % 2 != 0){
                columnasImpares++;
            }
        }

        // Si el número de inversiones es par y las filas y columnas tienen paridad, el tablero es resoluble
        if(inversiones % 2 == 0 && filasImpares % 2 == 0 && columnasImpares % 2 == 0){
            return true;
        } else {
            return false;
        }
    }

    public void actualizarPistas(){
        for (int i = 0; i < coordenadasValoresAlterados.size(); i++) {
            int[] coordenadas = coordenadasValoresAlterados.get(i);
            int x = coordenadas[0];
            int y = coordenadas[1];
            tablero[x][y].cambiarEstadoPista();
        }
    }
    public void dejarDeMostrarPistas(){
        for (CasillaLightsOut[] casillaLightsOuts : tablero) {
            for (CasillaLightsOut casillaLightsOut : casillaLightsOuts) {
                casillaLightsOut.setPista(false);
            }
        }
        }
    }




