package com.zlm.project.data.model.other.article;

/**
 * @author Milla
 * @create 2019-05-07
 */
public class FavoriteType {

    private String name;
    private boolean isAdd;

    public FavoriteType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
