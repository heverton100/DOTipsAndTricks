package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modules {

    @SerializedName("id_module")
    @Expose
    private Integer idModule;
    @SerializedName("name_module")
    @Expose
    private String nameModule;
    @SerializedName("description_module")
    @Expose
    private String descriptionModule;
    @SerializedName("type_module")
    @Expose
    private String typeModule;
    @SerializedName("image_module")
    @Expose
    private String imageModule;


    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getNameModule() {
        return nameModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }

    public String getDescriptionModule() {
        return descriptionModule;
    }

    public void setDescriptionModule(String descriptionModule) {
        this.descriptionModule = descriptionModule;
    }

    public String getTypeModule() {
        return typeModule;
    }

    public void setTypeModule(String typeModule) {
        this.typeModule = typeModule;
    }

    public String getImageModule() {
        return imageModule;
    }

    public void setImageModule(String imageModule) {
        this.imageModule = imageModule;
    }

}