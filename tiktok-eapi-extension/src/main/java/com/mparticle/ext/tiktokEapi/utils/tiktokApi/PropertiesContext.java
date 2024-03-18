package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertiesContext {

    @SerializedName("contents")
    private List<Content> contents;

    @SerializedName("content_type")
    private String contentType;

    @SerializedName("currency")
    private String currency;

    @SerializedName("value")
    private float value;

    @SerializedName("query")
    private String query;

    @SerializedName("description")
    private String description;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("shop_id")
    private String shopId;

    @SerializedName("promotions")
    private List<Promotion> promotions;

    @SerializedName("tax_amount")
    private  float taxAmount;

    @SerializedName("shipping_amount")
    private float shippingAmount;

    @SerializedName("coupon_code")
    private String couponCode;

    public PropertiesContext() {

    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public float getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(float shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
