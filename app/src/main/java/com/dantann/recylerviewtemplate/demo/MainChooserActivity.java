package com.dantann.recylerviewtemplate.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dantann.recylerviewtemplate.R;
import com.dantann.recylerviewtemplate.demo.heroimage.HeroImageFirstActivity;
import com.dantann.recylerviewtemplate.framework.BaseViewHolder;
import com.dantann.recylerviewtemplate.framework.PresenterAdapter;
import com.dantann.recylerviewtemplate.framework.SpacesItemDecoration;
import com.dantann.recylerviewtemplate.framework.ViewHolderPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainChooserActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<IntentModel> intentModels = new ArrayList<>();

        IntentModel model = new IntentModel();
        model.title = "Hero Image Transition";
        model.subtitle = "Activity showcasing hero transition.";
        model.intent = new Intent(this, HeroImageFirstActivity.class);
        intentModels.add(model);

        IntentAdapter intentAdapter = new IntentAdapter(intentModels);
        intentAdapter.addViewTypePresenter(IntentAdapter.SIMPLE_VIEW_TYPE,new IntentPresenter());
        mRecyclerView.setLayoutManager(new SimpleLayoutManager(this));
        mRecyclerView.setAdapter(intentAdapter);
        int padding = (int) getResources().getDimension(R.dimen.simple_cardPadding);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(padding));

    }

    private static class IntentAdapter extends PresenterAdapter {

        public static final int SIMPLE_VIEW_TYPE = 0;

        private List<IntentModel> intentModels;

        public IntentAdapter(List<IntentModel> models) {
            intentModels = models;
        }

        @Nullable
        @Override
        public Object getDataForPosition(int position) {
            return intentModels.get(position);
        }

        @Override
        public int getItemViewType(int position) {
            return SIMPLE_VIEW_TYPE;
        }

        @Override
        public int getItemCount() {
            return intentModels.size();
        }
    }

    private static class IntentModel {
        private String title;
        private String subtitle;
        private Intent intent;
    }

    private static class IntentPresenter implements ViewHolderPresenter<IntentModel> {

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BaseViewHolder(new SimpleCardView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, final IntentModel model) {
            SimpleCardView simpleCardView = (SimpleCardView) holder.itemView;
            simpleCardView.titleTextView.setText(model.title);
            simpleCardView.subtitleTextView.setText(model.subtitle);
            simpleCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(model.intent);
                }
            });
        }

        @Override
        public void onUnbindViewHolder(BaseViewHolder holder) {
            SimpleCardView simpleCardView = (SimpleCardView) holder.itemView;
            simpleCardView.titleTextView.setText("");
            simpleCardView.subtitleTextView.setText("");
            simpleCardView.setOnClickListener(null);
        }

    }

    private static class SimpleLayoutManager extends LinearLayoutManager {

        public SimpleLayoutManager(Context context) {
            super(context);
        }

        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
