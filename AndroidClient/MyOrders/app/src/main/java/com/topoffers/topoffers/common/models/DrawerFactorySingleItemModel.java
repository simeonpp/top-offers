package com.topoffers.topoffers.common.models;

/**
 * Created by Simeon on 5.3.2017 г..
 */

public class DrawerFactorySingleItemModel {
    private String title;
    private Class className;

    public DrawerFactorySingleItemModel(String title, Class className) {
        this.title = title;
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }
}
