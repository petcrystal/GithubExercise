package com.zlm.project.data.model.other.article;

import android.text.Spanned;
import android.widget.TextView;

public class ArticleContent {

    private TextView content;
    private Spanned contentStr;
    private int position;

    public ArticleContent(TextView content, Spanned contentStr, int position) {
        this.content = content;
        this.contentStr = contentStr;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public TextView getContent() {
        return content;
    }

    public void setContent(TextView content) {
        this.content = content;
    }

    public Spanned getContentStr() {
        return contentStr;
    }

    public void setContentStr(Spanned contentStr) {
        this.contentStr = contentStr;
    }
}
