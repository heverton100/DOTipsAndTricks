package com.example.dotipsandtricks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tips {

    @SerializedName("id_tip")
    @Expose
    private Integer idTip;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("category_tip")
    @Expose
    private String categoryTip;
    @SerializedName("content_tip")
    @Expose
    private String contentTip;

    @SerializedName("response")
    @Expose
    private String Response;

    @SerializedName("liked")
    @Expose
    private Integer liked;

    @SerializedName("likes")
    @Expose
    private Integer likes;

    @SerializedName("url_image")
    @Expose
    private String urlImage;

    public Integer getIdTip() {
        return idTip;
    }

    public void setIdTip(Integer idTip) {
        this.idTip = idTip;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryTip() {
        return categoryTip;
    }

    public void setCategoryTip(String categoryTip) {
        this.categoryTip = categoryTip;
    }

    public String getContentTip() {
        return contentTip;
    }

    public void setContentTip(String contentTip) {
        this.contentTip = contentTip;
    }

    public String getResponse() {
        return Response;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getUrlImage() {
        return urlImage;
    }

}
