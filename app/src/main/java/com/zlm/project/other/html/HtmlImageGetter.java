package com.zlm.project.other.html;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.zlm.project.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * @author Milla
 * @create 2019-05-08
 */
public class HtmlImageGetter implements Html.ImageGetter {

    private TextView textView;
    private Context context;

    // -------------------------------------------
    public HtmlImageGetter(TextView textView) {
        this.textView = textView;
        this.context = textView.getContext();
    }

    // -------------------------------------------
    @Override
    public Drawable getDrawable(String source) {
        MyDrawableWrapper myDrawable = new MyDrawableWrapper();
        Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(
                0,
                0,
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        myDrawable.setDrawable(drawable);
        Glide.with(context)
                .asBitmap()
                .load(source)
                .into(new BitmapTarget(myDrawable));
        return myDrawable;
    }

    // -------------------------------------------
    class BitmapTarget extends SimpleTarget<Bitmap> {
        private final MyDrawableWrapper myDrawable;

        public BitmapTarget(MyDrawableWrapper myDrawable) {
            this.myDrawable = myDrawable;
        }

        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
            Drawable drawable = new BitmapDrawable(context.getResources(), resource);
            // Get original image size.
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, width, height);
            myDrawable.setBounds(0, 0, width, height);
            myDrawable.setDrawable(drawable);
            textView.setText(textView.getText());
            textView.invalidate();
        }
    }

    // -------------------------------------------
}
