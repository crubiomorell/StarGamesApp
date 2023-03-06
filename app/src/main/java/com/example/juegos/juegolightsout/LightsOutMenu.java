package com.example.juegos.juegolightsout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juegos.R;

public class LightsOutMenu extends AppCompatActivity {
    MediaPlayer mediaPlayerBotones, mediaPlayerMusica;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightsoutmenu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button lightsout3x3 = findViewById(R.id.game3X3);
        Button lightsout4x4 = findViewById(R.id.game4X4);
        Button lightsout5x5 = findViewById(R.id.game5x5);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_set);
        lightsout3x3.startAnimation(animation);
        lightsout4x4.startAnimation(animation);
        lightsout5x5.startAnimation(animation);

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


        lightsout3x3.setOnClickListener(v -> {
            mediaPlayerBotones = MediaPlayer.create(v.getContext(), R.raw.boton);
            mediaPlayerBotones.start();
            Intent intent = new Intent(v.getContext(), LightsOut.class);
            Bundle bundle = new Bundle();
            bundle.putInt("nivelDificultad", 1);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
        });

        lightsout4x4.setOnClickListener(v -> {
            mediaPlayerBotones = MediaPlayer.create(v.getContext(), R.raw.boton);
            mediaPlayerBotones.start();
            Intent intent = new Intent(v.getContext(), LightsOut.class);
            Bundle bundle = new Bundle();
            bundle.putInt("nivelDificultad", 2);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
        });
        lightsout5x5.setOnClickListener(v -> {
            mediaPlayerBotones = MediaPlayer.create(v.getContext(), R.raw.boton);
            mediaPlayerBotones.start();
            Intent intent = new Intent(v.getContext(), LightsOut.class);
            Bundle bundle = new Bundle();
            bundle.putInt("nivelDificultad", 3);
            intent.putExtras(bundle);
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


