package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skilllevels {

    @SerializedName("id_skill")
    @Expose
    private Integer idSkill;
    @SerializedName("level_skill")
    @Expose
    private Integer levelSkill;
    @SerializedName("value_skill")
    @Expose
    private Integer valueSkill;

    public Integer getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Integer idSkill) {
        this.idSkill = idSkill;
    }

    public Integer getLevelSkill() {
        return levelSkill;
    }

    public void setLevelSkill(Integer levelSkill) {
        this.levelSkill = levelSkill;
    }

    public Integer getValueSkill() {
        return valueSkill;
    }

    public void setValueSkill(Integer valueSkill) {
        this.valueSkill = valueSkill;
    }

}
