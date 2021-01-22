package com.example.dotipsandtricks.remote;

import com.example.dotipsandtricks.model.response.ResponseMy;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageService {

    @Multipart
    @POST("/1/upload")
    Call<ResponseMy> postImage(@Part("image\"; filename=\"my_file.jpg\" ") RequestBody file, @Part("key") RequestBody key);
}
