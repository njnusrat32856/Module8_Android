package com.nusrat.childcare;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sleep extends AppCompatActivity {

    private MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sleep);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mySong = MediaPlayer.create(this, R.raw.sleep_music);
    }
    public void playIT(View v) {
        if (mySong != null) {
            mySong.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Release the MediaPlayer resources when the activity is paused
        if (mySong != null) {
            mySong.release();
            mySong = null;  // Set to null to avoid accessing released MediaPlayer
        }
    }

    // Method to pause the music when invoked
    public void stopIT(View view) {
        if (mySong != null && mySong.isPlaying()) {
            mySong.pause();
        }
    }

}