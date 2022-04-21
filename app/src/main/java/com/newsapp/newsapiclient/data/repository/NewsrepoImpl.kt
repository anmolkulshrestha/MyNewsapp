package com.newsapp.newsapiclient.data.repository

import com.newsapp.newsapiclient.data.model.APIResponse
import com.newsapp.newsapiclient.data.model.Article
import com.newsapp.newsapiclient.data.repository.dataSource.Newslocaldata
import com.newsapp.newsapiclient.data.repository.dataSource.Newsremotedata
import com.newsapp.newsapiclient.data.util.Resource
import com.newsapp.newsapiclient.domain.repository.Newsrepo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsrepoImpl(private val newsremotedata: Newsremotedata, private val newslocaldata: Newslocaldata):Newsrepo {
    override suspend fun getnewsheadlinesfromapi(country : String, page : Int): Resource<APIResponse> {
        return responseToResource(newsremotedata.gettopheadlinesfromapi(country,page))
    }

    override suspend fun getsearchednewsfromapi(searchQuery: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsremotedata.getsearchednewsfromapi(searchQuery,page))
    }
    private fun responseToResource(response:Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result) } }
        return Resource.Error(response.message())
    }
    
    override suspend fun savenews(article: Article) {
        newslocaldata.savearticletodb(article) }

    override suspend fun deletenews(article: Article) {
        newslocaldata.deletearticlesfromdb(article) }

    override fun getsavednews(): Flow<List<Article>> {
        return newslocaldata.getsavedarticles() }
}