package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assembly {
    @SerializedName("id_item")
    @Expose
    private Integer idItem;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("name_item")
    @Expose
    private String nameItem;
    @SerializedName("description_item")
    @Expose
    private String descriptionItem;
    @SerializedName("url_image_item")
    @Expose
    private String urlImageItem;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    public String getUrlImageItem() {
        return urlImageItem;
    }

    public void setUrlImageItem(String urlImageItem) {
        this.urlImageItem = urlImageItem;
    }

}
