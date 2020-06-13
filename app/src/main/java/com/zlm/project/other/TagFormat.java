package com.zlm.project.other;

import androidx.lifecycle.MutableLiveData;

import com.zlm.project.R;
import com.zlm.project.utils.RegexUtil;

/**
 * @author Milla
 * @create 2019/4/10
 */
public class TagFormat {

    // -------------------------------------------
    private final int LIMITED_TIME = 1;
    private final int PAYMENT = 2;

    private MutableLiveData<Boolean> isTag;
    private MutableLiveData<String> tag;
    private MutableLiveData<Integer> tagRes;
    private MutableLiveData<Integer> txtColor;

    private String tagName;

    // -------------------------------------------
    public boolean setTag(boolean isTop, boolean isAd, int accessType, MutableLiveData<String> tag, MutableLiveData<Boolean> isTag, MutableLiveData<Integer> tagRes, MutableLiveData<Integer> txtColor) {

        this.isTag = isTag;
        this.tag = tag;
        this.tagRes = tagRes;
        this.txtColor = txtColor;

        if (isAd && !isTop) {
            setTagStatus("Sponsor", true, R.drawable.frame_tag, R.color.mid_gray);
            return true;
        } else if (isAd && isTop) {
            setTagStatus("Sponsor", true, R.drawable.frame_tag_white, R.color.white);
            return true;
        }

        switch (accessType) {
            case LIMITED_TIME:
                setTagStatus("限時免費", true, R.drawable.frame_tag_red, R.color.white);
                return true;
            case PAYMENT:
                setTagStatus("付費", true, R.drawable.frame_tag_gold, R.color.white);
                return true;
            default:
                setTagStatus("", false, R.drawable.frame_tag_gold, R.color.white);
                return false;
        }
    }

    // -------------------------------------------
    public int getLength() {
        if (tagName.length() > 0) {
            return (int) (tagName.length() * (RegexUtil.isChinese(tagName.charAt(0)) ? 2 : 1) * 2.6) + (tagName.length() > 2 ? -1 : 1);
        } else {
            return 0;
        }
    }

    // -------------------------------------------
    private void setTagStatus(String tagName, boolean isShow, int res, int color) {
        this.tagName = tagName;
        tag.postValue(tagName);
        isTag.postValue(isShow);
        tagRes.postValue(res);
        txtColor.postValue(color);
    }

    // -------------------------------------------
}
