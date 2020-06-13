package com.zlm.project.data.model.other.speech;

public class Speech {

    private String str;
    private boolean isZh;

    public Speech(String str, boolean isZh) {
        this.str = str;
        this.isZh = isZh;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isZh() {
        return isZh;
    }

    public void setZh(boolean zh) {
        isZh = zh;
    }
}
