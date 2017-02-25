package com.topoffers.topoffers.common.models;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int quantity;
    private double singlePrice;
    private double totalPrice;
    private String status;
    private String productTitle;
    private String productImageIdentifier;
    private String productDescription;
    private String productSellerFirstName;
    private String productSellerLastName;
    private String productSellerAddress;
    private String productSellerPhone;
    private String productSellerUsername;
    private String buyerFirstName;
    private String buyerLastName;
    private String buyerAddress;
    private String buyerUsername;

    public Order(int id, int quantity, double singlePrice, double totalPrice, String status, String productTitle, String productImageIdentifier, String productDescription, String productSellerFirstName, String productSellerLastName, String productSellerAddress, String productSellerPhone, String productSellerUsername, String buyerFirstName, String buyerLastName, String buyerAddress, String buyerUsername) {
        this.id = id;
        this.quantity = quantity;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.productTitle = productTitle;
        this.productImageIdentifier = productImageIdentifier;
        this.productDescription = productDescription;
        this.productSellerFirstName = productSellerFirstName;
        this.productSellerLastName = productSellerLastName;
        this.productSellerAddress = productSellerAddress;
        this.productSellerPhone = productSellerPhone;
        this.productSellerUsername = productSellerUsername;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
        this.buyerAddress = buyerAddress;
        this.buyerUsername = buyerUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImageIdentifier() {
        return productImageIdentifier;
    }

    public void setProductImageIdentifier(String productImageIdentifier) {
        this.productImageIdentifier = productImageIdentifier;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductSellerFirstName() {
        return productSellerFirstName;
    }

    public void setProductSellerFirstName(String productSellerFirstName) {
        this.productSellerFirstName = productSellerFirstName;
    }

    public String getProductSellerLastName() {
        return productSellerLastName;
    }

    public void setProductSellerLastName(String productSellerLastName) {
        this.productSellerLastName = productSellerLastName;
    }

    public String getProductSellerAddress() {
        return productSellerAddress;
    }

    public void setProductSellerAddress(String productSellerAddress) {
        this.productSellerAddress = productSellerAddress;
    }

    public String getProductSellerPhone() {
        return productSellerPhone;
    }

    public void setProductSellerPhone(String productSellerPhone) {
        this.productSellerPhone = productSellerPhone;
    }

    public String getProductSellerUsername() {
        return productSellerUsername;
    }

    public void setProductSellerUsername(String productSellerUsername) {
        this.productSellerUsername = productSellerUsername;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }
}
