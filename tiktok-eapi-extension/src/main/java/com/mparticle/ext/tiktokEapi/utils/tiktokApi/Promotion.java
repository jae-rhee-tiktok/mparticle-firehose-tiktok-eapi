package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class Promotion {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("creative")
    private String creative;

    @SerializedName("position")
    private String position;

    public String getPosition() {
        return position;
    }

    public Promotion() {

    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCreative() {
        return creative;
    }

    public void setCreative(String creative) {
        this.creative = creative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
