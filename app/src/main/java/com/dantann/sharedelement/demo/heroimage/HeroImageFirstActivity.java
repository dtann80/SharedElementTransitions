package com.dantann.sharedelement.demo.heroimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dantann.sharedelement.R;
import com.dantann.sharedelement.framework.ThrottledOnClickListener;

public class HeroImageFirstActivity extends AppCompatActivity {

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
                HeroImageSecondActivity.startActivityWithHeroAnimation(HeroImageFirstActivity.this,heroView);
            }
        });
    }
}