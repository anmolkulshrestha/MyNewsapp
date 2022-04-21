package com.newsapp.newsapiclient.domain.repository

import com.newsapp.newsapiclient.data.model.APIResponse
import com.newsapp.newsapiclient.data.model.Article
import com.newsapp.newsapiclient.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Newsrepo{

      suspend fun getnewsheadlinesfromapi(country : String, page : Int): Resource<APIResponse>
      suspend fun getsearchednewsfromapi(searchQuery:String,page: Int) : Resource<APIResponse>
      suspend fun savenews(article: Article)
      suspend fun deletenews(article: Article)
      fun getsavednews(): Flow<List<Article>>




}