package com.zlm.project.other.html;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author Milla
 * @create 2019-05-08
 */
public class MyDrawableWrapper extends BitmapDrawable {

    private Drawable drawable;

    // -------------------------------------------
    MyDrawableWrapper() {
    }

    // -------------------------------------------
    @Override
    public void draw(Canvas canvas) {
        if (drawable != null)
            drawable.draw(canvas);
    }

    // -------------------------------------------
    public Drawable getDrawable() {
        return drawable;
    }

    // -------------------------------------------
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    // -------------------------------------------
}
