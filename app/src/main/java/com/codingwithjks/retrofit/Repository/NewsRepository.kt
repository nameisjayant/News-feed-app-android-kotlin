package com.codingwithjks.retrofit.Repository

import com.codingwithjks.newsfeedapp.Model.News
import com.codingwithjks.retrofit.Network.NewsApiImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiImp: NewsApiImp) {

    fun getNews():Flow<News> = flow {
        val response = newsApiImp.getNews("bitcoin","156c7ce8e18b47b98a0a459cb348684f","2020-10-05","publishedAt")
        emit(response)
    }.flowOn(Dispatchers.IO)

}
