package com.example.juegos.puntuaciones;

public class Puntuaciones2048 {
    private int id;
    private String nombreJugador;
    private String puntuacion;

    public Puntuaciones2048() {}

    public int getId() {
        return this.id;
    }

    public String getNombreJugador() {
        return this.nombreJugador;
    }

    public String getPuntuacion() {
        return this.puntuacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

}