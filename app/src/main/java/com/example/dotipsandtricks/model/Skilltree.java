package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skilltree {

    @SerializedName("id_skill")
    @Expose
    private Integer idSkill;
    @SerializedName("name_skill")
    @Expose
    private String nameSkill;
    @SerializedName("description_skill")
    @Expose
    private String descriptionSkill;
    @SerializedName("type_skill")
    @Expose
    private String typeSkill;
    @SerializedName("image_skill")
    @Expose
    private String imageSkill;
    @SerializedName("visual_effect")
    @Expose
    private String visualEffect;

    public Integer getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Integer idSkill) {
        this.idSkill = idSkill;
    }

    public String getNameSkill() {
        return nameSkill;
    }

    public void setNameSkill(String nameSkill) {
        this.nameSkill = nameSkill;
    }

    public String getDescriptionSkill() {
        return descriptionSkill;
    }

    public void setDescriptionSkill(String descriptionSkill) {
        this.descriptionSkill = descriptionSkill;
    }


    public String getTypeSkill() {
        return typeSkill;
    }

    public void setTypeSkill(String typeSkill) {
        this.typeSkill = typeSkill;
    }

    public String getImageSkill() {
        return imageSkill;
    }

    public void setImageSkill(String imageSkill) {
        this.imageSkill = imageSkill;
    }

    public String getVisualEffect() {
        return visualEffect;
    }

    public void setVisualEffect(String visualEffect) {
        this.visualEffect = visualEffect;
    }

}
