package com.zlm.project.data.model.other.article;


import java.util.Comparator;

public class ArticleDS implements Comparator<ArticleDS> {

    private Integer position = 99;
    private String name;
    private Object data;

    public ArticleDS(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public ArticleDS() {

    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public int compare(ArticleDS o1, ArticleDS o2) {
        return o1.position.compareTo(o2.position);
    }
}
