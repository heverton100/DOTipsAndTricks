package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("id_item")
    @Expose
    private Integer idItem;
    @SerializedName("id_category")
    @Expose
    private Integer idCategory;
    @SerializedName("description_item")
    @Expose
    private String descriptionItem;
    @SerializedName("url_image_item")
    @Expose
    private String urlImageItem;
    @SerializedName("name_item")
    @Expose
    private String nameItem;
    @SerializedName("damage_base_lasers")
    @Expose
    private String damageBaseLasers;
    @SerializedName("sigla_hardware")
    @Expose
    private String siglaHardware;
    @SerializedName("exists_assembly")
    @Expose
    private String existsAssembly;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
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

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getDamageBaseLasers() {
        return damageBaseLasers;
    }

    public void setDamageBaseLasers(String damageBaseLasers) {
        this.damageBaseLasers = damageBaseLasers;
    }

    public String getSiglaHardware() {
        return siglaHardware;
    }

    public void setSiglaHardware(String siglaHardware) {
        this.siglaHardware = siglaHardware;
    }

    public String getExistsAssembly() {
        return existsAssembly;
    }

    public void setExistsAssembly(String existsAssembly) {
        this.existsAssembly = existsAssembly;
    }

}