package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("price")
    private float price;
    @SerializedName("quantity")
    private int quantity;

    @SerializedName("content_id")
    private String contentId;

    @SerializedName("content_category")
    private String contentCategory;

    @SerializedName("content_name")
    private String contentName;

    @SerializedName("brand")
    private String brand;

    public Content() {

    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentCategory() {
        return contentCategory;
    }

    public void setContentCategory(String contentCategory) {
        this.contentCategory = contentCategory;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
