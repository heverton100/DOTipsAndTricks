package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("id_item")
    @Expose
    private Integer idItem;
    @SerializedName("id_categoria")
    @Expose
    private Integer idCategoria;
    @SerializedName("descricao_item")
    @Expose
    private String descricaoItem;
    @SerializedName("url_image_item")
    @Expose
    private String urlImageItem;
    @SerializedName("nome_item")
    @Expose
    private String nomeItem;
    @SerializedName("dano_base_lasers")
    @Expose
    private String danoBaseLasers;
    @SerializedName("sigla_hardware")
    @Expose
    private String siglaHardware;
    @SerializedName("existe_montagem")
    @Expose
    private String existeMontagem;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getUrlImageItem() {
        return urlImageItem;
    }

    public void setUrlImageItem(String urlImageItem) {
        this.urlImageItem = urlImageItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDanoBaseLasers() {
        return danoBaseLasers;
    }

    public void setDanoBaseLasers(String danoBaseLasers) {
        this.danoBaseLasers = danoBaseLasers;
    }

    public String getSiglaHardware() {
        return siglaHardware;
    }

    public void setSiglaHardware(String siglaHardware) {
        this.siglaHardware = siglaHardware;
    }

    public String getExisteMontagem() {
        return existeMontagem;
    }

    public void setExisteMontagem(String existeMontagem) {
        this.existeMontagem = existeMontagem;
    }

}