package com.newsapp.newsapiclient.presentation.di

import com.newsapp.newsapiclient.BuildConfig
import com.newsapp.newsapiclient.data.api.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Netmodule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.BASE_URL).build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit):NewsAPI{


        return retrofit.create(NewsAPI::class.java)
    }



}













