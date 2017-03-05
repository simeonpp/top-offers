package com.topoffers.topoffers.common.models;

import java.util.List;

/**
 * Created by Simeon on 5.3.2017 г..
 */

public class DrawerFactoryModel {
    private List<DrawerFactorySingleItemModel> items;

    public DrawerFactoryModel(List<DrawerFactorySingleItemModel> items) {
        this.items = items;
    }

    public List<DrawerFactorySingleItemModel> getItems() {
        return items;
    }

    public void setItems(List<DrawerFactorySingleItemModel> items) {
        this.items = items;
    }
}
