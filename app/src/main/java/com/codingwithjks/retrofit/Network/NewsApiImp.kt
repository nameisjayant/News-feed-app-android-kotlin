package com.codingwithjks.retrofit.Network

import com.codingwithjks.newsfeedapp.Model.News
import com.codingwithjks.newsfeedapp.Network.NewsApi
import javax.inject.Inject

class NewsApiImp @Inject constructor(private val newsApi: NewsApi) {

    suspend fun getNews(q:String, apiKey:String, from:String, publishedKey:String
    ):News = newsApi.getNews(q,apiKey,from,publishedKey)
 }
