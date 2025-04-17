package com.example.musicalbumui;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AudioImage extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.audio_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sub), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent it = getIntent();
        String tag = it.getStringExtra("it_tag");

        TextView title = findViewById(R.id.title);
        TextView lyrics = findViewById(R.id.lyrics);
        ImageView song_image = findViewById(R.id.song_image);

        Resources res = getResources();
        int titleId = res.getIdentifier("title"+tag, "string", getPackageName());
        String strTitle = res.getString(titleId);
        title.setText(strTitle);

        int lyricsId = res.getIdentifier("lyrics"+tag, "string", getPackageName());
        lyrics.setText(lyricsId);

        int imageFileNameId = res.getIdentifier("song_image"+tag, "string", getPackageName());
        String imageFileName = res.getString(imageFileNameId);
        int imageId = res.getIdentifier(imageFileName, "drawable", getPackageName());
        song_image.setImageResource(imageId);

        int audioFileNameId = res.getIdentifier("audio"+tag, "string",getPackageName());
        String audioFileName = res.getString(audioFileNameId);
        int audioId = res.getIdentifier(audioFileName, "raw", getPackageName());

        mp = MediaPlayer.create(this, audioId);
        mp.setLooping(true);
        mp.start();

    }

    public void goBack(View view) {
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        finish();
    }
}
