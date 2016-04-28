package com.dantann.sharedelement.demo.heroimage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dantann.sharedelement.R;
import com.dantann.sharedelement.framework.ThrottledOnClickListener;

public class HeroImageSecondActivity extends AppCompatActivity {

    private static final String HERO_TRANSITION_NAME = "HERO_TRANSITION_NAME";

    private ImageView heroView;
    private static Bitmap sHeroBitmap;

    public static void startActivityWithHeroAnimation(Activity activity, ImageView view) {
        final Intent intent = new Intent(activity, HeroImageSecondActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName(HERO_TRANSITION_NAME);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,view, HERO_TRANSITION_NAME);
            sHeroBitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
            activity.startActivity(intent,options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_top);

        heroView = (ImageView) findViewById(R.id.imageView);
        if (sHeroBitmap != null) {
            heroView.setImageBitmap(sHeroBitmap);
            sHeroBitmap = null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            heroView.setTransitionName(HERO_TRANSITION_NAME);
            heroView.setOnClickListener(new ThrottledOnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    super.onClick(v);
                    HeroImageSecondActivity.this.finishAfterTransition();
                }
            });
        }
    }
}