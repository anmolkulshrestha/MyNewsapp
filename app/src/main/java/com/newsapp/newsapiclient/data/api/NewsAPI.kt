package com.newsapp.newsapiclient.data.api

import com.newsapp.newsapiclient.BuildConfig
import com.newsapp.newsapiclient.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
  @GET("v2/top-headlines")
  suspend fun gettopheadlinesfromapi(@Query("country") country:String, @Query("page")
      page:Int, @Query("apiKey") apiKey:String = BuildConfig.API_KEY):Response<APIResponse>

    @GET("v2/everything")
    suspend fun getsearchedtopheadlinesfromapi(@Query("q")
        searchQuery:String, @Query("page") page:Int, @Query("apiKey") apiKey:String = BuildConfig.API_KEY
    ):Response<APIResponse>

}