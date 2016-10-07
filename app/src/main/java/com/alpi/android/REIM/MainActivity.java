package com.alpi.android.REIM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_bienvenida);
        imageView = (ImageView) findViewById(R.id.reim_logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_bienvenida);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
