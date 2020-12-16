package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assembly {
    @SerializedName("id_item")
    @Expose
    private Integer idItem;
    @SerializedName("quantidade")
    @Expose
    private Integer quantidade;
    @SerializedName("nome_item")
    @Expose
    private String nomeItem;
    @SerializedName("descricao_item")
    @Expose
    private String descricaoItem;
    @SerializedName("url_image_item")
    @Expose
    private String urlImageItem;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
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

}
