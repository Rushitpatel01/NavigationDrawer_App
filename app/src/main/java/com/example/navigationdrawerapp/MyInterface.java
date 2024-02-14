package com.example.navigationdrawerapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyInterface
{
    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterUser> registerUser(@Field("name") String name,@Field("email")String email,@Field("password") String password);


    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> loginUser(@Field("name")String name,@Field("password") String password);
}
