package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserContext {

    @SerializedName("email")
    public List<String> email;

    @SerializedName("phone")
    public List<String> phone;

    @SerializedName("extId")
    public List<String> extId;

    @SerializedName("ip")
    public String ip;

    @SerializedName("user_agent")
    public String userAgent;

    @SerializedName("locale")
    public String locale;

    @SerializedName("clickId")
    public String clickId;

    @SerializedName("cookieId")
    public String cookieId;

    @SerializedName("idfa")
    public String idfa;

    @SerializedName("idfv")
    public String idfv;

    @SerializedName("gaid")
    public String gaid;

    @SerializedName("att_status")
    public String attStatus;

    public UserContext() {

    }
}
