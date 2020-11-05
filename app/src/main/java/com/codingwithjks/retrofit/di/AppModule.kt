package com.codingwithjks.retrofit.di

import com.codingwithjks.newsfeedapp.Network.BaseUrl
import com.codingwithjks.newsfeedapp.Network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule  {

    @Provides
    fun provideBaseUrl():String = BaseUrl.baseUrl

    @Provides
    @Singleton
    fun providesRetrofitBuilder(baseUrl:String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideNewApi(retrofit: Retrofit) : NewsApi = retrofit.create(NewsApi::class.java)
}