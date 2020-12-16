package de.hskl.gatav.flappybender.listeners;

import android.widget.SeekBar;

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private Runnable actionOnChange;

    public SeekBarListener(Runnable executeOnChange) {
        actionOnChange = executeOnChange;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        actionOnChange.run();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        actionOnChange.run();
    }
}
