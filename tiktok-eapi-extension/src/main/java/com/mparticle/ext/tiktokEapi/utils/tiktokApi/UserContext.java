package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserContext {

    @SerializedName("email")
    private List<String> email;

    @SerializedName("phone")
    private List<String> phone;

    @SerializedName("external_id")
    private List<String> extId;

    @SerializedName("ip")
    private String ip;

    @SerializedName("user_agent")
    private String userAgent;

    @SerializedName("locale")
    private String locale;

    @SerializedName("ttclid")
    private String clickId;

    @SerializedName("ttp")
    private String cookieId;

    @SerializedName("idfa")
    private String idfa;

    @SerializedName("idfv")
    private String idfv;

    @SerializedName("gaid")
    private String gaid;

    @SerializedName("att_status")
    private String attStatus;

    @SerializedName("fn")
    private String firstName;

    @SerializedName("ln")
    private String lastName;

    @SerializedName("city")
    private String city;

    @SerializedName("region")
    private String region;

    @SerializedName("country")
    private String country;

    @SerializedName("zip")
    private String zip;

    public UserContext() {

    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getExtId() {
        return extId;
    }

    public void setExtId(List<String> extId) {
        this.extId = extId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getGaid() {
        return gaid;
    }

    public void setGaid(String gaid) {
        this.gaid = gaid;
    }

    public String getAttStatus() {
        return attStatus;
    }

    public void setAttStatus(String attStatus) {
        this.attStatus = attStatus;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
