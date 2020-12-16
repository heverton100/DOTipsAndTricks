package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ships {

    @SerializedName("id_nave")
    @Expose
    private Integer idNave;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("bonus")
    @Expose
    private String bonus;
    @SerializedName("velocidade")
    @Expose
    private Integer velocidade;
    @SerializedName("carga")
    @Expose
    private Integer carga;
    @SerializedName("pontos_hp")
    @Expose
    private Integer pontosHp;
    @SerializedName("slots_laser")
    @Expose
    private Integer slotsLaser;
    @SerializedName("slots_geradores")
    @Expose
    private Integer slotsGeradores;
    @SerializedName("slots_extras")
    @Expose
    private Integer slotsExtras;
    @SerializedName("slots_modulos_nave")
    @Expose
    private Integer slotsModulosNave;
    @SerializedName("image_nave")
    @Expose
    private String imageNave;
    @SerializedName("tem_habilidade")
    @Expose
    private String temHabilidade;
    @SerializedName("slots_lanca_misseis")
    @Expose
    private Integer slotsLancaMisseis;

    public Integer getIdNave() {
        return idNave;
    }

    public void setIdNave(Integer idNave) {
        this.idNave = idNave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public Integer getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Integer velocidade) {
        this.velocidade = velocidade;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public Integer getPontosHp() {
        return pontosHp;
    }

    public void setPontosHp(Integer pontosHp) {
        this.pontosHp = pontosHp;
    }

    public Integer getSlotsLaser() {
        return slotsLaser;
    }

    public void setSlotsLaser(Integer slotsLaser) {
        this.slotsLaser = slotsLaser;
    }

    public Integer getSlotsGeradores() {
        return slotsGeradores;
    }

    public void setSlotsGeradores(Integer slotsGeradores) {
        this.slotsGeradores = slotsGeradores;
    }

    public Integer getSlotsExtras() {
        return slotsExtras;
    }

    public void setSlotsExtras(Integer slotsExtras) {
        this.slotsExtras = slotsExtras;
    }

    public Integer getSlotsModulosNave() {
        return slotsModulosNave;
    }

    public void setSlotsModulosNave(Integer slotsModulosNave) {
        this.slotsModulosNave = slotsModulosNave;
    }

    public String getImageNave() {
        return imageNave;
    }

    public void setImageNave(String imageNave) {
        this.imageNave = imageNave;
    }

    public String getTemHabilidade() {
        return temHabilidade;
    }

    public void setTemHabilidade(String temHabilidade) {
        this.temHabilidade = temHabilidade;
    }

    public Integer getSlotsLancaMisseis() {
        return slotsLancaMisseis;
    }

    public void setSlotsLancaMisseis(Integer slotsLancaMisseis) {
        this.slotsLancaMisseis = slotsLancaMisseis;
    }

}