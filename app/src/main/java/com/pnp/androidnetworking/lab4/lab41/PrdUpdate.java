package com.pnp.androidnetworking.lab4.lab41;

import com.google.gson.annotations.SerializedName;

public class PrdUpdate {
    @SerializedName("pid")
    private String pid;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;

    public PrdUpdate(String pid, String name, String price, String description) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public PrdUpdate() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
