package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.listeners.SeekBarListener;
import de.hskl.gatav.flappybender.sound.Discman;

public class OptionsActivity extends OwnActivity {

    private SeekBar volumeSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button btn = findViewById(R.id.BUTTON_BACK_OPTIONS);
        btn.setOnClickListener(this::back);
        volumeSeekbar = findViewById(R.id.SEEKBAR_VOLUME);
        if(Discman.wasCreated()) {
            volumeSeekbar.setProgress(Discman.getInstance().getVolume());
        }
        volumeSeekbar.setOnSeekBarChangeListener(new SeekBarListener(this::changedVolume));
    }

    @Override
    protected String getMusic() {
        return null;
    }

    private void changedVolume() {
        Discman.getInstance().setVolume(volumeSeekbar.getProgress());
    }

    private void back(View view) {
        finish();
    }
}