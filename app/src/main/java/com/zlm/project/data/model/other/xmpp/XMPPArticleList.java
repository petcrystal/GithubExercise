package com.zlm.project.data.model.other.xmpp;

/**
 * @author Milla
 * @create 2019/4/18
 */
public class XMPPArticleList {

    private String ArticleId;
    private int Position;

    public XMPPArticleList(String articleId, int position) {
        ArticleId = articleId;
        Position = position;
    }

    public String getArticleId() {
        return ArticleId;
    }

    public void setArticleId(String articleId) {
        ArticleId = articleId;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
}
