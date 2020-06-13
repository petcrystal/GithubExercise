package com.zlm.project.data.model.other.xmpp;

/**
 * @author Milla
 * @create 2019/4/18
 */
public class XMPPRequest<T> {

    private int Action;
    private MetaBean Meta;
    private String Token;
    private Object Opt;
    private DataBean Data;

    public static class MetaBean {

        private String Ver;
        private int Device;
        private int From;
        private String Time;

        public MetaBean(String ver, int device, int from, String time) {
            Ver = ver;
            Device = device;
            From = from;
            Time = time;
        }

        public String getVer() {
            return Ver;
        }

        public void setVer(String ver) {
            Ver = ver;
        }

        public int getDevice() {
            return Device;
        }

        public void setDevice(int device) {
            Device = device;
        }

        public int getFrom() {
            return From;
        }

        public void setFrom(int from) {
            From = from;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }
    }

    public static class DataBean<T> {
        private String EventName;
        private T Data;

        public DataBean(String eventName, T data) {
            EventName = eventName;
            Data = data;
        }

        public String getEventName() {
            return EventName;
        }

        public void setEventName(String eventName) {
            EventName = eventName;
        }

        public T getData() {
            return Data;
        }

        public void setData(T data) {
            Data = data;
        }
    }

    public int getAction() {
        return Action;
    }

    public void setAction(int action) {
        Action = action;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public MetaBean getMeta() {
        return Meta;
    }

    public void setMeta(MetaBean meta) {
        Meta = meta;
    }

    private String EventName;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
