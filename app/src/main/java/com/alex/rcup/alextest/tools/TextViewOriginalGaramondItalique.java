package com.alex.rcup.alextest.tools;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Alex on 4/22/16.
 */
public class TextViewOriginalGaramondItalique extends TextView

{

    public TextViewOriginalGaramondItalique(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewOriginalGaramondItalique(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewOriginalGaramondItalique(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/originalgaramonditalic.ttf");
        setTypeface(tf);
    }

}