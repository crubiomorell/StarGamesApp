package com.example.juegos.juego2048;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.juegos.Menu;
import com.example.juegos.R;
import com.example.juegos.puntuaciones.DataBase;
import com.example.juegos.puntuaciones.PuntuacionActivity;

public class Juego2048 extends AppCompatActivity{
    ImageView[][] casillas;
    Controlador2048 controlador;
    Toast ganaste;
    Toast perdiste;
    ConstraintLayout layout;

    TextView puntuacion;

    DataBase baseDatos;

    private MediaPlayer mediaPlayerSwipe, mediaPlayerBotones, mediaPlayerGanar,mediaPlayerPerder,mediaPlayerSong;

    @SuppressLint({"ClickableViewAccessibility", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego2048);
        ganaste = Toast.makeText(this, "Has ganado!", Toast.LENGTH_SHORT);
        perdiste = Toast.makeText(this, "Has perdido!", Toast.LENGTH_SHORT);
        Button newGame = findViewById(R.id.newGame);
        puntuacion = findViewById(R.id.puntuacion);
        Button returnButton = findViewById(R.id.returnButton);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_set);
        returnButton.startAnimation(animation);
        newGame.startAnimation(animation);
        mediaPlayerSwipe = MediaPlayer.create(this, R.raw.swipe);
        mediaPlayerBotones = MediaPlayer.create(this, R.raw.boton);
        mediaPlayerGanar = MediaPlayer.create(this, R.raw.win);
        mediaPlayerPerder = MediaPlayer.create(this, R.raw.lose);
        mediaPlayerSong = MediaPlayer.create(this, R.raw.song3);
        mediaPlayerSong.setLooping(true);
        mediaPlayerSong.start();
        baseDatos = new DataBase(this);
        casillas = new ImageView[4][4];
        layout =  findViewById(R.id.l2048);
        casillas[0][0] =  findViewById(R.id.casilla00);
        casillas[0][1] =  findViewById(R.id.casilla01);
        casillas[0][2] =  findViewById(R.id.casilla02);
        casillas[0][3] =  findViewById(R.id.casilla03);
        casillas[1][0] =  findViewById(R.id.casilla10);
        casillas[1][1] =  findViewById(R.id.casilla11);
        casillas[1][2] =  findViewById(R.id.casilla12);
        casillas[1][3] =  findViewById(R.id.casilla13);
        casillas[2][0] =  findViewById(R.id.casilla20);
        casillas[2][1] =  findViewById(R.id.casilla21);
        casillas[2][2] =  findViewById(R.id.casilla22);
        casillas[2][3] =  findViewById(R.id.casilla23);
        casillas[3][0] =  findViewById(R.id.casilla30);
        casillas[3][1] =  findViewById(R.id.casilla31);
        casillas[3][2] =  findViewById(R.id.casilla32);
        casillas[3][3] =  findViewById(R.id.casilla33);
        controlador = new Controlador2048(casillas);
        reestablecerPuntuacion();
        layout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                realizarMovimiento("arriba");
            }

            public void onSwipeBottom() {
                realizarMovimiento("abajo");
            }

            public void onSwipeLeft() {
                realizarMovimiento("izquierda");
            }

            public void onSwipeRight() {
                realizarMovimiento("derecha");
            }
        });
    }


    private void realizarMovimiento(String direccion) {
        controlador.move(direccion);
        mediaPlayerSwipe.start();
        controlador.addRandomCell();
        controlador.updateView();
        String puntos =("  "+ controlador.getPuntuacion());
        puntuacion.setText(puntos);
        comprobarWinLose();
    }

    private void comprobarWinLose() {
        if (controlador.hasGanado()) {
            ganaste.show();
            mediaPlayerGanar.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.CustomDialogStyle));
            builder.setTitle("Has ganado! Escribe tu nombre para guardar tu puntuación:");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("Guardar puntuacion", (dialog, which) -> {
                String nombreJugador = input.getText().toString();
                if(nombreJugador.equals("")) {
                    nombreJugador = "Player";
                }
                baseDatos.newScore2048(nombreJugador, String.valueOf(controlador.getPuntuacion()));
                Intent intent = new Intent(getBaseContext(), PuntuacionActivity.class);
                intent.putExtra("GAME", "2048");
                controlador = new Controlador2048(casillas);
            });
            builder.show();


        }
        if (controlador.hasPerdido()) {
            mediaPlayerPerder.start();
            perdiste.show();

            String puntuaciondb = String.valueOf(controlador.getPuntuacion());
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.CustomDialogStyle));
            builder.setTitle("Has perdido... Escribe tu nombre para guardar tu puntuación:");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("Guardar puntuacion", (dialog, which) -> {
                String nombreJugador = input.getText().toString();
                if(nombreJugador.equals("")) {
                    nombreJugador = "Player";
                }
                baseDatos.newScore2048(nombreJugador, puntuaciondb);
                Intent intent = new Intent(getBaseContext(), PuntuacionActivity.class);
                intent.putExtra("GAME", "2048");
                controlador = new Controlador2048(casillas);
                reestablecerPuntuacion();
            });
            builder.show();



        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick (View v){
        switch(v.getId()) {
            case R.id.newGame:
                mediaPlayerBotones.start();
                controlador = new Controlador2048(casillas);
                break;
            case R.id.returnButton:
                mediaPlayerBotones.start();
                Intent intent = new Intent(this, Menu.class);
                startActivityForResult(intent, 0);
                break;
        }

    }

    private void reestablecerPuntuacion() {
            puntuacion.setText("  0");

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





