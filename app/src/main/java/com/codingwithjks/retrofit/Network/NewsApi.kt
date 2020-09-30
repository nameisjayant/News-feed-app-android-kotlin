package com.codingwithjks.newsfeedapp.Network


import com.codingwithjks.newsfeedapp.Model.News
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Query

interface NewsApi {
    //http://newsapi.org/v2/everything?q=bitcoin&from=2020-09-17&sortBy=publishedAt&apiKey=156c7ce8e18b47b98a0a459cb348684f

    @GET("everything/")
    fun getNews(
        @Query("q") q: String,
        @Query("apiKey")
        apiKey: String,
        @Query("from") from: String,
        @Query("sortBy") publishedAt: String

    ): Call<News>
}