package com.example.infosysdemo.webservicecall.`interface`

import com.example.infosysdemo.model.AppListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface
{

    @GET("facts.json")
    fun getList(): Call<AppListResponse>
}