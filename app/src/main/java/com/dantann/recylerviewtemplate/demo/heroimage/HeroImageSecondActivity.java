package com.dantann.recylerviewtemplate.demo.heroimage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dantann.recylerviewtemplate.R;
import com.dantann.recylerviewtemplate.framework.ThrottledOnClickListener;

public class HeroImageSecondActivity extends Activity {

    private static final String HERO_TRANSITION_NAME = "HERO_TRANSITION_NAME";

    private ImageView heroView;

    public static void startActivityWithAnimation(Activity activity, View view) {
        final Intent intent = new Intent(activity, HeroImageSecondActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName(HERO_TRANSITION_NAME);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,view, HERO_TRANSITION_NAME);
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
        heroView.setImageResource(R.drawable.big_bunny_cover);

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

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }
}