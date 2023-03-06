package com.example.juegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.juegos.juego2048.Juego2048;
import com.example.juegos.juegolightsout.LightsOutMenu;
import com.example.juegos.puntuaciones.PuntuacionActivity;

public class Menu extends AppCompatActivity {
    MediaPlayer mediaPlayerBotones, mediaPlayerMusica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button login2048 = findViewById(R.id.login2048);
        Button loginLightsOut = findViewById(R.id.loginLightsOut);
        Button loginPuntuaciones = findViewById(R.id.loginPuntuaciones);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_set);
        login2048.startAnimation(animation);
        loginLightsOut.startAnimation(animation);
        loginPuntuaciones.startAnimation(animation);
        ImageView imageView = findViewById(R.id.alienAnimation);
        imageView.setImageResource(R.drawable.alien);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animacion_arco);
        imageView.startAnimation(animacion);
        if(mediaPlayerMusica == null) {
            mediaPlayerMusica = MediaPlayer.create(this, R.raw.song);
            mediaPlayerMusica.setLooping(true);
            mediaPlayerMusica.start();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(animacion);
                handler.postDelayed(this, 10000);
            }
        }, 15000);


        login2048.setOnClickListener(v -> {
            mediaPlayerBotones = MediaPlayer.create(v.getContext(), R.raw.boton);
            mediaPlayerBotones.start();
            Intent intent = new Intent(v.getContext(), Juego2048.class);
            startActivityForResult(intent, 0);
        });
        loginLightsOut.setOnClickListener(v -> {
            mediaPlayerBotones = MediaPlayer.create(v.getContext(), R.raw.boton);
            mediaPlayerBotones.start();
            Intent intent = new Intent(v.getContext(), LightsOutMenu.class);
            startActivityForResult(intent, 0);


        });
        loginPuntuaciones.setOnClickListener(v -> {
            mediaPlayerBotones = MediaPlayer.create(v.getContext(), R.raw.boton);
            mediaPlayerBotones.start();
            Intent intent = new Intent(v.getContext(), PuntuacionActivity.class);
            startActivityForResult(intent, 0);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mediaPlayerMusica != null) {
            mediaPlayerMusica.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayerMusica != null) {
            mediaPlayerMusica.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayerMusica != null) {
            mediaPlayerMusica.stop();
            mediaPlayerMusica.release();
            mediaPlayerMusica = null;
        }
    }

}