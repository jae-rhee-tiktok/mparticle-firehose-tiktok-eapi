package com.mparticle.tiktokEapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertiesContext {

    @SerializedName("contents")
    public List<Content> contents;

    @SerializedName("content_type")
    public String contentType;

    @SerializedName("currency")
    public String currency;

    @SerializedName("value")
    public float value;

    @SerializedName("query")
    public String query;

    @SerializedName("description")
    public String description;

    @SerializedName("order_id")
    public String orderId;

    @SerializedName("shop_id")
    public String shopId;

    public PropertiesContext() {

    }
}
