package com.example.meuprogressoemkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiElectsRequest {

    @GET("/elects")
    fun getElects(): Call<List<Elect>>

    @GET("/elects/{id}")
    fun getElect(@Path("id")): Call<Elect>
}