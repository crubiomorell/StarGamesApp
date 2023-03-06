package com.example.juegos.puntuaciones;

public class PuntuacionesLightsOut {
    private int id;
    private String nombreJugador;
    private String puntuacion;

    private String dificultad;

    public PuntuacionesLightsOut() {}

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

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}