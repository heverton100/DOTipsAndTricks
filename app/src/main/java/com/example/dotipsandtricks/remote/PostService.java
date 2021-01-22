package com.example.dotipsandtricks.remote;

import com.example.dotipsandtricks.model.CategoryItem;
import com.example.dotipsandtricks.model.Items;
import com.example.dotipsandtricks.model.Modules;
import com.example.dotipsandtricks.model.Assembly;
import com.example.dotipsandtricks.model.ShipAbilities;
import com.example.dotipsandtricks.model.ShipDesigns;
import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.model.PointsPilot;
import com.example.dotipsandtricks.model.Skilllevels;
import com.example.dotipsandtricks.model.Skilltree;
import com.example.dotipsandtricks.model.Tips;
import com.example.dotipsandtricks.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostService {

    @GET("getShips.php")
    Call<List<Ships>> getShips();

    @GET("getModules.php")
    Call<List<Modules>> getModules();

    @GET("getShip.php")
    Call<Ships> getShip(@Query("shipID") Integer shipID);

    @GET("getShipModules.php")
    Call<List<Modules>> getShipModules(@Query("shipID") Integer shipID);

    @GET("getModuleShips.php")
    Call<List<Ships>> getModuleShips(@Query("moduleID") Integer moduleID);

    @GET("getModules.php")
    Call<List<Modules>> getModulesFilter(@Query("filter") String filter);

    @GET("getCategories.php")
    Call<List<CategoryItem>> getCategories();

    @GET("getItems.php")
    Call<List<Items>> getItems(@Query("categoryID") Integer categoryID);

    @GET("getItem.php")
    Call<Items> getItem(@Query("itemID") Integer itemID);

    @GET("getAssembly.php")
    Call<List<Assembly>> getAssembly(@Query("itemID") Integer itemID);

    @GET("getPP.php")
    Call<List<PointsPilot>> getPP();

    @GET("getPP.php")
    Call<List<PointsPilot>> getPPfilter(@Query("filter") String filter);

    @GET("getSkilltree.php")
    Call<List<Skilltree>> getSkilltree();

    @GET("getSkilllevels.php")
    Call<List<Skilllevels>> getSkilllevels(@Query("skillID") Integer skillID);

    @GET("getSkilltree.php")
    Call<List<Skilltree>> getSkilltreefilter(@Query("filter") String filter);

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
    Call<List<ShipAbilities>> getAbilities(@Query("shipID") Integer shipID);

    @GET("tips/getTips.php")
    Call<List<Tips>> getTips(@Query("userlogged") Integer userlogged);

    @POST("tips/daoTip.php")
    @FormUrlEncoded
    Call<Tips> newTip(@Field("function") String function,
                     @Field("id_user") Integer id_user,
                     @Field("category_tip") String category_tip,
                     @Field("content_tip") String content_tip,
                     @Field("url_image") String url_image);

    @GET("tips/getTips.php")
    Call<List<Tips>> getMyTips(@Query("filtroid") Integer filtroid, @Query("userlogged") Integer userlogged);

    @POST("tips/daoTip.php")
    @FormUrlEncoded
    Call<Tips> deleteTip(@Field("function") String function,
                      @Field("id_tip") Integer id_tip);

    @POST("tips/daoTip.php")
    @FormUrlEncoded
    Call<Tips> newLike(@Field("function") String function,
                         @Field("id_tip") Integer id_tip,
                         @Field("id_user") Integer id_user);

    @POST("tips/daoTip.php")
    @FormUrlEncoded
    Call<Tips> desLike(@Field("function") String function,
                       @Field("id_tip") Integer id_tip,
                       @Field("id_user") Integer id_user);

    @GET("getShipDesigns.php")
    Call<List<ShipDesigns>> getShipDesigns(@Query("shipID") Integer shipID);

}

