package com.newsapp.newsapiclient.data.repository.dataSourceImpl

import com.newsapp.newsapiclient.data.api.NewsAPI
import com.newsapp.newsapiclient.data.model.APIResponse
import com.newsapp.newsapiclient.data.repository.dataSource.Newsremotedata
import retrofit2.Response

class NewsremotedataImpl(private val newsAPI: NewsAPI):Newsremotedata {
    override suspend fun gettopheadlinesfromapi(country : String, page : Int): Response<APIResponse> {
          return newsAPI.gettopheadlinesfromapi(country,page) }

    override suspend fun getsearchednewsfromapi(searchQuery: String, page: Int): Response<APIResponse> {
        return newsAPI.getsearchedtopheadlinesfromapi(searchQuery,page) }
}