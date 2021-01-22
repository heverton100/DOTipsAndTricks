package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointsPilot {

    @SerializedName("research_point")
    @Expose
    private String researchPoint;
    @SerializedName("quantity_logs")
    @Expose
    private Integer quantityLogs;
    @SerializedName("total_logs")
    @Expose
    private Integer totalLogs;
    @SerializedName("cost_total")
    @Expose
    private String costTotal;
    @SerializedName("cost_with_discount")
    @Expose
    private String costWithDiscount;
    @SerializedName("cost_with_premium")
    @Expose
    private String costWithPremium;
    @SerializedName("cost_discount_premium")
    @Expose
    private String costDiscountPremium;

    public String getResearchPoint() {
        return researchPoint;
    }

    public void setResearchPoint(String researchPoint) {
        this.researchPoint = researchPoint;
    }

    public Integer getQuantityLogs() {
        return quantityLogs;
    }

    public void setQuantityLogs(Integer quantityLogs) {
        this.quantityLogs = quantityLogs;
    }

    public Integer getTotalLogs() {
        return totalLogs;
    }

    public void setTotalLogs(Integer totalLogs) {
        this.totalLogs = totalLogs;
    }

    public String getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(String costTotal) {
        this.costTotal = costTotal;
    }

    public String getCostWithDiscount() {
        return costWithDiscount;
    }

    public void setCostWithDiscount(String costWithDiscount) {
        this.costWithDiscount = costWithDiscount;
    }

    public String getCostWithPremium() {
        return costWithPremium;
    }

    public void setCostWithPremium(String costWithPremium) {
        this.costWithPremium = costWithPremium;
    }

    public String getCostDiscountPremium() {
        return costDiscountPremium;
    }

    public void setCostDiscountPremium(String costDiscountPremium) {
        this.costDiscountPremium = costDiscountPremium;
    }

}
