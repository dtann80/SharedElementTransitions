package com.dantann.sharedelement.demo;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dantann.sharedelement.R;
import com.dantann.sharedelement.util.MaterialColorPalette;

public class SimpleCardView extends CardView {

    public final TextView titleTextView;
    public final TextView subtitleTextView;
    public final ViewGroup container;

    public SimpleCardView(Context context) {
        this(context,null);
    }

    public SimpleCardView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.view_simple_card, this);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        subtitleTextView = (TextView) findViewById(R.id.subtitleTextView);
        container = (ViewGroup) findViewById(R.id.container);

        setRadius(getResources().getDimensionPixelSize(R.dimen.simple_card_corner_radius));
        setCardElevation(getResources().getDimensionPixelSize(R.dimen.simple_card_elevation));
        setPreventCornerOverlap(false);
        setForeground(ContextCompat.getDrawable(getContext(), R.drawable.default_selector));

        container.setBackgroundColor(MaterialColorPalette.randomColor());
    }
}
