package com.example.navigationdrawerapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Instance_Class
{
    public static MyInterface callAPI()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rushitpatel.000webhostapp.com/mySite/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyInterface myInterface = retrofit.create(MyInterface.class);
        return myInterface;

    }
}
