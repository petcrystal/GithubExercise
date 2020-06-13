package com.zlm.project.other.speech;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.zlm.project.data.model.other.article.ArticleContent;
import com.zlm.project.data.model.other.speech.Speech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Broadcaster implements TextToSpeech.OnDonListener {

    // -------------------------------------------
    private TextToSpeech textToSpeech;
    private LabelScroll labelScroll;

    // tool
    private TextView txt;

    private OnPlayDoneListener listener;
    private String splitRegex;
    // 文章分段
    private int contentCount = 0;
    // 段落
    private int playCount = 0;
    // 該段落之文字 (中、英)
    private int partCount = 1;
    private Map<String, List<Speech>> speechMap;
    private List<String> keys;
    private List<ArticleContent> contentList;
    private Spanned content;
    private Context context;

    // -------------------------------------------
    public Broadcaster(Context context, NestedScrollView scrollView, TextView txt, LinearLayout sumLayout) {
        this.context = context;
        this.txt = txt;
        labelScroll = new LabelScroll(scrollView, sumLayout);
        init();
    }

    // -------------------------------------------
    public Broadcaster(Context context, NestedScrollView scrollView, LinearLayout sumLayout) {
        this.context = context;
        labelScroll = new LabelScroll(scrollView, sumLayout);
        init();
    }

    // -------------------------------------------
    private void init() {
        // txt to speech init.
        textToSpeech = new TextToSpeech(context);
        textToSpeech.setOnDonListener(this);
        splitRegex = "。|；|！|？|;|!|、";
    }

    // -------------------------------------------

    /**
     * 設定播報內文
     *
     * @param content 內文
     */
    public void setContentStr(String content) {
        this.content = Html.fromHtml(content);
    }

    // -------------------------------------------

    /**
     * 設定播報內文
     *
     * @param contents 內文列表
     */
    public void setContentList(List<ArticleContent> contents) {
        this.contentList = contents;
    }

    // -------------------------------------------

    /**
     * 取得文章內容
     */
    private ArticleContent getArticleContent() {
        return contentList.get(contentCount);
    }

    // -------------------------------------------

    /**
     * 開始播報（單筆）
     */
    public void toSpeech() {
        if (content != null) {
            start(content, txt, -1);
        }
    }

    // -------------------------------------------

    /**
     * 開始播報（多筆）
     */
    public void toSpeechs() {
        contentCount = 0;
        if (contentList.size() > 0) {
            start(getArticleContent().getContentStr(), getArticleContent().getContent(), getArticleContent().getPosition());
        }
    }

    // -------------------------------------------

    /**
     * 開始播報
     */
    private void start(Spanned content, TextView txt, int position) {
        speechMap = textToSpeech.splitText(String.valueOf(content), splitRegex);
        keys = new ArrayList(speechMap.keySet());
        if (!keys.isEmpty()) {
            playCount = 0;
            textToSpeech.reset().toSpeech(speechMap.get(keys.get(playCount)));
            labelScroll.setTextView(txt);
            labelScroll.setStringData(content, keys.get(playCount), LabelScroll.ONLY_BG, position);
        }
    }

    // -------------------------------------------

    /**
     * 暫停播報並重整
     */
    public void reset() {
        if (txt != null) {
            // 單筆
            txt.setText(content);
        } else {
            // 多筆
            ((Activity) context).runOnUiThread(() -> contentList.get(contentCount).getContent().setText(contentList.get(contentCount).getContentStr()));
        }
        textToSpeech.reset();
        playCount = 0;
        partCount = 1;
    }

    // -------------------------------------------

    /**
     * 停止播報
     */
    public void close() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    // -------------------------------------------
    public void pause() {
        if (textToSpeech != null) {
            textToSpeech.reset();
        }
    }

    // -------------------------------------------
    public void resume() {
        partCount = 1;
        textToSpeech.reset().toSpeech(speechMap.get(keys.get(playCount)));
    }

    // -------------------------------------------
    @Override
    public void onDone(String utteranceId) {
        if (playCount == (keys.size() - 1)) {
            reset();
            // 多筆
            if (contentList != null && contentList.size() - 1 > contentCount) {
                contentCount++;
                start(getArticleContent().getContentStr(), getArticleContent().getContent(), getArticleContent().getPosition());
            } else {
                // 代表播放完畢
                contentCount = 0;
                if (listener != null) {
                    listener.done();
                }
            }
            return;
        }

        if (speechMap.get(keys.get(playCount)) != null && Objects.requireNonNull(speechMap.get(keys.get(playCount))).size() > partCount) {
            partCount++;
        } else {
            partCount = 1;
            playCount++;
            textToSpeech.toSpeech(speechMap.get(keys.get(playCount)));
            if (contentList != null) {
                // 多篇
                labelScroll.setStringData(getArticleContent().getContentStr(), keys.get(playCount), LabelScroll.ONLY_BG, getArticleContent().getPosition());
            } else {
                // 單篇
                labelScroll.setStringData(content, keys.get(playCount), LabelScroll.ALL, -1);
            }
        }
    }

    // -------------------------------------------
    public interface OnPlayDoneListener {
        void done();
    }

    // -------------------------------------------
    public void setOnPlayDoneListener(OnPlayDoneListener listener) {
        this.listener = listener;
    }
    // -------------------------------------------

}
