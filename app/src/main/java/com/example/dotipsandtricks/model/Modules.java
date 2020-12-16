package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modules {

    @SerializedName("id_modulo")
    @Expose
    private Integer idModulo;
    @SerializedName("nome_modulo")
    @Expose
    private String nomeModulo;
    @SerializedName("descricao_modulo")
    @Expose
    private String descricaoModulo;
    @SerializedName("tipo_modulo")
    @Expose
    private String tipoModulo;
    @SerializedName("image_modulo")
    @Expose
    private String imageModulo;


    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public void setNomeModulo(String nomeModulo) {
        this.nomeModulo = nomeModulo;
    }

    public String getDescricaoModulo() {
        return descricaoModulo;
    }

    public void setDescricaoModulo(String descricaoModulo) {
        this.descricaoModulo = descricaoModulo;
    }

    public String getTipoModulo() {
        return tipoModulo;
    }

    public void setTipoModulo(String tipoModulo) {
        this.tipoModulo = tipoModulo;
    }

    public String getImageModulo() {
        return imageModulo;
    }

    public void setImageModulo(String imageModulo) {
        this.imageModulo = imageModulo;
    }

}