package com.zlm.project.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author Milla
 * @create 2019/4/2
 */
public final class BindingUtil {

    // -------------------------------------------
    private BindingUtil() {

    }

    // -------------------------------------------
    @BindingAdapter(value = {"imageUrl", "radius"}, requireAll = false)
    public static void setImageUrl(ImageView imageView, String url, int radius) {
        Context context = imageView.getContext();
        int default_radius = 4;

        RequestOptions requestOptions = new RequestOptions();
        if (radius > 0) {
            default_radius = radius;
        }
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(default_radius));
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    // -------------------------------------------
    @BindingAdapter("imageUrlToCircle")
    public static void setImageUrlCircle(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    // -------------------------------------------
    @BindingAdapter("characterBackground")
    public static void setCharacterBackground(TextView textView, int res) {
        Context context = textView.getContext();
        if (res != 0) {
            textView.setBackground(context.getDrawable(res));
        }
    }

    @BindingAdapter("characterText")
    public static void setCharacterText(TextView textView, int res) {
        Context context = textView.getContext();
        if (res != 0) {
            textView.setTextColor(context.getResources().getColor(res));
        }
    }

    // -------------------------------------------
    @BindingAdapter("btnTextColor")
    public static void setBtnTextColor(Button button, int res) {
        Context context = button.getContext();
        if (res != 0) {
            button.setTextColor(context.getResources().getColor(res));
        }
    }

    // -------------------------------------------
    @BindingAdapter("android:layout_marginTop")
    public static void setLayoutMarginTop(View view, float margin) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp != null) {
            lp.setMargins(lp.leftMargin, (int) margin, lp.rightMargin, lp.bottomMargin);
            view.setLayoutParams(lp);
        }
    }



    // -------------------------------------------

/*
    // -------------------------------------------
    @BindingAdapter("adapter")
    public static void addPaymentItems(RecyclerView recyclerView, List<BlockContentResponse.DataBean> dataBeans) {
        if (recyclerView.getAdapter() != null && (recyclerView.getAdapter() instanceof PaymentLatestAdapter) && dataBeans!=null) {
            ((PaymentLatestAdapter) recyclerView.getAdapter()).setData(dataBeans);
        }
    }

*/
}
