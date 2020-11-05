package com.codingwithjks.retrofit.ViewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codingwithjks.newsfeedapp.Model.News
import com.codingwithjks.retrofit.Repository.NewsRepository
import kotlinx.coroutines.flow.catch

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val newsResponse:LiveData<News> =
        newsRepository.getNews()
            .catch { e->
                Log.d("main", "${e.message}")
            }.asLiveData()

}