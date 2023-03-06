package com.example.juegos.juegolightsout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.juegos.Menu;
import com.example.juegos.R;
import com.example.juegos.puntuaciones.DataBase;
import com.example.juegos.puntuaciones.PuntuacionActivity;

import java.util.Timer;
import java.util.TimerTask;


public class LightsOut extends AppCompatActivity {
    private ImageButton[][] botones;
    private  ControladorLightsOut controlador;
    private int nivelDificultad;
    private TextView tiempoJuego;
    private Timer contador;
    private TimerTask timerTask;
    private Double tiempoTranscurrido = 0.0;
    private DataBase baseDatos;
    private String dificultad;
    private MediaPlayer mediaPlayerBombillas, mediaPlayerBotones, mediaPlayerGanar,mediaPlayerSong;
    private boolean primerClick = true;
    private boolean wasPanelControl = false;
    private boolean partidaAcabada;
    private boolean haUsadoPistas = false;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            setNivelDificultad(bundle.getInt("nivelDificultad"));
        }
        Button newGame;
        Button returnButton;
        Button pistasButton;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_set);
        // Inicializa el objeto MediaPlayer con el archivo de sonido.
        mediaPlayerBombillas = MediaPlayer.create(this, R.raw.click);
        mediaPlayerBotones = MediaPlayer.create(this, R.raw.boton);
        mediaPlayerGanar = MediaPlayer.create(this, R.raw.win);
        mediaPlayerSong = MediaPlayer.create(this, R.raw.song3);
        switch (nivelDificultad) {
            case 1:
                setContentView(R.layout.lightsout3x3);
                newGame = findViewById(R.id.newGame);
                returnButton = findViewById(R.id.returnButton);
                pistasButton = findViewById(R.id.pistasButton);
                returnButton.startAnimation(animation);
                newGame.startAnimation(animation);
                pistasButton.startAnimation(animation);
                dificultad= "Fácil";
                baseDatos = new DataBase(this);
                tiempoJuego = findViewById(R.id.timer);
                contador = new Timer();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                botones = new ImageButton[3][3];
                botones[0][0] = findViewById(R.id.imgBttn00);
                botones[0][1] = findViewById(R.id.imgBttn01);
                botones[0][2] = findViewById(R.id.imgBttn02);
                botones[1][0] = findViewById(R.id.imgBttn10);
                botones[1][1] = findViewById(R.id.imgBttn11);
                botones[1][2] = findViewById(R.id.imgBttn12);
                botones[2][0] = findViewById(R.id.imgBttn20);
                botones[2][1] = findViewById(R.id.imgBttn21);
                botones[2][2] = findViewById(R.id.imgBttn22);
                controlador = new ControladorLightsOut(botones, this);
                mediaPlayerSong.setLooping(true);
                mediaPlayerSong.start();
                break;
            case 2:
                setContentView(R.layout.lightsout4x4);
                newGame = findViewById(R.id.newGame);
                returnButton = findViewById(R.id.returnButton);
                pistasButton = findViewById(R.id.pistasButton);
                returnButton.startAnimation(animation);
                newGame.startAnimation(animation);
                pistasButton.startAnimation(animation);
                dificultad= "Normal";
                baseDatos = new DataBase(this);
                tiempoJuego = findViewById(R.id.timer);
                contador = new Timer();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                botones = new ImageButton[4][4];
                botones[0][0] = findViewById(R.id.imgBttn00);
                botones[0][1] = findViewById(R.id.imgBttn01);
                botones[0][2] = findViewById(R.id.imgBttn02);
                botones[0][3] = findViewById(R.id.imgBttn03);
                botones[1][0] = findViewById(R.id.imgBttn10);
                botones[1][1] = findViewById(R.id.imgBttn11);
                botones[1][2] = findViewById(R.id.imgBttn12);
                botones[1][3] = findViewById(R.id.imgBttn13);
                botones[2][0] = findViewById(R.id.imgBttn20);
                botones[2][1] = findViewById(R.id.imgBttn21);
                botones[2][2] = findViewById(R.id.imgBttn22);
                botones[2][3] = findViewById(R.id.imgBttn23);
                botones[3][0] = findViewById(R.id.imgBttn30);
                botones[3][1] = findViewById(R.id.imgBttn31);
                botones[3][2] = findViewById(R.id.imgBttn32);
                botones[3][3] = findViewById(R.id.imgBttn33);
                controlador = new ControladorLightsOut(botones, this);
                mediaPlayerSong.setLooping(true);
                mediaPlayerSong.start();
                break;
            case 3:
                setContentView(R.layout.lightsout5x5);
                newGame = findViewById(R.id.newGame);
                returnButton = findViewById(R.id.returnButton);
                pistasButton = findViewById(R.id.pistasButton);
                returnButton.startAnimation(animation);
                newGame.startAnimation(animation);
                pistasButton.startAnimation(animation);
                dificultad= "Difícil";
                baseDatos = new DataBase(this);
                tiempoJuego = findViewById(R.id.timer);
                contador = new Timer();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                botones = new ImageButton[5][5];
                botones[0][0] = findViewById(R.id.imgBttn00);
                botones[0][1] = findViewById(R.id.imgBttn01);
                botones[0][2] = findViewById(R.id.imgBttn02);
                botones[0][3] = findViewById(R.id.imgBttn03);
                botones[0][4] = findViewById(R.id.imgBttn04);
                botones[1][0] = findViewById(R.id.imgBttn10);
                botones[1][1] = findViewById(R.id.imgBttn11);
                botones[1][2] = findViewById(R.id.imgBttn12);
                botones[1][3] = findViewById(R.id.imgBttn13);
                botones[1][4] = findViewById(R.id.imgBttn14);
                botones[2][0] = findViewById(R.id.imgBttn20);
                botones[2][1] = findViewById(R.id.imgBttn21);
                botones[2][2] = findViewById(R.id.imgBttn22);
                botones[2][3] = findViewById(R.id.imgBttn23);
                botones[2][4] = findViewById(R.id.imgBttn24);
                botones[3][0] = findViewById(R.id.imgBttn30);
                botones[3][1] = findViewById(R.id.imgBttn31);
                botones[3][2] = findViewById(R.id.imgBttn32);
                botones[3][3] = findViewById(R.id.imgBttn33);
                botones[3][4] = findViewById(R.id.imgBttn34);
                botones[4][0] = findViewById(R.id.imgBttn40);
                botones[4][1] = findViewById(R.id.imgBttn41);
                botones[4][2] = findViewById(R.id.imgBttn42);
                botones[4][3] = findViewById(R.id.imgBttn43);
                botones[4][4] = findViewById(R.id.imgBttn44);
                controlador = new ControladorLightsOut(botones, this);
                mediaPlayerSong.setLooping(true);
                mediaPlayerSong.start();
                break;
        }

    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch(getNivelDificultad()){
            case 1:
                switch(v.getId()) {
                    case R.id.imgBttn00:
                        controlador.click(0,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn01:
                        controlador.click(0,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn02:
                        controlador.click(0,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn10:
                        controlador.click(1,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn11:
                        controlador.click(1,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn12:
                        controlador.click(1,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn20:
                        controlador.click(2,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn21:
                        controlador.click(2,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn22:
                        controlador.click(2,2);
                        reproducirSonido();
                        break;
                    case R.id.newGame:
                        nuevaPartida();
                        wasPanelControl = true;
                        reestablecerTiempo();
                        break;
                    case R.id.returnButton:
                        mediaPlayerBotones.start();
                        wasPanelControl = true;
                        Intent intent = new Intent(v.getContext(), Menu.class);
                        startActivityForResult(intent, 0);
                        break;
                    case R.id.pistasButton:
                        controlador.mostrarPistas();
                        haUsadoPistas = true;
                        break;
        }
        break;
            case 2:
                switch(v.getId()) {
                    case R.id.imgBttn00:
                        controlador.click(0,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn01:
                        controlador.click(0,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn02:
                        controlador.click(0,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn03:
                        controlador.click(0,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn10:
                        controlador.click(1,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn11:
                        controlador.click(1,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn12:
                        controlador.click(1,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn13:
                        controlador.click(1,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn20:
                        controlador.click(2,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn21:
                        controlador.click(2,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn22:
                        controlador.click(2,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn23:
                        controlador.click(2,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn30:
                        controlador.click(3,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn31:
                        controlador.click(3,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn32:
                        controlador.click(3,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn33:
                        controlador.click(3,3);
                        reproducirSonido();
                        break;
                    case R.id.newGame:
                        nuevaPartida();
                        wasPanelControl = true;
                        reestablecerTiempo();
                        break;
                    case R.id.returnButton:
                        mediaPlayerBotones.start();
                        wasPanelControl = true;
                        Intent intent = new Intent(v.getContext(), Menu.class);
                        startActivityForResult(intent, 0);
                        break;
                    case R.id.pistasButton:
                        controlador.mostrarPistas();
                        haUsadoPistas = true;
                        break;
                }
                break;
            case 3:
                switch(v.getId()) {
                    case R.id.imgBttn00:
                        controlador.click(0,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn01:
                        controlador.click(0,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn02:
                        controlador.click(0,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn03:
                        controlador.click(0,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn04:
                        controlador.click(0,4);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn10:
                        controlador.click(1,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn11:
                        controlador.click(1,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn12:
                        controlador.click(1,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn13:
                        controlador.click(1,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn14:
                        controlador.click(1,4);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn20:
                        controlador.click(2,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn21:
                        controlador.click(2,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn22:
                        controlador.click(2,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn23:
                        controlador.click(2,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn24:
                        controlador.click(2,4);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn30:
                        controlador.click(3,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn31:
                        controlador.click(3,1);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn32:
                        controlador.click(3,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn33:
                        controlador.click(3,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn34:
                        controlador.click(3,4);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn40:
                        controlador.click(4,0);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn41:
                        controlador.click(4,1);
                        break;
                    case R.id.imgBttn42:
                        controlador.click(4,2);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn43:
                        controlador.click(4,3);
                        reproducirSonido();
                        break;
                    case R.id.imgBttn44:
                        controlador.click(4,4);
                        reproducirSonido();
                        break;
                    case R.id.newGame:
                        nuevaPartida();
                        wasPanelControl = true;
                        reestablecerTiempo();
                        break;
                    case R.id.returnButton:
                        mediaPlayerBotones.start();
                        wasPanelControl = true;
                        Intent intent = new Intent(v.getContext(), Menu.class);
                        startActivityForResult(intent, 0);
                        break;
                    case R.id.pistasButton:
                        controlador.mostrarPistas();
                        haUsadoPistas = true;
                        break;
                }
                break;
        }
        if(primerClick){
            if(wasPanelControl){
                wasPanelControl = false;
            }else{
            iniciarTemporizador();
            primerClick = false;
            wasPanelControl = false;
            }
        }
        if (controlador.ganar() && !partidaAcabada) {
            mediaPlayerGanar.start();
            timerTask.cancel();
            partidaAcabada = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¡Has resuelto el puzzle! Escribe tu nombre para el ranking:");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("Guardar puntuación", (dialog, which) -> {
                String nombreJugador = input.getText().toString();
                if(nombreJugador.equals("")) {
                    nombreJugador = "Player";
                }
                if(haUsadoPistas){
                    nombreJugador = nombreJugador + " (Pistas)";
                }
                baseDatos.newScoreLightsOut(nombreJugador, getTiempo(), dificultad);
                Intent intent = new Intent(getBaseContext(), PuntuacionActivity.class);
                intent.putExtra("GAME", "LightsOut");
            });
            builder.show();
        }
    }

    private void nuevaPartida(){
        mediaPlayerBotones.start();
        controlador = new ControladorLightsOut(botones,this);
        while (controlador.ganar()){
            controlador = new ControladorLightsOut(botones,this);
        }
        reestablecerTiempo();
        partidaAcabada = false;
        primerClick = true;
        wasPanelControl = true; //Evitamos que se inicie el temporizador antes de que pulse una bombilla
    }

    private void iniciarTemporizador() {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(() -> {
                    tiempoTranscurrido++;
                    tiempoJuego.setText(getTiempo());
                });
            }
        };
        contador.scheduleAtFixedRate(timerTask, 0 ,1000);
    }

    private String getTiempo() {
        int seconds = (int) (tiempoTranscurrido % 60);
        int minutes = (int) (tiempoTranscurrido / 60) % 60;
        int hours = (int) (tiempoTranscurrido / 3600);

        return String.format(" %02d : %02d : %02d", hours, minutes, seconds);
    }

    private void reestablecerTiempo() {
        if(timerTask != null) {
            timerTask.cancel();
            tiempoTranscurrido = 0.0;
            String tiempoInicial = " 00 : 00 : 00";
            tiempoJuego.setText(tiempoInicial);
        }
    }

    public void reproducirSonido() {
        if(!partidaAcabada) {
            mediaPlayerBombillas.start(); // Reproduce el sonido.
        }
}

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public boolean isPartidaAcabada() {
        return partidaAcabada;
    }

    protected void onResume() {
        super.onResume();
        if(mediaPlayerSong != null) {
            mediaPlayerSong.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayerSong != null) {
            mediaPlayerSong.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayerSong != null) {
            mediaPlayerSong.stop();
            mediaPlayerSong.release();
            mediaPlayerSong = null;
        }
    }
}


