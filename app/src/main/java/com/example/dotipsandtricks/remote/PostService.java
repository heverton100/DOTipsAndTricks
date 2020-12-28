package com.example.dotipsandtricks.remote;

import com.example.dotipsandtricks.model.CategoryItem;
import com.example.dotipsandtricks.model.Items;
import com.example.dotipsandtricks.model.Modules;
import com.example.dotipsandtricks.model.Assembly;
import com.example.dotipsandtricks.model.ShipAbilities;
import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.model.PointsPilot;
import com.example.dotipsandtricks.model.Skilllevels;
import com.example.dotipsandtricks.model.Skilltree;
import com.example.dotipsandtricks.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostService {

    @GET("getNaves.php")
    Call<List<Ships>> getNaves();

    @GET("getModulos.php")
    Call<List<Modules>> getModulos();

    @GET("getNave.php")
    Call<Ships> getNave(@Query("naveID") Integer naveID);

    @GET("getNaveModulos.php")
    Call<List<Modules>> getNaveModulos(@Query("naveID") Integer naveID);

    @GET("getModuloNaves.php")
    Call<List<Ships>> getModuloNaves(@Query("moduloID") Integer moduloID);

    @GET("getModulos.php")
    Call<List<Modules>> getModulosFiltro(@Query("filtro") String filtro);

    @GET("getCategorias.php")
    Call<List<CategoryItem>> getCategorias();

    @GET("getItens.php")
    Call<List<Items>> getItens(@Query("categoriaID") Integer categoriaID);

    @GET("getItem.php")
    Call<Items> getItem(@Query("itemID") Integer itemID);

    @GET("getMontagem.php")
    Call<List<Assembly>> getMontagem(@Query("itemID") Integer itemID);

    @GET("getPP.php")
    Call<List<PointsPilot>> getPP();

    @GET("getPP.php")
    Call<List<PointsPilot>> getPPfiltro(@Query("filtro") String filtro);

    @GET("getSkilltree.php")
    Call<List<Skilltree>> getSkilltree();

    @GET("getSkilllevels.php")
    Call<List<Skilllevels>> getSkilllevels(@Query("skillID") Integer skillID);

    @GET("getSkilltree.php")
    Call<List<Skilltree>> getSkilltreefiltro(@Query("filtro") String filtro);

    @POST("user/login.php")
    @FormUrlEncoded
    Call<Users> login(@Field("useremail") String useremail,
                         @Field("pass") String userpass);

    @POST("user/register.php")
    @FormUrlEncoded
    Call<Users> register(@Field("username") String username,
                        @Field("useremail") String useremail,
                        @Field("pass") String userpass);

    @POST("user/forgotpass.php")
    @FormUrlEncoded
    Call<Users> forgotpass(@Field("useremail") String useremail,
                         @Field("pass") String userpass);

    @GET("getAbilities.php")
    Call<List<ShipAbilities>> getAbilities(@Query("naveID") Integer naveID);

}

