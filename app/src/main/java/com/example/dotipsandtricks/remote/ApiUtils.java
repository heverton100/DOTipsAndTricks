package com.example.dotipsandtricks.remote;

public class ApiUtils {

    public static final String URL_BASE = "https://myaplicationasp.azurewebsites.net/api/";



    public static PostService getPostService(){
        return RetrofitClient.getClient(URL_BASE).create(PostService.class);
    }



}

