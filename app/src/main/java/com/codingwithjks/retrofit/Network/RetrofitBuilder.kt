package com.codingwithjks.newsfeedapp.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun getRetrofit():Retrofit= Retrofit.Builder()
        .baseUrl(BaseUrl.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newApi:NewsApi= getRetrofit().create(NewsApi::class.java)

}