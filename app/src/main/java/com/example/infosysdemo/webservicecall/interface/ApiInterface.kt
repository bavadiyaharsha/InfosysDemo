package com.example.infosysdemo.webservicecall.`interface`

import com.example.infosysdemo.model.AppListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface
{

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getList(): Call<AppListResponse>
}