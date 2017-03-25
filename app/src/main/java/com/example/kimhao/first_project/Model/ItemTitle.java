package com.example.kimhao.first_project.Model;

/**
 * Created by KimHao on 16/03/2017.
 */

public class ItemTitle extends ItemList {
    private final int VIEW_TITLE = 2;
    private String mTitle;

    public ItemTitle(){
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String strTitle) {
        this.mTitle = strTitle;
    }

    public ItemTitle(String strTitle) {

        this.mTitle = strTitle;
    }

    @Override
    public int getType() {
        return VIEW_TITLE;
    }
}
