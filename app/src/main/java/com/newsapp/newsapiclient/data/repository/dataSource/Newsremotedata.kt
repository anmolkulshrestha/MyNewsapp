package com.newsapp.newsapiclient.data.repository.dataSource

import com.newsapp.newsapiclient.data.model.APIResponse
import retrofit2.Response

interface Newsremotedata {
    suspend fun gettopheadlinesfromapi(country : String, page : Int):Response<APIResponse>
    suspend fun getsearchednewsfromapi(searchQuery:String, page : Int):Response<APIResponse>
}