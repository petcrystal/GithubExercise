package com.zlm.project.data.model.other.xmpp;

/**
 * @author Milla
 * @create 2019/4/18
 */
public class XMPPChannel {

    private String ChannelId;
    private int Position;

    public XMPPChannel(String channelId, int position) {
        ChannelId = channelId;
        Position = position;
    }

    public String getChannelId() {
        return ChannelId;
    }

    public void setChannelId(String channelId) {
        ChannelId = channelId;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
}
