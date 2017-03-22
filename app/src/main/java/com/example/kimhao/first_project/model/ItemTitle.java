package com.example.kimhao.first_project.model;

/**
 * Created by KimHao on 16/03/2017.
 */

public class ItemTitle extends ItemList {
    String strTitle;

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public ItemTitle(String strTitle) {

        this.strTitle = strTitle;
    }

    @Override
    public int getType() {
        return 1;
    }
}
