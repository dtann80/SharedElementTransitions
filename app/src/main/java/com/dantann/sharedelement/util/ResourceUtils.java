package com.dantann.sharedelement.util;


import android.content.res.Resources;
import android.util.TypedValue;

public class ResourceUtils {

    public static float convertDpToPx(Resources resources, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }
}
