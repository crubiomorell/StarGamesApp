package com.example.juegos.juego2048;

public class Tablero2048 {

        private Casilla2048[][] tableroJuego;

        private boolean partidaAcabada = false;

        private int puntuacion;

        public Tablero2048(int h, int w){
            this.crearTablero(h, w);
        }

        public Casilla2048 getPos(int i, int j) {return tableroJuego[i][j];}

        public void crearTablero(int h, int w){
            tableroJuego = new Casilla2048[h][w];
            for(int i = 0; i < tableroJuego.length; i++) {
                for(int j = 0; j < tableroJuego[i].length; j++) {
                    tableroJuego[i][j] = new Casilla2048();
                    tableroJuego[i][j].setValorCasilla(0);
                }
            }
        }

        public int getHeight(){return this.tableroJuego.length;}

        public int getWidth(){return this.tableroJuego[0].length;}

    public void arriba() {
        if (!partidaAcabada){
            for (int i = 1; i < tableroJuego.length; i++) {
                for (int j = 0; j < tableroJuego[i].length; j++) {
                    if (tableroJuego[i][j].getValorCasilla() != 0) {
                        for (int k = 0; k < i; k++) {
                            if (tableroJuego[k][j].getValorCasilla() == tableroJuego[i][j].getValorCasilla() && k == i - 1) {
                                tableroJuego[k][j].setValorCasilla(tableroJuego[i][j].getValorCasilla() * 2);
                                tableroJuego[i][j].setValorCasilla(0);
                                puntuacion += tableroJuego[k][j].getValorCasilla();
                                break;
                            } else if (tableroJuego[k][j].getValorCasilla() == 0) {
                                tableroJuego[k][j].setValorCasilla(tableroJuego[i][j].getValorCasilla());
                                tableroJuego[i][j].setValorCasilla(0);
                                for (int l = k-1; l >= 0; l--) {
                                    if (tableroJuego[l][j].getValorCasilla() == tableroJuego[k][j].getValorCasilla()) {
                                        tableroJuego[l][j].setValorCasilla(tableroJuego[k][j].getValorCasilla() * 2);
                                        tableroJuego[k][j].setValorCasilla(0);
                                        puntuacion += tableroJuego[l][j].getValorCasilla();
                                        break;
                                    } else if (tableroJuego[l][j].getValorCasilla() != 0) {
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    public void abajo() {
        if (!partidaAcabada){
            for (int i = tableroJuego.length - 2; i >= 0; i--) {
                for (int j = 0; j < tableroJuego[i].length; j++) {
                    if (tableroJuego[i][j].getValorCasilla() != 0) {
                        for (int k = tableroJuego.length - 1; k > i; k--) {
                            if (tableroJuego[k][j].getValorCasilla() == tableroJuego[i][j].getValorCasilla() && k == i + 1) {
                                tableroJuego[k][j].setValorCasilla(tableroJuego[i][j].getValorCasilla() * 2);
                                tableroJuego[i][j].setValorCasilla(0);
                                puntuacion += tableroJuego[k][j].getValorCasilla();
                                break;
                            } else if (tableroJuego[k][j].getValorCasilla() == 0) {
                                tableroJuego[k][j].setValorCasilla(tableroJuego[i][j].getValorCasilla());
                                tableroJuego[i][j].setValorCasilla(0);
                                for (int l = k+1; l < tableroJuego.length; l++) {
                                    if (tableroJuego[l][j].getValorCasilla() == tableroJuego[k][j].getValorCasilla()) {
                                        tableroJuego[l][j].setValorCasilla(tableroJuego[k][j].getValorCasilla() * 2);
                                        tableroJuego[k][j].setValorCasilla(0);
                                        puntuacion += tableroJuego[l][j].getValorCasilla();
                                        break;
                                    } else if (tableroJuego[l][j].getValorCasilla() != 0) {
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

   public void izquierda() {
       if (!partidaAcabada){
           for (int j = 1; j < tableroJuego[0].length; j++) {
               for (int i = 0; i < tableroJuego.length; i++) {
                   if (tableroJuego[i][j].getValorCasilla() != 0) {
                       for (int k = 0; k < j; k++) {
                           if (tableroJuego[i][k].getValorCasilla() == tableroJuego[i][j].getValorCasilla() && k == j - 1) {
                               tableroJuego[i][k].setValorCasilla(tableroJuego[i][j].getValorCasilla() * 2);
                               tableroJuego[i][j].setValorCasilla(0);
                                 puntuacion += tableroJuego[i][k].getValorCasilla();
                               break;
                           } else if (tableroJuego[i][k].getValorCasilla() == 0) {
                               tableroJuego[i][k].setValorCasilla(tableroJuego[i][j].getValorCasilla());
                               tableroJuego[i][j].setValorCasilla(0);
                               for (int l = k-1; l >= 0; l--) {
                                   if (tableroJuego[i][l].getValorCasilla() == tableroJuego[i][k].getValorCasilla()) {
                                       tableroJuego[i][l].setValorCasilla(tableroJuego[i][k].getValorCasilla() * 2);
                                       tableroJuego[i][k].setValorCasilla(0);
                                        puntuacion += tableroJuego[i][l].getValorCasilla();
                                       break;
                                   } else if (tableroJuego[i][l].getValorCasilla() != 0) {
                                       break;
                                   }
                               }
                               break;
                           }
                       }
                   }
               }
           }
       }
   }


    public void derecha() {
        if (!partidaAcabada){
            for (int i = 0; i < tableroJuego.length; i++) {
                for (int j = tableroJuego[i].length - 2; j >= 0; j--) {
                    if (tableroJuego[i][j].getValorCasilla() != 0) {
                        for (int k = tableroJuego[i].length - 1; k > j; k--) {
                            if (tableroJuego[i][k].getValorCasilla() == tableroJuego[i][j].getValorCasilla() && k == j + 1) {
                                tableroJuego[i][k].setValorCasilla(tableroJuego[i][j].getValorCasilla() * 2);
                                tableroJuego[i][j].setValorCasilla(0);
                                puntuacion += tableroJuego[i][k].getValorCasilla();
                                break;
                            } else if (tableroJuego[i][k].getValorCasilla() == 0) {
                                tableroJuego[i][k].setValorCasilla(tableroJuego[i][j].getValorCasilla());
                                tableroJuego[i][j].setValorCasilla(0);
                                for (int l = k + 1; l < tableroJuego[i].length; l++) {
                                    if (tableroJuego[i][l].getValorCasilla() == tableroJuego[i][k].getValorCasilla()) {
                                        tableroJuego[i][l].setValorCasilla(tableroJuego[i][k].getValorCasilla() * 2);
                                        tableroJuego[i][k].setValorCasilla(0);
                                        puntuacion += tableroJuego[i][l].getValorCasilla();
                                        break;
                                    } else if (tableroJuego[i][l].getValorCasilla() != 0) {
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    public void anadirCasilla(){
            int i = (int) (Math.random() * tableroJuego.length);
            int j = (int) (Math.random() * tableroJuego[0].length);
            if(tableroJuego[i][j].getValorCasilla() == 0){
                int numAleatorio = (int) (Math.random() * 2);
                switch(numAleatorio) {
                    case 0:
                        tableroJuego[i][j].setValorCasilla(2);
                        break;
                    case 1:
                        tableroJuego[i][j].setValorCasilla(4);
                        break;
                }
            }else if(perdiste()){
                return;
            }else{
                anadirCasilla();
            }
        }

        public boolean perdiste(){
            for (Casilla2048[] juego : tableroJuego) {
                for (Casilla2048 casilla2048 : juego) {
                    if (casilla2048.getValorCasilla() == 0) {
                        return false;
                    }
                }
            }
            partidaAcabada = true;
            return true;
        }

        public boolean ganaste(){
            for (Casilla2048[] juego : tableroJuego) {
                for (Casilla2048 casilla2048 : juego) {
                    if (casilla2048.getValorCasilla() == 2048) {
                        return true;
                    }
                }
            }
            return false;
        }

    public int getPuntuacion() {
        return puntuacion;
    }

}

