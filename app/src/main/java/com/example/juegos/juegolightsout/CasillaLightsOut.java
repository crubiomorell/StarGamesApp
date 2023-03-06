package com.example.juegos.juegolightsout;

public class CasillaLightsOut {
    private boolean estaEncendida;
    private boolean esPista;

    public CasillaLightsOut(){
        this.estaEncendida = false;
        this.esPista = false;
    }

    public void cambiarEstado(){
        if(estaEncendida){
            setEncendida(false);
        } else {
            setEncendida(true);
        }
    }
    public void cambiarEstadoPista(){
        if(esPista){
            setPista(false);
        } else {
            setPista(true);
        }
    }
    public boolean estaEncendida() {
        return this.estaEncendida;
    }
    public boolean esPista() {
        return this.esPista;
    }
    public void setEncendida(boolean on) {
        this.estaEncendida = on;
    }

    public void setPista(boolean pista) {
        this.esPista = pista;
    }
}

