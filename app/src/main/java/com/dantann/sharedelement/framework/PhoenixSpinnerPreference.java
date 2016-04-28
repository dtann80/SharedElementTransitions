package com.dantann.sharedelement.framework;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.dantann.sharedelement.R;
import com.jakewharton.processphoenix.ProcessPhoenix;


/**
 * {@link Preference} that contains a spinner for selecting different options.
 * After selecting an option will result in restarting the app.
 *
 * @param <T> {@link BindableAdapter} that provides options for spinner
 */
public abstract class PhoenixSpinnerPreference<T extends BindableAdapter> extends Preference {

    /**
     * Current selected position in the spinner
     */
    private int mSelection = 0;

    /**
     * The adapter to be used to populate the spinner
     */
    protected T mAdapter;

    protected String mDefaultValue;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PhoenixSpinnerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    public PhoenixSpinnerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public PhoenixSpinnerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public PhoenixSpinnerPreference(Context context) {
        super(context);
        initialize();
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        mDefaultValue = a.getString(index);
        return mDefaultValue;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        if (spinner != null) {
            spinner.setAdapter(mAdapter);
            String defaultValue = getPersistedString(mDefaultValue);
            int pos = getPositionForString(defaultValue);
            mSelection = pos;
            spinner.setSelection(pos,false);
            spinner.setOnItemSelectedListener(new OnItemSelectedListener());
        }
    }

    protected void initialize() {
        setLayoutResource(R.layout.view_preference_spinner);
        mAdapter = onCreateAdapter(getContext());
        if (TextUtils.isEmpty(mDefaultValue)) {
            mDefaultValue = getStringForPosition(0);
        }
    }

    protected void performAppRestart() {
        setEnabled(false);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ProcessPhoenix.triggerRebirth(getContext());
            }
        });
    }

    /**
     * Called to create the adapter
     *
     * @param context
     * @return {@link BindableAdapter} that is used to populate the spinner
     */
    public abstract T onCreateAdapter(Context context);

    /**
     * Called when a user selects an item from the spinner
     *
     * @param selectedPosition - the new selected position
     * @param oldPosition - the last selected position
     */
    public abstract void onItemSelected(int selectedPosition, int oldPosition);

    /**
     * Used to retrieve String representation of the spinner item position
     *
     * @param position - position of spinner item
     * @return String representation of the specified position
     */
    public abstract String getStringForPosition(int position);


    /**
     * Used to get spinner item position by specified name
     *
     * @param name - name that is mapped to a given spinner item position
     * @return - spinner item position
     */
    public abstract int getPositionForString(String name);

    private class OnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (mSelection != position) {
                int oldPosition = mSelection;
                mSelection = position;
                persistString(getStringForPosition(mSelection));
                PhoenixSpinnerPreference.this.onItemSelected(mSelection, oldPosition);
                performAppRestart();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
