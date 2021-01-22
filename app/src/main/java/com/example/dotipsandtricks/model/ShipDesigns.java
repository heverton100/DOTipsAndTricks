package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipDesigns {

    @SerializedName("id_ship_design")
    @Expose
    private Integer idShipDesign;
    @SerializedName("name_design")
    @Expose
    private String nameDesign;
    @SerializedName("image_design")
    @Expose
    private String imageDesign;

    public Integer getIdShipDesign() {
        return idShipDesign;
    }

    public void setIdShipDesign(Integer idShipDesign) {
        this.idShipDesign = idShipDesign;
    }

    public String getNameDesign() {
        return nameDesign;
    }

    public void setNameDesign(String nameDesign) {
        this.nameDesign = nameDesign;
    }

    public String getImageDesign() {
        return imageDesign;
    }

    public void setImageDesign(String imageDesign) {
        this.imageDesign = imageDesign;
    }

}
