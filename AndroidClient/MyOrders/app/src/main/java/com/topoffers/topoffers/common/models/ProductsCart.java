package com.topoffers.topoffers.common.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dsm2001 on 3/4/2017.
 */

public class ProductsCart implements Serializable{

    private ArrayList<Product> products = new ArrayList<>();

    public  ProductsCart(){

    }

    public void AddProduct(Product product){
        this.products.add(product);
    }

    public ArrayList<Product> getProducts(){
        return this.products;
    }
}
