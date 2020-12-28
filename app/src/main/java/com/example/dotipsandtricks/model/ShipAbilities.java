package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipAbilities {

    @SerializedName("id_ability")
    @Expose
    private Integer idAbility;
    @SerializedName("id_ship")
    @Expose
    private Integer idShip;
    @SerializedName("ability_name")
    @Expose
    private String abilityName;
    @SerializedName("ability_desc")
    @Expose
    private String abilityDesc;
    @SerializedName("ability_image")
    @Expose
    private String abilityImage;

    public Integer getIdAbility() {
        return idAbility;
    }

    public void setIdAbility(Integer idAbility) {
        this.idAbility = idAbility;
    }

    public Integer getIdShip() {
        return idShip;
    }

    public void setIdShip(Integer idShip) {
        this.idShip = idShip;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityDesc() {
        return abilityDesc;
    }

    public void setAbilityDesc(String abilityDesc) {
        this.abilityDesc = abilityDesc;
    }

    public String getAbilityImage() {
        return abilityImage;
    }

    public void setAbilityImage(String abilityImage) {
        this.abilityImage = abilityImage;
    }

}
