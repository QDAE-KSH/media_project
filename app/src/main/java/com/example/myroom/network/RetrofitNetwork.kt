package com.example.myroom.network

import com.mongodb.util.JSON
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Array

interface RetrofitNetwork {


    @GET("/db_connect")
    fun db_connect():Call<String>

    @GET("/keyword")
    fun keyword():Call<Any>

    @GET("/get_places")
    fun get_places():Call<Any>

    @GET("/get_detail")
    fun get_detail(
        @Query("source") source:String?,
        @Query("dest") dest:String?
    ):Call<Detail>




}