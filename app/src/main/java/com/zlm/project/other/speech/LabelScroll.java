package com.zlm.project.other.speech;

import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.zlm.project.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LabelScroll {

    // -------------------------------------------
    /**
     * 背景變色
     */
    public static final int ONLY_BG = 0;
    /**
     * 文字變色
     */
    public static final int ONLY_TXT = 1;
    /**
     * 背景、文字變色
     */
    public static final int ALL = 2;

    @IntDef({ONLY_BG, ONLY_TXT, ALL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    // -------------------------------------------
    private TextView txt;
    private NestedScrollView scrollView;
    private LinearLayout sumLayout;
    private int bgColor;
    private int txtColor;

    // -------------------------------------------
    public LabelScroll(NestedScrollView scrollView, LinearLayout sumLayout) {
        this.scrollView = scrollView;
        this.sumLayout = sumLayout;
        init();
    }

    // -------------------------------------------
    private void init() {
        bgColor = ContextCompat.getColor(scrollView.getContext(), R.color.main_read_a20);
        txtColor = Color.BLUE;
    }

    // -------------------------------------------

    /**
     * 設定TextView
     */
    public void setTextView(TextView txt) {
        this.txt = txt;
    }

    // -------------------------------------------

    /**
     * 設定文字資料
     *
     * @param mainStr 主要字串
     * @param subStr  比對字串
     * @param mode    啟用模式
     */
    public void setStringData(String mainStr, String subStr, @Mode int mode, int position) {
        setStringData(Html.fromHtml(mainStr), subStr, mode, position);
    }

    // -------------------------------------------

    /**
     * 設定文字資料
     *
     * @param mainStr 主要字串
     * @param subStr  比對字串
     * @param mode    啟用模式
     */
    public void setStringData(Spanned mainStr, String subStr, @Mode int mode, int position) {

        final int start = !mainStr.toString().contains(subStr) ? 0 : mainStr.toString().indexOf(subStr);
        int end = start + subStr.length();

        SpannableString colorSpanned = new SpannableString(mainStr);
        setType(mode, colorSpanned, start, end);

        txt.post(() -> {
            txt.setText(colorSpanned);
        });

        int total = 0;
        for (int count = 0; count < sumLayout.getChildCount(); count++) {
            if (sumLayout.getChildAt(count).getId() == R.id.content_layout && sumLayout.getChildAt(count) instanceof LinearLayout) {
                total += getContentLayoutHeight((LinearLayout) sumLayout.getChildAt(count), position);
                break;
            } else {
                total += sumLayout.getChildAt(count).getHeight();
            }
        }

        if (txt.getLayout() != null) {
            int lineNumber = txt.getLayout().getLineForOffset(start);
            int fragmentHeight = (lineNumber * txt.getLineHeight());
            int finalTotal = total;

            txt.post(() -> {
                scrollView.smoothScrollTo(0, finalTotal + fragmentHeight);
            });
        }
    }

    // -------------------------------------------
    private int getContentLayoutHeight(LinearLayout contentLayout, int position) {
        if (position == 0) {
            return 0;
        }
        int total = 0;
        for (int count = 0; count < position; count++) {
            total += contentLayout.getChildAt(count).getHeight();
        }
        return total;
    }

    // -------------------------------------------

    /**
     * 設定文字背景顏色
     */
    public void setBackgroundColor(int color) {
        this.bgColor = color;
    }

    // -------------------------------------------

    /**
     * 設定文字顏色
     */
    public void setTxtColor(int color) {
        this.txtColor = color;
    }

    // -------------------------------------------
    private void setSpannedColor(SpannableString spannedColor, Object colorType, int start, int end) {
        spannedColor.setSpan(colorType, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    // -------------------------------------------
    private void setType(@Mode int mode, SpannableString colorSpanned, int start, int end) {
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(bgColor);
        ForegroundColorSpan texColorSpan = new ForegroundColorSpan(txtColor);

        switch (mode) {
            case ALL:
                setSpannedColor(colorSpanned, backgroundColorSpan, start, end);
                setSpannedColor(colorSpanned, texColorSpan, start, end);
                break;
            case ONLY_BG:
                setSpannedColor(colorSpanned, backgroundColorSpan, start, end);
                break;
            case ONLY_TXT:
                setSpannedColor(colorSpanned, texColorSpan, start, end);
                break;
        }
    }

    // -------------------------------------------
}
