package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointsPilot {

    @SerializedName("ponto_pesquisa")
    @Expose
    private String pontoPesquisa;
    @SerializedName("qtdade_logs")
    @Expose
    private Integer qtdadeLogs;
    @SerializedName("total_logs")
    @Expose
    private Integer totalLogs;
    @SerializedName("custo_total")
    @Expose
    private String custoTotal;
    @SerializedName("custo_com_desconto")
    @Expose
    private String custoComDesconto;
    @SerializedName("custo_com_premium")
    @Expose
    private String custoComPremium;
    @SerializedName("custo_desconto_premium")
    @Expose
    private String custoDescontoPremium;

    public String getPontoPesquisa() {
        return pontoPesquisa;
    }

    public void setPontoPesquisa(String pontoPesquisa) {
        this.pontoPesquisa = pontoPesquisa;
    }

    public Integer getQtdadeLogs() {
        return qtdadeLogs;
    }

    public void setQtdadeLogs(Integer qtdadeLogs) {
        this.qtdadeLogs = qtdadeLogs;
    }

    public Integer getTotalLogs() {
        return totalLogs;
    }

    public void setTotalLogs(Integer totalLogs) {
        this.totalLogs = totalLogs;
    }

    public String getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(String custoTotal) {
        this.custoTotal = custoTotal;
    }

    public String getCustoComDesconto() {
        return custoComDesconto;
    }

    public void setCustoComDesconto(String custoComDesconto) {
        this.custoComDesconto = custoComDesconto;
    }

    public String getCustoComPremium() {
        return custoComPremium;
    }

    public void setCustoComPremium(String custoComPremium) {
        this.custoComPremium = custoComPremium;
    }

    public String getCustoDescontoPremium() {
        return custoDescontoPremium;
    }

    public void setCustoDescontoPremium(String custoDescontoPremium) {
        this.custoDescontoPremium = custoDescontoPremium;
    }

}
