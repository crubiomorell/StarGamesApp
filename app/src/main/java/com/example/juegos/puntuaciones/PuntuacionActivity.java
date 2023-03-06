
package com.example.juegos.puntuaciones;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.juegos.R;

public class PuntuacionActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayerSong;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuaciones_layout);
        mediaPlayerSong = MediaPlayer.create(this, R.raw.song);
        mediaPlayerSong.setLooping(true);
        mediaPlayerSong.start();
        DataBase mDB = new DataBase(this);

        String lightsout = "LightsOut";
        String j2048 = "2048";

        RecyclerView rcV = findViewById(R.id.recyclerview);
        RecyclerView rcV2 = findViewById(R.id.recyclerview2);

        AdaptadorPuntuacionesLightsOut adapter = new AdaptadorPuntuacionesLightsOut(this, mDB, lightsout);
        AdaptadorPuntuaciones2048 adapter2 = new AdaptadorPuntuaciones2048(this, mDB, j2048);

        rcV.setAdapter(adapter);
        rcV2.setAdapter(adapter2);

        rcV.setLayoutManager(new LinearLayoutManager(this));
        rcV2.setLayoutManager(new LinearLayoutManager(this));
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

