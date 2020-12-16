package de.hskl.gatav.flappybender.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.hskl.gatav.flappybender.R;
import de.hskl.gatav.flappybender.graphics.Asset;
import de.hskl.gatav.flappybender.graphics.AssetHandler;
import de.hskl.gatav.flappybender.logic.Game;
import de.hskl.gatav.flappybender.sound.Discman;

public class SkinActivity extends OwnActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        Button back = findViewById(R.id.BACK_SC);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.LOGO_PRE_MENU), PropertyValuesHolder.ofFloat("scaleX", 0.95f), PropertyValuesHolder.ofFloat("scaleY", 0.95f), PropertyValuesHolder.ofFloat("rotation", -2));
        animator.setDuration(500);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();

        ImageView ultimateBender =  findViewById(R.id.IMG_UB);
        ImageView basicBender = findViewById(R.id.IMG_BB);

        ObjectAnimator animatorBasicBender = ObjectAnimator.ofPropertyValuesHolder(basicBender, PropertyValuesHolder.ofFloat("scaleX", 1.2f), PropertyValuesHolder.ofFloat("scaleY", 1.2f), PropertyValuesHolder.ofFloat("alpha", 1f));
        animatorBasicBender.setDuration(1000);
        animatorBasicBender.setRepeatCount(ObjectAnimator.INFINITE);
        animatorBasicBender.setRepeatMode(ObjectAnimator.REVERSE);
        animatorBasicBender.start();

        ObjectAnimator animatorUltimateBender = ObjectAnimator.ofPropertyValuesHolder(ultimateBender, PropertyValuesHolder.ofFloat("scaleX", 1.2f), PropertyValuesHolder.ofFloat("scaleY", 1.2f), PropertyValuesHolder.ofFloat("alpha", 1f));
        animatorUltimateBender.setDuration(1000);
        animatorUltimateBender.setRepeatCount(ObjectAnimator.INFINITE);
        animatorUltimateBender.setRepeatMode(ObjectAnimator.REVERSE);
        animatorUltimateBender.start();

        TextView currentNameSkin = findViewById(R.id.ACT_CS);

        ultimateBender.setOnClickListener((v) -> chooseSkin(v, AssetHandler.getAsset(Asset.ASSET_BENDER_PROF)));
        basicBender.setOnClickListener((v) -> chooseSkin(v, AssetHandler.getAsset(Asset.ASSET_BENDER_STANDARD)));
        back.setOnClickListener(this::back);
    }

    @Override
    protected String getMusic() {
        return null;
    }

    private void back(View v){
        Intent intent = new Intent(SkinActivity.this, MenuActivity.class);
        startActivity(intent);

    }

    private  void chooseSkin(View v, Asset skin){
        Game.getInstance().setPlayerSkin(skin);
    }

    private void exit(View view) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        exit(null);
    }

}