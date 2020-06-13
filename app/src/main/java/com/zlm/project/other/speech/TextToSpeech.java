package com.zlm.project.other.speech;

import android.content.Context;
import android.speech.tts.UtteranceProgressListener;
import android.widget.Toast;

import com.zlm.project.data.model.other.speech.Speech;
import com.zlm.project.utils.RegexUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TextToSpeech extends UtteranceProgressListener implements android.speech.tts.TextToSpeech.OnInitListener {

    // -------------------------------------------
    private float default_pitch;
    private float default_speed;

    private android.speech.tts.TextToSpeech speech;
    private Context context;
    private OnDonListener listener;

    // -------------------------------------------
    TextToSpeech(Context context) {
        this.context = context;
        init();
    }

    // -------------------------------------------
    private void init() {
        default_pitch = 1.0f;
        default_speed = 1.0f;
        speech = new android.speech.tts.TextToSpeech(context, this);
        speech.setOnUtteranceProgressListener(this);
    }

    // -------------------------------------------
    private boolean isChinese(char c) {
        // 判斷是否是中文 0x4E00 ~ 0x9FA5 或是 0x30 ~ 0x39 數字
        return (c >= 0x4E00 && c <= 0x9FA5) || (c >= 0x30 && c <= 0x39);
    }

    // -------------------------------------------

    /**
     * 重新組裝
     *
     * @param str 待組裝之字串
     * @return 播放列表
     */
    private List<Speech> reorganization(String str) {

        StringBuilder zh = new StringBuilder();
        StringBuilder en = new StringBuilder();
        List<Speech> speeches = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (!RegexUtil.regexSpecial(String.valueOf(str.charAt(i)))) {
                if (isChinese(str.charAt(i))) {
                    dataArrange(zh, en, speeches, str.charAt(i), false);
                } else {
                    dataArrange(en, zh, speeches, str.charAt(i), true);
                }
            }
        }

        if (zh.length() + en.length() > 0) {
            // add the last paragraph.
            String endStr = zh.length() > 0 ? zh.toString() : en.toString();
            speeches.add(new Speech(endStr, isChinese(endStr.charAt(0))));
        }

        return speeches;
    }

    // -------------------------------------------

    /**
     * 切割文字段落
     *
     * @param str   待切割字串
     * @param regex 切割規則
     * @return 語音播放資料
     */
    Map<String, List<Speech>> splitText(String str, String regex) {

        Map<String, List<Speech>> dataMap = new LinkedHashMap<>();
        List<String> splitList = new ArrayList<>(Arrays.asList(str.split(regex)));

        for (int count = 0; count < splitList.size(); count++) {
            dataMap.put(splitList.get(count), reorganization(splitList.get(count)));
        }

        return dataMap;
    }

    // -------------------------------------------

    /**
     * 將中英分開
     *
     * @param mainBuilder 主字串
     * @param subBuilder  副字串 (需要儲存的)
     * @param speeches    儲存陣列
     * @param ch          字元
     * @param isZH        是否是中文 true:是、false:否
     */
    private void dataArrange(StringBuilder mainBuilder, StringBuilder subBuilder, List<Speech> speeches, char ch, boolean isZH) {
        if (subBuilder.length() > 0) {
            speeches.add(new Speech(subBuilder.toString(), isZH));
            subBuilder.setLength(0);
        }
        mainBuilder.append(ch);
    }

    // -------------------------------------------
    @Override
    public void onInit(int status) {
        if (status == android.speech.tts.TextToSpeech.SUCCESS) {
            speech.setPitch(default_pitch);
            speech.setSpeechRate(default_speed);
            int result = speech.setLanguage(Locale.TAIWAN);
            if (result == android.speech.tts.TextToSpeech.LANG_MISSING_DATA
                    || result == android.speech.tts.TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "不支援該語音", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // -------------------------------------------

    /**
     * 設定講話語調
     *
     * @param pitch 0 ~ 1.0 ，越小越偏向男生音
     */
    public void setVoicePitch(float pitch) {
        speech.setPitch(pitch);
    }

    // -------------------------------------------

    /**
     * 設定聲音速度
     *
     * @param speed 數值越大速度越快
     */
    public void setVoiceSpeed(float speed) {
        speech.setPitch(speed);
    }

    // -------------------------------------------

    /**
     * 停止播放
     */
    public void stop() {
        if (speech != null) {
            speech.stop();
            speech.shutdown();
        }
    }

    // -------------------------------------------

    /**
     * 重整
     */
    public TextToSpeech reset() {
        if (speech != null) {
            speech.stop();
        }
        return this;
    }

    // -------------------------------------------

    /**
     * 轉語音
     *
     * @param speeches 語音段落
     */
    void toSpeech(List<Speech> speeches) {

        for (int count = 0; count < speeches.size(); count++) {
            if (speeches.get(count).isZh()) {
                toSpeech(speeches.get(count).getStr(), Locale.TAIWAN);
            } else {
                toSpeech(speeches.get(count).getStr(), Locale.ENGLISH);
            }
        }
    }

    // -------------------------------------------

    /**
     * 轉語音
     *
     * @param str    播放文字
     * @param locale 播放語言
     */
    private void toSpeech(String str, Locale locale) {
        speech.setLanguage(locale);
        speech.speak(str, android.speech.tts.TextToSpeech.QUEUE_ADD, null, locale.getLanguage());
    }

    // -------------------------------------------
    @Override
    public void onStart(String utteranceId) {
    }

    @Override
    public void onDone(String utteranceId) {
        if (listener != null) {
            listener.onDone(utteranceId);
        }
    }

    @Override
    public void onError(String utteranceId) {
        stop();
    }

    // -------------------------------------------

    /**
     * 是否播放中
     */
    public boolean isSpeaking() {
        return speech.isSpeaking();
    }

    // -------------------------------------------
    void setOnDonListener(OnDonListener listener) {
        this.listener = listener;
    }

    // -------------------------------------------
    public interface OnDonListener {
        void onDone(String utteranceId);
    }

    // -------------------------------------------
}
