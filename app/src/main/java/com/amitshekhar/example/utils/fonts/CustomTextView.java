package com.amitshekhar.example.utils.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        setFont();
    }

    public CustomTextView(Context context, AttributeSet set) {
        super(context, set);
        setFont();
    }

    public CustomTextView(Context context, AttributeSet set, int defaultStyle) {
        super(context, set, defaultStyle);
        setFont();
    }

    private void setFont() {

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Black.ttf");
        setTypeface(typeface); //function used to set font

    }
}
