package com.dantann.recylerviewtemplate.demo.heroimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dantann.recylerviewtemplate.R;
import com.dantann.recylerviewtemplate.framework.ThrottledOnClickListener;

public class HeroImageFirstActivity extends Activity {

    private ImageView heroView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_center);
        heroView = (ImageView) findViewById(R.id.imageView);
        heroView.setImageResource(R.drawable.big_bunny_cover);
        heroView.setOnClickListener(new ThrottledOnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                HeroImageSecondActivity.startActivityWithAnimation(HeroImageFirstActivity.this,heroView);
            }
        });
    }
}