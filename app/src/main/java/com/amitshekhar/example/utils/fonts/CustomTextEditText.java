package com.amitshekhar.example.utils.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;



public class CustomTextEditText extends android.support.design.widget.TextInputEditText {

    public CustomTextEditText(Context context) {
        super(context);
        setFont();
    }

    public CustomTextEditText(Context context, AttributeSet set) {
        super(context, set);
        setFont();
    }

    public CustomTextEditText(Context context, AttributeSet set, int defaultStyle) {
        super(context, set, defaultStyle);
        setFont();
    }

    private void setFont() {

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        setTypeface(typeface); //function used to set font

    }
}