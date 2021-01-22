package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ships {

    @SerializedName("id_ship")
    @Expose
    private Integer idShip;
    @SerializedName("name_ship")
    @Expose
    private String nameShip;
    @SerializedName("description_ship")
    @Expose
    private String descriptionShip;
    @SerializedName("bonus")
    @Expose
    private String bonus;
    @SerializedName("velocity")
    @Expose
    private Integer velocity;
    @SerializedName("cargo")
    @Expose
    private Integer cargo;
    @SerializedName("points_hp")
    @Expose
    private Integer pointsHp;
    @SerializedName("slots_lasers")
    @Expose
    private Integer slotsLasers;
    @SerializedName("slots_generators")
    @Expose
    private Integer slotsGenerators;
    @SerializedName("slots_extras")
    @Expose
    private Integer slotsExtras;
    @SerializedName("slots_modules_ship")
    @Expose
    private Integer slotsModulesShip;
    @SerializedName("image_ship")
    @Expose
    private String imageShip;
    @SerializedName("has_ability")
    @Expose
    private String hasAbility;
    @SerializedName("slots_missile_launcher")
    @Expose
    private Integer slotsMissileLauncher;

    public Integer getIdShip() {
        return idShip;
    }

    public void setIdShip(Integer idShip) {
        this.idShip = idShip;
    }

    public String getNameShip() {
        return nameShip;
    }

    public void setNameShip(String nameShip) {
        this.nameShip = nameShip;
    }

    public String getDescriptionShip() {
        return descriptionShip;
    }

    public void setDescriptionShip(String descriptionShip) {
        this.descriptionShip = descriptionShip;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public Integer getPointsHp() {
        return pointsHp;
    }

    public void setPointsHp(Integer pointsHp) {
        this.pointsHp = pointsHp;
    }

    public Integer getSlotsLasers() {
        return slotsLasers;
    }

    public void setSlotsLasers(Integer slotsLasers) {
        this.slotsLasers = slotsLasers;
    }

    public Integer getSlotsGenerators() {
        return slotsGenerators;
    }

    public void setSlotsGenerators(Integer slotsGenerators) {
        this.slotsGenerators = slotsGenerators;
    }

    public Integer getSlotsExtras() {
        return slotsExtras;
    }

    public void setSlotsExtras(Integer slotsExtras) {
        this.slotsExtras = slotsExtras;
    }

    public Integer getSlotsModulesShip() {
        return slotsModulesShip;
    }

    public void setSlotsModulesShip(Integer slotsModulesShip) {
        this.slotsModulesShip = slotsModulesShip;
    }

    public String getImageShip() {
        return imageShip;
    }

    public void setImageShip(String imageShip) {
        this.imageShip = imageShip;
    }

    public String getHasAbility() {
        return hasAbility;
    }

    public void setHasAbility(String hasAbility) {
        this.hasAbility = hasAbility;
    }

    public Integer getSlotsMissileLauncher() {
        return slotsMissileLauncher;
    }

    public void setSlotsMissileLauncher(Integer slotsMissileLauncher) {
        this.slotsMissileLauncher = slotsMissileLauncher;
    }

}