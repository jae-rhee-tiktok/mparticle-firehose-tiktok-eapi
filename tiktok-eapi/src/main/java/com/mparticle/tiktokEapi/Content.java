package com.mparticle.tiktokEapi;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("price")
    public float price;
    @SerializedName("quantity")
    public int quantity;

    @SerializedName("content_id")
    public String contentId;

    @SerializedName("content_category")
    public String contentCategory;

    @SerializedName("content_name")
    public String contentName;

    @SerializedName("brand")
    public String brand;

    public Content() {

    }
}
